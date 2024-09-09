package modelClasses.app.chatting

import upickle.default.*
import modelClasses.ids.Chatting.MessageId
import modelClasses.ids.User.UserId

case class Message(
                  id     : MessageId,
                  userId : UserId,
                  message: String,
                  date   : String     // TODO: ¿String o Date?
                  ) derives ReadWriter

// object Message {
//   type Id = Long
// }
