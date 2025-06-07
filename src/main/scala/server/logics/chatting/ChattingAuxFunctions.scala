package server.logics.chatting

import memory.repositories.ChatRepository
import memory.repositories.UserRepository
import domain.app.chatting.Chat
import domain.app.chatting.Message
import domain.app.user.User
import domain.errors.UserError.*
import domain.ids.Chatting.ChatId
import domain.ids.Chatting.MessageId
import domain.ids.User.UserId
import server.logics.commonFunctions.CommonFunctions

object ChattingAuxFunctions {

  val assertUserAndChatIds: ((UserId, ChatId)) => Either[UserError, (User, Chat)] =
    (userId, chatId) =>
      CommonFunctions.getUser(userId) match
        case Left(error) => Left(error)
        case Right(user) =>
          CommonFunctions.getChat(chatId) match
            case Left(error) => Left(error)
            case Right(chat) =>
              if userId.value != chat.user1Id.value then
                Left(BadRequest("User ID introduced and user ID stored in the chat did not match"))
              else if !user.chatsIds.contains(chatId) && !user.archivedChatsIds.contains(chatId) then
                Left(BadRequest("Chat ID introduced and chat ID stored in the user did not match"))
              else
                Right((user, chat))
  

  val assertUserAndChatAndMessageIds: ((UserId, ChatId, MessageId)) => Either[UserError, (User, Chat, Message)] =
    (userId, chatId, messageId) =>
      assertUserAndChatIds(userId, chatId) match
        case Left(error)       => Left(error)
        case Right(user, chat) =>
          CommonFunctions.getMessage(messageId) match
            case Left(error)    => Left(error)
            case Right(message) =>
              if !chat.messagesIds.contains(messageId) then
                Left(BadRequest("The message ID introduced wasn't stored in the chat specified by the chat ID"))
              else
                Right((user, chat, message))


  val assertTwoUsersAndChat: ((UserId, UserId, ChatId)) => Either[UserError, Either[(User, User), (User, User, Chat)]] =
    (user1Id, user2Id, chatId) =>
      CommonFunctions.getBothUsers(user1Id, user2Id) match
        case Left(error)         => Left(error)
        case Right(user1, user2) =>
          CommonFunctions.getChat(chatId) match
            case Left(NotFound(_)) => Right(Left(user1, user2))
            case Left(error)       => Left(error)
            case Right(chat)       =>
              if chat.user1Id == user1.id && chat.user2Id == user2.id then
                Right(Right(user1, user2, chat))
              else
                Left(BadRequest("Users IDs introduced didn't match with IDs stored by the chat specified"))
                
  
  val newChatAndUpdateUserWithMessage: ((User, UserId, ChatId, Message)) => Unit =
    (user, user2Id, chatId, message) =>
      val newChat = Chat(
        id           = chatId,
        user1Id      = user.id,
        user2Id      = user2Id,
        messagesIds  = List(message.id),
        creationDate = message.creationDate,
        archived     = false
      )
      val updatedUser = user.copy(
        chatsIds = chatId :: user.chatsIds
      )
      ChatRepository.put(chatId, newChat)
      UserRepository.put(user.id, updatedUser)

  val checkUserChatsAndUpdate: ((User, Chat, Message)) => Unit =
    (user, chat, message) =>
      if user.chatsIds.contains(chat.id) && !user.archivedChatsIds.contains(chat.id) then
        val updatedChat = chat.copy(
          messagesIds = message.id :: chat.messagesIds,
          archived    = false
        )
        ChatRepository.put(chat.id, updatedChat)

      else if !user.chatsIds.contains(chat.id) && user.archivedChatsIds.contains(chat.id) then
        val updatedChat = chat.copy(
          messagesIds = message.id :: chat.messagesIds,
          archived    = false
        )
        val updatedUser = user.copy(
          chatsIds         = chat.id :: user.chatsIds,
          archivedChatsIds = user.archivedChatsIds.filterNot(_ == chat.id)
        )
        ChatRepository.put(chat.id, updatedChat)
        UserRepository.put(user.id, updatedUser)
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
      if user1.blockedIds.contains(user2.id) then
        Left(Conflict(s"User with ID ${user2.id.value} is blocked by ${user1.id.value}"))
      else if user2.blockedIds.contains(user1.id) then
        Left(Conflict(s"User with ID ${user1.id.value} is blocked by ${user2.id.value}"))
      else
        Right(())
}
