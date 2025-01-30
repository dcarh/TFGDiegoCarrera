package server.logics.chatting

import cats.effect.IO
import dummies.repositories.ChatRepository
import dummies.repositories.MessageRepository
import dummies.repositories.UserRepository

import modelClasses.app.chatting.Chat
import modelClasses.app.chatting.Message
import modelClasses.app.user.User
import modelClasses.errors.UserError.*
import modelClasses.ids.Chatting.ChatId
import modelClasses.ids.Chatting.MessageId
import modelClasses.ids.User.UserId

object ChattingAuxFunctions {

  val assertUserAndChatIds: ((UserId, ChatId)) => Either[UserError, (User, Chat)] =
    (userId, chatId) =>
      UserRepository.get(userId) match
        case Some(user) =>
          ChatRepository.get(chatId) match
            case Some(chat) =>
              if userId.value != chat.user1Id.value then
                Left(BadRequest("User ID introduced and user ID stored in the chat did not match"))
              else if !user.chats.contains(chatId) && !user.archivedChats.contains(chatId) then
                Left(BadRequest("Chat ID introduced and chat ID stored in the user did not match"))
              else
                Right((user, chat))
            case None if chatId.value <= 0 =>
              Left(BadRequest("Invalid chat ID"))
            case None =>
              Left(NotFound(s"Chat with ID ${chatId.value} not found"))
        case None if userId.value <= 0 =>
          Left(BadRequest("Invalid user ID"))
        case None =>
          Left(NotFound(s"User with ID ${userId.value} not found"))

  val assertUserAndChatAndMessageIds: ((UserId, ChatId, MessageId)) => Either[UserError, (User, Chat, Message)] =
    (userId, chatId, messageId) =>
      assertUserAndChatIds(userId, chatId) match
        case Left(error) => Left(error)
        case Right(user, chat) =>
          MessageRepository.get(messageId) match
            case Some(message) =>
              if !chat.messagesIds.contains(messageId) then
                Left(BadRequest("The message ID introduced wasn't stored in the chat specified by the chat ID"))
              else
                Right((user, chat, message))
            case None if messageId.value <= 0 =>
              Left(BadRequest("Invalid message ID"))
            case None =>
              Left(NotFound(s"Message with ID ${messageId.value} not found"))

  val assertTwoUsersAndChat: ((UserId, UserId, ChatId)) => Either[UserError, Either[(User, User), (User, User, Chat)]] =
    (user1Id, user2Id, chatId) =>
      UserRepository.get(user1Id) match
        case Some(user1) =>
          UserRepository.get(user2Id) match
            case Some(user2) =>
              ChatRepository.get(chatId) match
                case Some(chat) =>
                  if chat.user1Id == user1.id && chat.user2Id == user2.id then
                    Right(Right(user1, user2, chat))
                  else
                    Left(BadRequest("Users IDs introduced didn't match with IDs stored by the chat specified"))

                case None if chatId.value <= 0 =>
                  Left(BadRequest("Invalid chat ID"))

                case None =>
                  Right(Left(user1, user2))

            case None if user2Id.value <= 0 =>
              Left(BadRequest("Invalid user ID"))

            case None =>
              Left(NotFound(s"User with ID ${user1Id.value} not found"))

        case None if user1Id.value <= 0 =>
          Left(BadRequest("Invalid user ID"))

        case None =>
          Left(NotFound(s"User with ID ${user1Id.value} not found"))


  val updateUserAndChatWithNewMessage: ((User, Chat, Message)) => Unit =
    (user, chat, message) =>
      val updatedChat = chat.copy(
        messagesIds = message.id :: chat.messagesIds,
        archived = false
      )
      val updatedUser = user.copy(
        chats = chat.id :: user.chats,
        archivedChats = user.archivedChats.filterNot(_ == chat.id)
      )
      ChatRepository.put(chat.id, updatedChat)
      UserRepository.put(user.id, updatedUser)

  
  val updateChatWithNewMessage: ((Chat, Message)) => Unit =
    (chat, message) =>
      val updatedChat = chat.copy(
        messagesIds = message.id :: chat.messagesIds,
        archived = false
      )
      ChatRepository.put(chat.id, updatedChat)
  
  val newChatAndUpdateUserWithMessage: ((User, UserId, ChatId, Message)) => Unit =
    (user, user2Id, chatId, message) =>
      val newChat = Chat(
        id = chatId,
        user1Id = user.id,
        user2Id = user2Id,
        messagesIds = List(message.id),
        creationDate = message.creationDate,
        archived = false
      )
      val updatedUser = user.copy(
        chats = chatId :: user.chats
      )
      ChatRepository.put(chatId, newChat)
      UserRepository.put(user.id, updatedUser)

  val checkUserChatsAndUpdate: ((User, Chat, Message)) => Unit =
    (user, chat, message) =>
      if user.chats.contains(chat.id) && !user.archivedChats.contains(chat.id) then
        ChattingAuxFunctions.updateChatWithNewMessage(chat, message)
      else if !user.chats.contains(chat.id) && user.archivedChats.contains(chat.id) then
        ChattingAuxFunctions.updateUserAndChatWithNewMessage(user, chat, message)
      else
        throw Exception("Internal server error")
        
  val findSecondUserChatAndUpdate: ((UserId, ChatId, User, Message)) => Unit =
    (user1Id, chat1Id, user2, message) =>
      ChatRepository.getAll.find(
        chat2 => (chat2.user1Id == user2.id) && (chat2.user2Id == user1Id)
      ) match
        case Some(chat2) =>
          ChattingAuxFunctions.checkUserChatsAndUpdate(user2, chat2, message)

        case None =>
          val chat2Id = chat1Id.copy(
            value = chat1Id.value + 1
          )
          ChattingAuxFunctions.newChatAndUpdateUserWithMessage(user2, user1Id, chat2Id, message)
          
  val checkIfUsersBlocked: ((User, User)) => Either[UserError, Unit] =
    (user1, user2) =>
      if user1.blocked.contains(user2.id) then
        Left(Conflict(s"User with ID ${user2.id.value} is blocked by ${user1.id.value}"))
      else if user2.blocked.contains(user1.id) then
        Left(Conflict(s"User with ID ${user1.id.value} is blocked by ${user2.id.value}"))
      else
        Right(())
}
