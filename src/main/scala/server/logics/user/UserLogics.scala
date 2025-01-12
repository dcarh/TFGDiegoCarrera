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

  // TODO: Puede que, lo más coherente, a la hora de crear el usuario, es que este reciba un UserProfile, en vez de un User
  val createUser: User => IO[Either[UserError, User]] =
    newUser => IO {
      UserRepository.put(newUser.id, newUser)
      Right(newUser)
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val deleteUser: UserId => IO[Either[UserError, Unit]] =
    userId => IO {
      UserRepository.delete(userId) match {
        case "Object deleted successfully!" =>
          Right(())
        case otherMessage =>
          Left(Conflict(s"User with ID ${userId.value} could not be deleted: $otherMessage"))
      }
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

}
