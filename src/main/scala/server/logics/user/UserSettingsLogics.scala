package server.logics.user

import cats.effect.IO
import dummies.repositories.UserRepository
import modelClasses.app.user.UserSettings
import modelClasses.errors.UserError.*
import modelClasses.ids.User.UserId

import server.logics.commonFunctions.CommonFunctions

object UserSettingsLogics {

  val getUserSettings: UserId => IO[Either[UserError, UserSettings]] =
    userId => IO {
      CommonFunctions.getUser(userId) match
        case Left(error) => Left(error)
        case Right(user) =>
          Right(user.settings)
          
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val editUserSettings: ((UserId, UserSettings)) => IO[Either[UserError, UserSettings]] =
    (userId, updatedUserSettingsData) => IO {
      CommonFunctions.getUser(userId) match
        case Left(error) => Left(error)
        case Right(user) =>
          val updatedUser = user.copy(
            settings = user.settings.copy(
              isPrivate = updatedUserSettingsData.isPrivate
              )
            )
          UserRepository.put(userId, updatedUser)
          Right(updatedUser.settings)
          
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }
}
