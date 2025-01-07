package server.logics.user

import cats.effect.IO
import dummies.repositories.UserRepository

import modelClasses.app.user.User
import modelClasses.errors.UserError.*
import modelClasses.ids.User.UserId

object UserLogics {

  val getUser: UserId => IO[Either[UserError, User]] =
    userId => IO {
      UserRepository.get(userId) match {
        case Some(user) =>
          Right(user)

        case None if userId.value <= 0 =>
          Left(BadRequest("Invalid chat ID"))

        case None =>
          Left(NotFound(s"Chat with ID ${userId.value} not found"))
      }
    }.handleError {
      case ex: Exception =>
        Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

}
