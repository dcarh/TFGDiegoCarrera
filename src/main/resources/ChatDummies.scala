import modelClasses.app.chatting.Chat

import modelClasses.ids.Chatting.{ChatId, MessageId}
import modelClasses.ids.User.UserId

object ChatDummies {
  val chat1: Chat = new Chat(1, 2, 5, [1, 2, 3])
  val chat2: Chat = new Chat()
  val chat3: Chat = new Chat()
  val chat4: Chat = new Chat()
  val chat5: Chat = new Chat()

}