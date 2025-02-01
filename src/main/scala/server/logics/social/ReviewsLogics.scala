package server.logics.social

import cats.effect.IO
import dummies.repositories.{ReviewRepository, UserRepository}

import modelClasses.app.social.Review
import modelClasses.app.user.User
import modelClasses.errors.UserError.*
import modelClasses.ids.Social.ReviewId
import modelClasses.ids.User.UserId

object ReviewsLogics {

  private def checkIfUserExistsAndApply(userId: UserId)(reviewId: ReviewId, f: (User, ReviewId) => Either[UserError, User]): Either[UserError, User] =
    UserRepository.get(userId) match
      case Some(user) =>
        f(user, reviewId)

      case None if userId.value <= 0 =>
        Left(BadRequest("Invalid review ID"))

      case None =>
        Left(NotFound(s"Review with ID ${userId.value} not found"))

  private val addNewReviewToUser: (User, ReviewId) => Either[UserError, User] =
    (user, reviewId) =>
      if !user.reviews.contains(reviewId) then
        val updatedUser = user.copy(
          reviews = reviewId :: user.reviews
        )
        UserRepository.put(updatedUser.id, updatedUser)
        Right(user)

      else
        Left(BadRequest("The user with the ID stored in the review already has an review with the same ID"))

  private val updateUserFromReview: (User, ReviewId) => Either[UserError, User] =
    (user, reviewId) =>
      if user.reviews.contains(reviewId) then
        Right(user)

      else
        Left(BadRequest("The user with the ID stored in the review doesn't own the review"))

  private val removeReviewFromUser: (User, ReviewId) => Either[UserError, User] =
    (user, reviewId) =>
      if user.reviews.contains(reviewId) then
        val updatedUser = user.copy(
          reviews = user.reviews.filterNot(_ == reviewId)
        )
        UserRepository.put(updatedUser.id, updatedUser)
        Right(user)

      else
        Left(BadRequest("The user ID stored in the review doesn't own the review"))



  val getAllReviews: Option[String] => IO[Either[UserError, List[Review]]] = {
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

  val getReview: ReviewId => IO[Either[UserError, Review]] =
    reviewId => IO {
      ReviewRepository.get(reviewId) match 
        case Some(review) =>
          Right(review)

        case None if reviewId.value <= 0 =>
          Left(BadRequest("Invalid review ID"))

        case None =>
          Left(NotFound(s"Review with ID ${reviewId.value} not found"))
      
    }.handleError {
      case ex: Exception =>
        Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val createReview: Review => IO[Either[UserError, Review]] =
    newReview => IO {
      ReviewRepository.get(newReview.id) match
        case Some(_) =>
          Left(Conflict(s"Review with ID ${newReview.id.value} already exists"))

        case None if newReview.id.value <= 0 =>
          Left(BadRequest("Invalid review ID"))

        case None =>
          checkIfUserExistsAndApply(newReview.userId)(newReview.id, addNewReviewToUser) match
            case Right(_) =>
              ReviewRepository.put(newReview.id, newReview)
              Right(newReview)

            case Left(error) =>
              Left(error)
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val editReview: ((ReviewId, Review)) => IO[Either[UserError, Review]] =
    (reviewId, updatedReviewData) => IO {
      ReviewRepository.get(reviewId) match 
        case Some(existingReview) =>
          checkIfUserExistsAndApply(existingReview.userId)(existingReview.id, updateUserFromReview) match
            case Right(value) =>
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
            
            case Left(error) => Left(error)

        case None if reviewId.value <= 0 =>
          Left(BadRequest("Invalid review ID"))
          
        case None =>
          Left(NotFound(s"Review with ID ${reviewId.value} not found"))
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val deleteReview: ReviewId => IO[Either[UserError, Unit]] =
    reviewId => IO {
      ReviewRepository.get(reviewId) match
        case Some(review) =>
          checkIfUserExistsAndApply(review.userId)(review.id, removeReviewFromUser) match
            case Right(_) =>
              ReviewRepository.delete(review.id)
              Right(())

            case Left(error) => Left(error)

        case None if reviewId.value <= 0 =>
          Left(BadRequest("Invalid review ID"))

        case None =>
          Left(NotFound(s"Review with ID ${reviewId.value} not found"))
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

}
