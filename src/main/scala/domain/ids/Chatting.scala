package domain.ids

import io.circe.generic.auto.*

object Chatting {

    case class ChatId(value: Long)
    case class MessageId(value: Long)

}
