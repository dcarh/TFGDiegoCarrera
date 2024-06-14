package modelClasses.app.chatting

import modelClasses.app.user.User

case class Message(
                  id     : Message.Id,
                  userId : User.Id,
                  message: String,
                  date   : String     // TODO: ¿String o Date?
                  )

object Message {
  type Id = Long
}
