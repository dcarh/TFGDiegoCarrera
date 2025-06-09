package logics.functions

import domain.app.social.Review
import domain.app.user.User
import domain.errors.UserError.{BadRequest, UserError}
import memory.repositories.UserRepository

object ReviewsAuxFunctions {

  val addNewReviewToUser: (User, Review) => Either[UserError, User] =
    (user, review) =>
      if !user.reviewsIds.contains(review.id) then
        val updatedUser = user.copy(
          reviewsIds = review.id :: user.reviewsIds
        )
        UserRepository.put(updatedUser.id, updatedUser)
        Right(user)

      else
        Left(BadRequest("The user with the ID stored in the review already has an review with the same ID"))

  val updateUserFromReview: (User, Review) => Either[UserError, User] =
    (user, review) =>
      if user.reviewsIds.contains(review.id) then
        Right(user)

      else
        Left(BadRequest("The user with the ID stored in the review doesn't own the review"))

  val removeReviewFromUser: (User, Review) => Either[UserError, User] =
    (user, review) =>
      if user.reviewsIds.contains(review.id) then
        val updatedUser = user.copy(
          reviewsIds = user.reviewsIds.filterNot(_ == review.id)
        )
        UserRepository.put(updatedUser.id, updatedUser)
        Right(user)

      else
        Left(BadRequest("The user ID stored in the review doesn't own the review"))

}
