package modelClasses.dummies

import modelClasses.app.chatting.Chat

import modelClasses.ids.Chatting.{ChatId, MessageId}
import modelClasses.ids.User.UserId

import modelClasses.dummies.ids.{ChatIdDummies, MessageIdDummies, UserIdDummies}

object ChatDummies {
  val chat1: Chat = Chat(
    ChatIdDummies.chatId1, 
    UserIdDummies.userId2, 
    UserIdDummies.userId5, 
    List(
      MessageIdDummies.messageId1, 
      MessageIdDummies.messageId4, 
      MessageIdDummies.messageId5
    )
  )
  
  val chat2: Chat = Chat(
    ChatIdDummies.chatId2, 
    UserIdDummies.userId1, 
    UserIdDummies.userId4, 
    List(
      MessageIdDummies.messageId2, 
      MessageIdDummies.messageId3
    )
  )
  
  val chat3: Chat = Chat(
    ChatIdDummies.chatId3, 
    UserIdDummies.userId4, 
    UserIdDummies.userId2, 
    List(
      MessageIdDummies.messageId6, 
      MessageIdDummies.messageId7, 
      MessageIdDummies.messageId8, 
      MessageIdDummies.messageId9
    )
  )
}