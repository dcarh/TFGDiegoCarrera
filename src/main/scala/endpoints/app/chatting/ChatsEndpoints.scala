package endpoints.app.chatting

import sttp.tapir.*
import endpoints.EndpointsUtils.appBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.ErrorInfo
import modelClasses.app.chatting.Chat
import modelClasses.ids.Chatting.ChatId

object ChatsEndpoints {

  private val chatBaseEndpoint:
    (String, String, String) => PublicEndpoint[Unit, ErrorInfo, Unit, Any] =
      (name, description, method) => appBaseEndpoint(name, description, "chat", method)

  val specificChatEndpoint: PublicEndpoint[ChatId, ErrorInfo, Chat, Any] =
    chatBaseEndpoint(
      "Specific chat endpoint", 
      "This endpoint returns a specific chat by its Id",
      "GET"
    )
      .in(PathInputs.pathChatId)
      .out(ChattingOutputs.chatSuccess)

}
