package modelClasses.app.chatting

import upickle.default.*
import modelClasses.ids.Chatting.{ChatId, MessageId}
import modelClasses.ids.User.UserId

import upickle.default.ReadWriter.join

case class Chat(
               id         : ChatId,
               user1Id    : UserId,
               user2Id    : UserId,
               messagesIds: List[MessageId]
               ) derives ReadWriter

// object  Chat {
//   type Id = Long
// }

