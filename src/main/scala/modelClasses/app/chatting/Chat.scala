package modelClasses.app.chatting

import modelClasses.ids.Chatting.{ChatId, MessageId}
import modelClasses.ids.User.UserId

case class Chat(
               id         : ChatId,
               user1Id    : UserId,
               user2Id    : UserId,
               messagesIds: List[MessageId]
               )

// object  Chat {
//   type Id = Long
// }

