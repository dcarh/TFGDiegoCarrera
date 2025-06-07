package server.logics.chatting

import cats.effect.IO
import memory.repositories.ChatRepository
import memory.repositories.MessageRepository
import memory.repositories.UserRepository

import domain.app.chatting.Chat
import domain.app.chatting.Message
import domain.errors.UserError.*
import domain.ids.Chatting.ChatId
import domain.ids.Chatting.MessageId
import domain.ids.User.UserId

import server.logics.commonFunctions.CommonFunctions.getUser

object ChattingLogics {

  val getChats: ((UserId, Option[Boolean])) => IO[Either[UserError, List[ChatId]]] =
    (userId, archivedOption) => IO.pure {
      getUser(userId) match
        case Left(error) => Left(error)
        case Right(user) => archivedOption match
            case Some(archived) if archived => Right(user.archivedChatsIds)
            case _                          => Right(user.chatsIds)

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val getChat: ((UserId, ChatId)) => IO[Either[UserError, Chat]] =
    (userId, chatId) => IO.pure {
      ChattingAuxFunctions.assertUserAndChatIds(userId, chatId) match
        case Left(error)    => Left(error)
        case Right(_, chat) => Right(chat)
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val archiveChat: ((UserId, ChatId)) => IO[Either[UserError, Chat]] =
    (userId, chatId) => IO.pure {
      ChattingAuxFunctions.assertUserAndChatIds(userId, chatId) match
        case Left(error)       => Left(error)
        case Right(user, chat) =>
          val (updatedChat, updatedUser) = if user.chatsIds.contains(chat.id) && !user.archivedChatsIds.contains(chat.id) then
            val updatedChat = chat.copy(
              archived = true
            )
            val updatedUser = user.copy(
              chatsIds         = user.chatsIds.filterNot(_ == chat.id),
              archivedChatsIds = chat.id :: user.archivedChatsIds
            )
            (updatedChat, updatedUser)

          else if !user.chatsIds.contains(chat.id) && user.archivedChatsIds.contains(chat.id) then
            val updatedChat = chat.copy(
              archived = false
            )
            val updatedUser = user.copy(
              chatsIds         = chat.id :: user.chatsIds,
              archivedChatsIds = user.archivedChatsIds.filterNot(_ == chat.id)
            )
            (updatedChat, updatedUser)
          else
            throw Exception("Internal server error")

          ChatRepository.put(chat.id, updatedChat)
          UserRepository.put(user.id, updatedUser)

          Right(updatedChat)
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val deleteChat: ((UserId, ChatId)) => IO[Either[UserError, Unit]] =
    (userId, chatId) => IO.pure {
      ChattingAuxFunctions.assertUserAndChatIds(userId, chatId) match
        case Left(error) => Left(error)
        case Right(_, _) =>
          ChatRepository.delete(chatId)
            Right(())
            
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val getChatMessages: ((UserId, ChatId)) => IO[Either[UserError, List[MessageId]]] =
    (userId, chatId) => IO.pure {
      ChattingAuxFunctions.assertUserAndChatIds(userId, chatId) match
        case Left(error)    => Left(error)
        case Right(_, chat) => Right(chat.messagesIds)
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val getMessage: ((UserId, ChatId, MessageId)) => IO[Either[UserError, Message]] =
    (userId, chatId, messageId) => IO.pure {
      ChattingAuxFunctions.assertUserAndChatAndMessageIds(userId, chatId, messageId) match
        case Left(error)          => Left(error)
        case Right(_, _, message) => Right(message)
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val sendMessage: ((UserId, ChatId, UserId, Message)) => IO[Either[UserError, Message]] =
    (user1Id, chatId, user2Id, message) => IO.pure {
      ChattingAuxFunctions.assertTwoUsersAndChat(user1Id, user2Id, chatId) match
        case Left(error)  => Left(error)
        case Right(tuple) => tuple match
          case Right(user1, user2, chat) =>
            ChattingAuxFunctions.checkIfUsersBlocked(user1, user2) match
              case Left(error) => Left(error)
              case Right(())   =>
                ChattingAuxFunctions.checkUserChatsAndUpdate(user1, chat, message)
                ChattingAuxFunctions.findSecondUserChatAndUpdate(user1Id, chatId, user2, message)
                MessageRepository.put(message.id, message)
                Right(message)

          case Left(user1, user2) =>
            ChattingAuxFunctions.checkIfUsersBlocked(user1, user2) match
              case Left(error) => Left(error)
              case Right(())   =>
                ChattingAuxFunctions.newChatAndUpdateUserWithMessage(user1, user2Id, chatId, message)
                ChattingAuxFunctions.findSecondUserChatAndUpdate(user1Id, chatId, user2, message)
                MessageRepository.put(message.id, message)
                Right(message)

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val deleteMessage: ((UserId, ChatId, MessageId)) => IO[Either[UserError, Unit]] =
    (userId, chatId, messageId) => IO.pure {
      ChattingAuxFunctions.assertUserAndChatAndMessageIds(userId, chatId, messageId) match
        case Left(error)    => Left(error)
        case Right(_, _, _) =>
          MessageRepository.delete(messageId) 
          Right(())
          
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }
}
