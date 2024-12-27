package server.logics.chatting

import cats.effect.IO
import dummies.repositories.ChatRepository

import modelClasses.app.chatting.Chat
import modelClasses.errors.UserError.*
import modelClasses.ids.Chatting.ChatId

object ChatsLogics {

  val getChatLogic: ChatId => IO[Either[UserError, Chat]] =
    chatId => IO {
      ChatRepository.get(chatId) match {
        case Some(chat) =>
          Right(chat)

        case None if chatId.value <= 0 =>
          Left(BadRequest("Invalid chat ID"))

        case None =>
          Left(NotFound(s"Chat with ID $chatId not found"))
      }
    }.handleError {
      case ex: Exception =>
        Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val createChatLogic: Chat => IO[Either[UserError, Chat]] =
    newChat => IO {
      ChatRepository.put(newChat.id, newChat)
      Right(newChat)
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val editChatLogic: ((ChatId, Chat)) => IO[Either[UserError, Chat]] =
    (chatId, updatedChatData) => IO {
      ChatRepository.get(chatId) match {
        case Some(existingChat) =>
          val updatedChat = existingChat.copy(
            id = updatedChatData.id,
            user1Id = updatedChatData.user1Id,
            user2Id = updatedChatData.user2Id,
            messagesIds = updatedChatData.messagesIds
          )
          ChatRepository.put(chatId, updatedChat)
          Right(updatedChat)
        case None =>
          Left(NotFound(s"Chat with ID ${chatId.value} not found"))
      }
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val deleteChatLogic: ChatId => IO[Either[UserError, Unit]] =
    chatId => IO {
      ChatRepository.delete(chatId) match {
        case "Object deleted successfully!" =>
          Right(())
        case otherMessage =>
          Left(Conflict(s"Chat with ID ${chatId.value} could not be deleted: $otherMessage"))
      }
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }
}