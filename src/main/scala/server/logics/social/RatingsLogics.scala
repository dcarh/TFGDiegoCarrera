package server.logics.social

import cats.effect.IO
import dummies.repositories.{RatingRepository, UserRepository}

import modelClasses.app.social.Rating
import modelClasses.app.user.User
import modelClasses.errors.UserError.*
import modelClasses.ids.Social.RatingId
import modelClasses.ids.User.UserId

import server.logics.commonFunctions.CommonFunctions

object RatingsLogics {

  private val addNewRatingToUser: (User, Rating) => Either[UserError, User] =
    (user, rating) =>
      if !user.ratings.contains(rating.id) then
        val updatedUser = user.copy(
          ratings = rating.id :: user.ratings
        )
        UserRepository.put(updatedUser.id, updatedUser)
        Right(user)

      else
        Left(BadRequest("The user with the ID stored in the rating already has an rating with the same ID"))

  private val updateUserFromRating: (User, Rating) => Either[UserError, User] =
    (user, rating) =>
      if user.ratings.contains(rating.id) then
        Right(user)

      else
        Left(BadRequest("The user with the ID stored in the rating doesn't own the rating"))

  private val removeRatingFromUser: (User, Rating) => Either[UserError, User] =
    (user, rating) =>
      if user.ratings.contains(rating.id) then
        val updatedUser = user.copy(
          ratings = user.ratings.filterNot(_ == rating.id)
        )
        UserRepository.put(updatedUser.id, updatedUser)
        Right(user)

      else
        Left(BadRequest("The user ID stored in the rating doesn't own the rating"))


  val getRating: RatingId => IO[Either[UserError, Rating]] =
    ratingId => IO {
      CommonFunctions.getRating(ratingId) match 
        case Left(error) => Left(error)
        case Right(rating) => Right(rating)
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val createRating: Rating => IO[Either[UserError, Rating]] =
    newRating => IO {
      RatingRepository.get(newRating.id) match
        case Some(_) => Left(Conflict(s"Rating with ID ${newRating.id.value} already exists"))
        case None if newRating.id.value <= 0 => Left(BadRequest("Invalid rating ID"))
        case None =>
          CommonFunctions.getUserAndApply(newRating.userId)(newRating, addNewRatingToUser) match
            case Left(error) => Left(error)
            case Right(_) =>
              RatingRepository.put(newRating.id, newRating)
              Right(newRating)

        
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val editRating: ((RatingId, Rating)) => IO[Either[UserError, Rating]] =
    (ratingId, updatedRatingData) => IO {
      CommonFunctions.getRating(ratingId) match
        case Left(error) => Left(error)
        case Right(existingRating) =>
          CommonFunctions.getUserAndApply(existingRating.userId)(existingRating, updateUserFromRating) match
            case Left(error) => Left(error)
            case Right(_) =>
              val updatedRating = existingRating.copy(
                id = updatedRatingData.id,
                userId = updatedRatingData.userId,
                mediaRatedId = updatedRatingData.mediaRatedId,
                rating = updatedRatingData.rating
              )
              RatingRepository.put(ratingId, updatedRating)
              Right(updatedRating)
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val deleteRating: RatingId => IO[Either[UserError, Unit]] =
    ratingId => IO {
      CommonFunctions.getRating(ratingId) match
        case Left(error) => Left(error)
        case Right(rating) =>
          CommonFunctions.getUserAndApply(rating.userId)(rating, removeRatingFromUser) match
            case Left(error) => Left(error)
            case Right(_) =>
              RatingRepository.delete(rating.id)
              Right(())

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

}
