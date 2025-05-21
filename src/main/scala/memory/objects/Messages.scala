package memory.objects

import memory.ids.{MessageIds, UserIds}
import modelClasses.app.chatting.Message

import java.time.LocalDateTime

object Messages {
  val message1: Message = Message(
    MessageIds.messageId1,
    UserIds.userId2,
    "Hey, I've noticed you've been watching The Bear recently. Is it worth the watch?",
    LocalDateTime.of(2024, 11, 6, 18, 27, 4)
  )
  
  val message2: Message = Message(
    MessageIds.messageId2,
    UserIds.userId1,
    "This is a dummy chat",
    LocalDateTime.of(2024, 11, 7, 15, 42, 3)
  )
  
  val message3: Message = Message(
    MessageIds.messageId3,
    UserIds.userId4,
    "Ok.",
    LocalDateTime.of(2024, 11, 7, 9, 20, 38)
  )
  
  val message4: Message = Message(
    MessageIds.messageId4,
    UserIds.userId5,
    "Yeah man, completely",
    LocalDateTime.of(2024, 11, 8, 20, 11, 47)
  )
  
  val message5: Message = Message(
    MessageIds.messageId5,
    UserIds.userId2,
    "Glad to hear that, I'll give it a try",
    LocalDateTime.of(2024, 11, 8, 20, 51, 39)
  )
  
  val message6: Message = Message(
    MessageIds.messageId6,
    UserIds.userId4,
    "Hi! Wanna go to the cinema to watch the new Avatar movie?",
    LocalDateTime.of(2024, 12, 12, 16, 56, 2)
  )
  
  val message7: Message = Message(
    MessageIds.messageId7,
    UserIds.userId2,
    "Sure, when is it ok for you?",
    LocalDateTime.of(2024, 12, 12, 16, 59, 51)
  )
  
  val message8: Message = Message(
    MessageIds.messageId8,
    UserIds.userId4,
    "Probably this saturday evening",
    LocalDateTime.of(2024, 12, 12, 17 ,8, 23)
  )
  
  val message9: Message = Message(
    MessageIds.messageId9,
    UserIds.userId2,
    "Nice, I'll buy tickets then",
    LocalDateTime.of(2024, 12, 12, 17, 13, 53)
  )
}