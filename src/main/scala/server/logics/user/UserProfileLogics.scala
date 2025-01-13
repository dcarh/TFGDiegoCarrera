package server.logics.user

import cats.effect.IO
import dummies.repositories.UserRepository
import modelClasses.app.user.UserProfile
import modelClasses.errors.UserError.*
import modelClasses.ids.User.UserId

object UserProfileLogics {

  val getUserProfile: UserId => IO[Either[UserError, UserProfile]] =
    userId => IO {
      UserRepository.get(userId) match {
        case Some(user) =>
          Right(user.profile)

        case None if userId.value <= 0 =>
          Left(BadRequest("Invalid entry ID"))

        case None =>
          Left(NotFound(s"User with ID ${userId.value} not found"))
      }
    }.handleError {
      case ex: Exception =>
        Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }
    
  val editUserProfile: ((UserId, UserProfile)) => IO[Either[UserError, UserProfile]] =
    (userId, updatedUserProfileData) => IO {
      UserRepository.get(userId) match {
        case Some(existingUser) =>
          val updatedUser = existingUser.copy(
            profile = existingUser.profile.copy(
              username = updatedUserProfileData.username,
              password = updatedUserProfileData.password,
              email = updatedUserProfileData.email,
              biography = updatedUserProfileData.biography,
              location = updatedUserProfileData.location
            )
          )
          UserRepository.put(userId, updatedUser)
          Right(updatedUser.profile)
        case None =>
          Left(NotFound(s"User with ID ${userId.value} not found"))
      }
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }
}
