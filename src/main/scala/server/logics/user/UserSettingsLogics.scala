package server.logics.user

import cats.effect.IO
import dummies.repositories.UserRepository
import modelClasses.app.user.UserSettings
import modelClasses.errors.UserError.*
import modelClasses.ids.User.UserId

object UserSettingsLogics {

  val getUserSettings: UserId => IO[Either[UserError, UserSettings]] =
    userId => IO {
      UserRepository.get(userId) match {
        case Some(user) =>
          Right(user.settings)

        case None if userId.value <= 0 =>
          Left(BadRequest("Invalid user ID"))

        case None =>
          Left(NotFound(s"User with ID ${userId.value} not found"))
      }
    }.handleError {
      case ex: Exception =>
        Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val editUserSettings: ((UserId, UserSettings)) => IO[Either[UserError, UserSettings]] =
    (userId, updatedUserSettingsData) => IO {
      UserRepository.get(userId) match {
        case Some(existingUser) =>
          val updatedUser = existingUser.copy(
            settings = existingUser.settings.copy(
              isPrivate = updatedUserSettingsData.isPrivate
            )
          )
          UserRepository.put(userId, updatedUser)
          Right(updatedUser.settings)
        case None =>
          Left(NotFound(s"User with ID ${userId.value} not found"))
      }
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }
}
