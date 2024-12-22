package server.logics.social

import cats.effect.IO

import modelClasses.app.social.Review
import modelClasses.errors.UserError.*
import modelClasses.ids.Social.ReviewId
import modelClasses.dummies.repositories.ReviewRepository

object ReviewsLogics {

  // TODO: getAllReviewsLogic
  val getAllReviewsLogic: Option[String] => IO[Either[UserError, List[Review]]] = {
    sortByOption =>
      IO {
        val reviews = ReviewRepository.getAll

        val sortedReviews = sortByOption match {
          case Some("least_liked") =>
            Right(reviews.sortBy(_.likes.size))

          case Some("most_liked") =>
            Right(reviews.sortBy(_.likes.size).reverse)

          case Some("least_replied") =>
            Right(reviews.sortBy(_.replies.size))

          case Some("most_replied") =>
            Right(reviews.sortBy(_.replies.size).reverse)

          case Some(unknown) =>
            Left(BadRequest(s"Invalid sorting parameter: $unknown"))

          case None =>
            Right(reviews)
        }
        sortedReviews
      }.handleError {
        case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }
  }

  val getReviewLogic: ReviewId => IO[Either[UserError, Review]] =
    reviewId => IO {
      ReviewRepository.get(reviewId) match {
        case Some(review) =>
          Right(review)

        case None if reviewId.value <= 0 =>
          Left(BadRequest("Invalid review ID"))

        case None =>
          Left(NotFound(s"Review with ID $reviewId not found"))
      }
    }.handleError {
      case ex: Exception =>
        Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val createReviewLogic: Review => IO[Either[UserError, Review]] =
    newReview => IO {
      ReviewRepository.put(newReview.id, newReview)
      Right(newReview)
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val editReviewLogic: ((ReviewId, Review)) => IO[Either[UserError, Review]] =
    (reviewId, updatedReviewData) => IO {
      ReviewRepository.get(reviewId) match {
        case Some(existingReview) =>
          val updatedReview = existingReview.copy(
            id = updatedReviewData.id,
            userId = updatedReviewData.userId,
            mediaReviewedId = updatedReviewData.mediaReviewedId,
            review = updatedReviewData.review,
            likes = updatedReviewData.likes,
            allowReplies = updatedReviewData.allowReplies,
            replies = updatedReviewData.replies,
            spoilers = updatedReviewData.spoilers
          )
          ReviewRepository.put(reviewId, updatedReview)
          Right(updatedReview)
        case None =>
          Left(NotFound(s"Review with ID ${reviewId.value} not found"))
      }
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val deleteReviewLogic: ReviewId => IO[Either[UserError, Unit]] =
    reviewId => IO {
      ReviewRepository.delete(reviewId) match {
        case "Object deleted successfully!" =>
          Right(())
        case otherMessage =>
          Left(Conflict(s"Review with ID ${reviewId.value} could not be deleted: $otherMessage"))
      }
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

}
