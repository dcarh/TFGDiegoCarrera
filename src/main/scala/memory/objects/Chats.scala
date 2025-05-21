package memory.objects

import memory.ids.{ChatIds, MessageIds, UserIds}
import modelClasses.app.chatting.Chat

import java.time.LocalDateTime

object Chats {
  val chat1: Chat = Chat(
    ChatIds.chatId1, 
    UserIds.userId2, 
    UserIds.userId5, 
    List(
      MessageIds.messageId1, 
      MessageIds.messageId4, 
      MessageIds.messageId5
    ),
    LocalDateTime.of(2024, 11, 6, 18, 27, 4),
    false
  )
  
  val chat2: Chat = Chat(
    ChatIds.chatId2, 
    UserIds.userId5, 
    UserIds.userId2, 
    List(
      MessageIds.messageId1, 
      MessageIds.messageId4, 
      MessageIds.messageId5
    ),
    LocalDateTime.of(2024, 11, 6, 18, 27, 4),
    false
  )
  
  val chat3: Chat = Chat(
    ChatIds.chatId3, 
    UserIds.userId1, 
    UserIds.userId4, 
    List(
      MessageIds.messageId2, 
      MessageIds.messageId3
    ),
    LocalDateTime.of(2024, 11, 7, 15, 42, 3),
    false
  )
  
  val chat4: Chat = Chat(
    ChatIds.chatId4, 
    UserIds.userId4, 
    UserIds.userId1, 
    List(
      MessageIds.messageId2, 
      MessageIds.messageId3
    ),
    LocalDateTime.of(2024, 11, 7, 15, 42, 3),
    false
  )
  
  val chat5: Chat = Chat(
    ChatIds.chatId5, 
    UserIds.userId4, 
    UserIds.userId2, 
    List(
      MessageIds.messageId6, 
      MessageIds.messageId7, 
      MessageIds.messageId8, 
      MessageIds.messageId9
    ),
    LocalDateTime.of(2024, 12, 12, 16, 56, 2),
    false
  )
  
  val chat6: Chat = Chat(
    ChatIds.chatId6, 
    UserIds.userId2, 
    UserIds.userId4, 
    List(
      MessageIds.messageId6, 
      MessageIds.messageId7, 
      MessageIds.messageId8, 
      MessageIds.messageId9
    ),
    LocalDateTime.of(2024, 12, 12, 16, 56, 2),
    true
  )
}