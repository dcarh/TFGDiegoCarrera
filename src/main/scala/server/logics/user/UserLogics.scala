package server.logics.user

import cats.effect.IO
import dummies.repositories.UserRepository

import modelClasses.app.user.User
import modelClasses.errors.UserError.*
import modelClasses.ids.User.UserId

import server.logics.commonFunctions.CommonFunctions

object UserLogics {

  val getUser: UserId => IO[Either[UserError, User]] =
    userId => IO {
      CommonFunctions.getUser(userId) match
        case Left(error) => Left(error)
        case Right(user) => Right(user)
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
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
      CommonFunctions.getUser(userId) match
        case Left(error) => Left(error)
        case Right(user) =>
          UserRepository.delete(userId) 
          Right(())
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

}
