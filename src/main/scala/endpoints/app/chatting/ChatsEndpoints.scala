package endpoints.app.chatting

import sttp.tapir.*
import endpoints.EndpointsUtils.httpMethodEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.chatting.Chat
import modelClasses.ids.Chatting.ChatId

object ChatsEndpoints {

  private val chatBaseEndpoint:
    (String, String, String) => PublicEndpoint[Unit, UserError, Unit, Any] =
      (name, description, method) => httpMethodEndpoint(name, description, "chat", method)

  val getChatEndpoint: PublicEndpoint[ChatId, UserError, Chat, Any] =
    chatBaseEndpoint(
      "Get chat endpoint", 
      "This endpoint returns a specific chat by its Id",
      "GET"
    )
      .in(PathInputs.pathChatId)
      .out(ChattingOutputs.chatSuccess)

}
