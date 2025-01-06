package server.logics.user.chatting

import cats.effect.IO
import dummies.repositories.UserRepository

import modelClasses.errors.UserError.*
import modelClasses.ids.User.UserId
import modelClasses.ids.Chatting.ChatId

object UserChatsLogics {
  
  // TODO: Implementar la funcionalidad del sortByOption

  val getUserChatsLogic: ((UserId, Option[String])) => IO[Either[UserError, List[ChatId]]] =
    (userId, sortByOption) => IO {
      UserRepository.get(userId) match {
        case Some(user) => 
          Right(user.chats)

        case None if userId.value <= 0 =>
          Left(BadRequest("Invalid user ID"))

        case None =>
          Left(NotFound(s"User with ID ${userId.value} not found"))
      }
    }.handleError {
      case ex: Exception =>
        Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }
}
