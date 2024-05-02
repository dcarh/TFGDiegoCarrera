package modelClasses.chatting

import modelClasses.user.User

case class Chat(
               id         : Chat.Id,
               user1Id    : User.Id,
               user2Id    : User.Id,
               messagesIds: List[Message.Id]
               )
object  Chat {
  type Id = Long
}

