package modelClasses.dummies.objects

import modelClasses.app.chatting.Message

import modelClasses.dummies.ids.{MessageIdDummies, UserIdDummies}

object MessageDummies {
  val message1: Message = Message(
    MessageIdDummies.messageId1,
    UserIdDummies.userId2,
    "Hey, I've noticed you've been watching The Bear recently. Is it worth the watch?",
    "06-11-2024 18:27:04"
  )
  
  val message2: Message = Message(
    MessageIdDummies.messageId2,
    UserIdDummies.userId1,
    "This is a dummy chat",
    "07-11-2024 15:42:03"
  )
  
  val message3: Message = Message(
    MessageIdDummies.messageId3,
    UserIdDummies.userId4,
    "Ok.",
    "07-11-2024 09:20:38"
  )
  
  val message4: Message = Message(
    MessageIdDummies.messageId4,
    UserIdDummies.userId5,
    "Yeah man, completely",
    "08-11-2024 20:11:47"
  )
  
  val message5: Message = Message(
    MessageIdDummies.messageId5,
    UserIdDummies.userId2,
    "Glad to hear that, I'll give it a try",
    "08-11-2024 20:51:39"
  )
  
  val message6: Message = Message(
    MessageIdDummies.messageId6,
    UserIdDummies.userId4,
    "Hi! Wanna go to the cinema to watch the new Avatar movie?",
    "12-12-2024 16:56:02"
  )
  
  val message7: Message = Message(
    MessageIdDummies.messageId7,
    UserIdDummies.userId2,
    "Sure, when is it ok for you?",
    "12-12-2024 16:59:51"
  )
  
  val message8: Message = Message(
    MessageIdDummies.messageId8,
    UserIdDummies.userId4,
    "Probably this saturday evening",
    "12-12-2024 17:08:23"
  )
  
  val message9: Message = Message(
    MessageIdDummies.messageId9,
    UserIdDummies.userId2,
    "Nice, I'll buy tickets then",
    "12-12-2024 17:13:53"
  )
}