package dummies.objects

import dummies.ids.{ChatIdDummies, MessageIdDummies, UserIdDummies}
import modelClasses.app.chatting.Chat

import java.time.LocalDateTime

object ChatDummies {
  val chat1: Chat = Chat(
    ChatIdDummies.chatId1, 
    UserIdDummies.userId2, 
    UserIdDummies.userId5, 
    List(
      MessageIdDummies.messageId1, 
      MessageIdDummies.messageId4, 
      MessageIdDummies.messageId5
    ),
    LocalDateTime.of(2024, 11, 6, 18, 27, 4),
    false
  )
  
  val chat2: Chat = Chat(
    ChatIdDummies.chatId2, 
    UserIdDummies.userId5, 
    UserIdDummies.userId2, 
    List(
      MessageIdDummies.messageId1, 
      MessageIdDummies.messageId4, 
      MessageIdDummies.messageId5
    ),
    LocalDateTime.of(2024, 11, 6, 18, 27, 4),
    false
  )
  
  val chat3: Chat = Chat(
    ChatIdDummies.chatId3, 
    UserIdDummies.userId1, 
    UserIdDummies.userId4, 
    List(
      MessageIdDummies.messageId2, 
      MessageIdDummies.messageId3
    ),
    LocalDateTime.of(2024, 11, 7, 15, 42, 3),
    false
  )
  
  val chat4: Chat = Chat(
    ChatIdDummies.chatId4, 
    UserIdDummies.userId4, 
    UserIdDummies.userId1, 
    List(
      MessageIdDummies.messageId2, 
      MessageIdDummies.messageId3
    ),
    LocalDateTime.of(2024, 11, 7, 15, 42, 3),
    false
  )
  
  val chat5: Chat = Chat(
    ChatIdDummies.chatId5, 
    UserIdDummies.userId4, 
    UserIdDummies.userId2, 
    List(
      MessageIdDummies.messageId6, 
      MessageIdDummies.messageId7, 
      MessageIdDummies.messageId8, 
      MessageIdDummies.messageId9
    ),
    LocalDateTime.of(2024, 12, 12, 16, 56, 2),
    false
  )
  
  val chat6: Chat = Chat(
    ChatIdDummies.chatId6, 
    UserIdDummies.userId2, 
    UserIdDummies.userId4, 
    List(
      MessageIdDummies.messageId6, 
      MessageIdDummies.messageId7, 
      MessageIdDummies.messageId8, 
      MessageIdDummies.messageId9
    ),
    LocalDateTime.of(2024, 12, 12, 16, 56, 2),
    true
  )
}