package modelClasses

import sttp.tapir.generic.auto._

case class Message(
                  id     : Message.Id,
                  userId : User.Id,
                  message: String,
                  date   : String     // TODO: ¿String o Date?
                  )

object Message {
  type Id = Long
}
