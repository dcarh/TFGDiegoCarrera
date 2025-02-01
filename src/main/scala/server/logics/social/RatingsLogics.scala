package server.logics.social

import cats.effect.IO
import dummies.repositories.{RatingRepository, UserRepository}

import modelClasses.app.social.Rating
import modelClasses.app.user.User
import modelClasses.errors.UserError.*
import modelClasses.ids.Social.RatingId
import modelClasses.ids.User.UserId

object RatingsLogics {

  private def checkIfUserExistsAndApply(userId: UserId)(ratingId: RatingId, f: (User, RatingId) => Either[UserError, User]): Either[UserError, User] =
    UserRepository.get(userId) match
      case Some(user) =>
        f(user, ratingId)

      case None if userId.value <= 0 =>
        Left(BadRequest("Invalid rating ID"))

      case None =>
        Left(NotFound(s"Rating with ID ${userId.value} not found"))

  private val addNewRatingToUser: (User, RatingId) => Either[UserError, User] =
    (user, ratingId) =>
      if !user.ratings.contains(ratingId) then
        val updatedUser = user.copy(
          ratings = ratingId :: user.ratings
        )
        UserRepository.put(updatedUser.id, updatedUser)
        Right(user)

      else
        Left(BadRequest("The user with the ID stored in the rating already has an rating with the same ID"))

  private val updateUserFromRating: (User, RatingId) => Either[UserError, User] =
    (user, ratingId) =>
      if user.ratings.contains(ratingId) then
        Right(user)

      else
        Left(BadRequest("The user with the ID stored in the rating doesn't own the rating"))

  private val removeRatingFromUser: (User, RatingId) => Either[UserError, User] =
    (user, ratingId) =>
      if user.ratings.contains(ratingId) then
        val updatedUser = user.copy(
          ratings = user.ratings.filterNot(_ == ratingId)
        )
        UserRepository.put(updatedUser.id, updatedUser)
        Right(user)

      else
        Left(BadRequest("The user ID stored in the rating doesn't own the rating"))


  val getRating: RatingId => IO[Either[UserError, Rating]] =
    ratingId => IO {
      RatingRepository.get(ratingId) match 
        case Some(rating) =>
          Right(rating)

        case None if ratingId.value <= 0 =>
          Left(BadRequest("Invalid rating ID"))

        case None =>
          Left(NotFound(s"Rating with ID ${ratingId.value} not found"))
      
    }.handleError {
      case ex: Exception =>
        Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val createRating: Rating => IO[Either[UserError, Rating]] =
    newRating => IO {
      RatingRepository.get(newRating.id) match
        case Some(_) =>
          Left(Conflict(s"Rating with ID ${newRating.id.value} already exists"))
        
        case None if newRating.id.value <= 0 =>
          Left(BadRequest("Invalid rating ID"))
        
        case None =>
          checkIfUserExistsAndApply(newRating.userId)(newRating.id, addNewRatingToUser) match
            case Right(_) =>
              RatingRepository.put(newRating.id, newRating)
              Right(newRating)

            case Left(error) =>
              Left(error)
        
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val editRating: ((RatingId, Rating)) => IO[Either[UserError, Rating]] =
    (ratingId, updatedRatingData) => IO {
      RatingRepository.get(ratingId) match
        case Some(existingRating) =>
          checkIfUserExistsAndApply(existingRating.userId)(existingRating.id, updateUserFromRating) match
            case Right(_) =>
              val updatedRating = existingRating.copy(
                id = updatedRatingData.id,
                userId = updatedRatingData.userId,
                mediaRatedId = updatedRatingData.mediaRatedId,
                rating = updatedRatingData.rating
              )
              RatingRepository.put(ratingId, updatedRating)
              Right(updatedRating)
            
            case Left(error) => Left(error)
          
        case None if ratingId.value <= 0 =>
          Left(BadRequest("Invalid rating ID"))
          
        case None =>
          Left(NotFound(s"Rating with ID ${ratingId.value} not found"))
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val deleteRating: RatingId => IO[Either[UserError, Unit]] =
    ratingId => IO {
      RatingRepository.get(ratingId) match
        case Some(rating) =>
          checkIfUserExistsAndApply(rating.userId)(rating.id, removeRatingFromUser) match
            case Right(_) =>
              RatingRepository.delete(rating.id)
              Right(())

            case Left(error) => Left(error)
          
        case None if ratingId.value <= 0 =>
          Left(BadRequest("Invalid rating ID"))
          
        case None =>
          Left(NotFound(s"Rating with ID ${ratingId.value} not found"))

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

}
