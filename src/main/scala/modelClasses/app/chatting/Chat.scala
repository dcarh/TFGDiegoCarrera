package modelClasses.app.chatting

import modelClasses.ids.Chatting.{ChatId, MessageId}
import modelClasses.ids.User.UserId

import java.time.LocalDateTime

case class Chat(
               id          : ChatId,
               user1Id     : UserId,
               user2Id     : UserId,
               messagesIds : List[MessageId],
               creationDate: LocalDateTime,
               archived    : Boolean
               )
