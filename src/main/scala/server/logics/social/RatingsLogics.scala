package server.logics.social

import cats.effect.IO
import dummies.repositories.RatingRepository

import modelClasses.app.social.Rating
import modelClasses.errors.UserError.*
import modelClasses.ids.Social.RatingId

object RatingsLogics {

  val getRatingLogic: RatingId => IO[Either[UserError, Rating]] =
    ratingId => IO {
      RatingRepository.get(ratingId) match {
        case Some(rating) =>
          Right(rating)

        case None if ratingId.value <= 0 =>
          Left(BadRequest("Invalid rating ID"))

        case None =>
          Left(NotFound(s"Rating with ID ${ratingId.value} not found"))
      }
    }.handleError {
      case ex: Exception =>
        Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val createRatingLogic: Rating => IO[Either[UserError, Rating]] =
    newRating => IO {
      RatingRepository.put(newRating.id, newRating)
      Right(newRating)
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val editRatingLogic: ((RatingId, Rating)) => IO[Either[UserError, Rating]] =
    (ratingId, updatedRatingData) => IO {
      RatingRepository.get(ratingId) match {
        case Some(existingRating) =>
          val updatedRating = existingRating.copy(
            id = updatedRatingData.id,
            userId = updatedRatingData.userId,
            mediaRatedId = updatedRatingData.mediaRatedId,
            rating = updatedRatingData.rating
          )
          RatingRepository.put(ratingId, updatedRating)
          Right(updatedRating)
        case None =>
          Left(NotFound(s"Rating with ID ${ratingId.value} not found"))
      }
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val deleteRatingLogic: RatingId => IO[Either[UserError, Unit]] =
    ratingId => IO {
      RatingRepository.delete(ratingId) match {
        case "Object deleted successfully!" =>
          Right(())
        case otherMessage =>
          Left(Conflict(s"Rating with ID ${ratingId.value} could not be deleted: $otherMessage"))
      }
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

}
