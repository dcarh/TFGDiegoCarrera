package modelClasses.app.chatting

import modelClasses.ids.Chatting.MessageId
import modelClasses.ids.User.UserId

import java.time.LocalDateTime

case class Message(
                  id          : MessageId,
                  userId      : UserId,
                  message     : String,
                  creationDate: LocalDateTime
                  )
