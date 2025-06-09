package logics.functions

import domain.app.social.Rating
import domain.app.user.User
import domain.errors.UserError.{BadRequest, UserError}
import memory.repositories.UserRepository

object RatingsAuxFunctions {

  val addNewRatingToUser: (User, Rating) => Either[UserError, User] =
    (user, rating) =>
      if !user.ratingsIds.contains(rating.id) then
        val updatedUser = user.copy(
          ratingsIds = rating.id :: user.ratingsIds
        )
        UserRepository.put(updatedUser.id, updatedUser)
        Right(user)

      else
        Left(BadRequest("The user with the ID stored in the rating already has an rating with the same ID"))

  val updateUserFromRating: (User, Rating) => Either[UserError, User] =
    (user, rating) =>
      if user.ratingsIds.contains(rating.id) then
        Right(user)

      else
        Left(BadRequest("The user with the ID stored in the rating doesn't own the rating"))

  val removeRatingFromUser: (User, Rating) => Either[UserError, User] =
    (user, rating) =>
      if user.ratingsIds.contains(rating.id) then
        val updatedUser = user.copy(
          ratingsIds = user.ratingsIds.filterNot(_ == rating.id)
        )
        UserRepository.put(updatedUser.id, updatedUser)
        Right(user)

      else
        Left(BadRequest("The user ID stored in the rating doesn't own the rating"))

}
