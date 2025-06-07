package domain.app.chatting

import domain.ids.Chatting.{ChatId, MessageId}
import domain.ids.User.UserId

import java.time.LocalDateTime

case class Chat(
               id          : ChatId,
               user1Id     : UserId,
               user2Id     : UserId,
               messagesIds : List[MessageId],
               creationDate: LocalDateTime,
               archived    : Boolean
               )
