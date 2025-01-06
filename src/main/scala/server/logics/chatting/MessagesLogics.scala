package server.logics.chatting

import cats.effect.IO
import dummies.repositories.MessageRepository

import modelClasses.app.chatting.Message
import modelClasses.errors.UserError.*
import modelClasses.ids.Chatting.MessageId

object MessagesLogics {

  val getMessageLogic:
    MessageId => IO[Either[UserError, Message]] =
      messageId => IO {
        MessageRepository.get(messageId) match {
          case Some(message) =>
            Right(message)
  
          case None if messageId.value <= 0 =>
            Left(BadRequest("Invalid message ID"))
  
          case None =>
            Left(NotFound(s"Message with ID ${messageId.value} not found"))
        }
      }.handleError {
        case ex: Exception =>
          Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
      }

  val createMessageLogic: Message => IO[Either[UserError, Message]] =
    newMessage => IO {
      MessageRepository.put(newMessage.id, newMessage)
      Right(newMessage)
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val editMessageLogic: ((MessageId, Message)) => IO[Either[UserError, Message]] =
    (messageId, updatedMessageData) => IO {
      MessageRepository.get(messageId) match {
        case Some(existingMessage) =>
          val updatedMessage = existingMessage.copy(
            id = updatedMessageData.id,
            userId = updatedMessageData.userId,
            message = updatedMessageData.message,
            date = updatedMessageData.date
          )
          MessageRepository.put(messageId, updatedMessage)
          Right(updatedMessage)
        case None =>
          Left(NotFound(s"Message with ID ${messageId.value} not found"))
      }
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val deleteMessageLogic: MessageId => IO[Either[UserError, Unit]] =
    messageId => IO {
      MessageRepository.delete(messageId) match {
        case "Object deleted successfully!" =>
          Right(())
        case otherMessage =>
          Left(Conflict(s"Message with ID ${messageId.value} could not be deleted: $otherMessage"))
      }
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }
    
}
