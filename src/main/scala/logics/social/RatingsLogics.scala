package logics.social

import cats.effect.IO
import memory.repositories.RatingRepository
import domain.app.social.Rating
import domain.app.user.User
import domain.errors.UserError.*
import domain.ids.Social.RatingId
import logics.functions.{CommonFunctions, RatingsAuxFunctions}

object RatingsLogics {


  val getRating: RatingId => IO[Either[UserError, Rating]] =
    ratingId => IO.pure {
      CommonFunctions.getRating(ratingId) match 
        case Left(error)   => Left(error)
        case Right(rating) => Right(rating)
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val createRating: Rating => IO[Either[UserError, Rating]] =
    newRating => IO.pure {
      RatingRepository.get(newRating.id) match
        case Some(_)                         => Left(Conflict(s"Rating with ID ${newRating.id.value} already exists"))
        case None if newRating.id.value <= 0 => Left(BadRequest("Invalid rating ID"))
        case None                            =>
          CommonFunctions.getUserAndApply(newRating.userId)(newRating, RatingsAuxFunctions.addNewRatingToUser) match
            case Left(error) => Left(error)
            case Right(_)    =>
              RatingRepository.put(newRating.id, newRating)
              Right(newRating)
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val editRating: ((RatingId, Rating)) => IO[Either[UserError, Rating]] =
    (ratingId, updatedRating) => IO.pure {
      if (ratingId.value != updatedRating.id.value)
        Left(BadRequest("Rating ID in path and updated rating ID did not match"))
      else
        CommonFunctions.getRating(ratingId) match
          case Left(error)           => Left(error)
          case Right(existingRating) =>
            CommonFunctions.getUserAndApply(existingRating.userId)(existingRating, RatingsAuxFunctions.updateUserFromRating) match
              case Left(error) => Left(error)
              case Right(_)    =>
                RatingRepository.put(ratingId, updatedRating)
                Right(updatedRating)
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val deleteRating: RatingId => IO[Either[UserError, Unit]] =
    ratingId => IO.pure {
      CommonFunctions.getRating(ratingId) match
        case Left(error)   => Left(error)
        case Right(rating) =>
          CommonFunctions.getUserAndApply(rating.userId)(rating, RatingsAuxFunctions.removeRatingFromUser) match
            case Left(error) => Left(error)
            case Right(_)    =>
              RatingRepository.delete(rating.id)
              Right(())

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

}
