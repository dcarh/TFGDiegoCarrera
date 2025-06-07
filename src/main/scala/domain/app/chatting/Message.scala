package domain.app.chatting

import domain.ids.Chatting.MessageId
import domain.ids.User.UserId

import java.time.LocalDateTime

case class Message(
                  id          : MessageId,
                  userId      : UserId,
                  message     : String,
                  creationDate: LocalDateTime
                  )
