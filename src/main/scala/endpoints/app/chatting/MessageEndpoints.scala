package endpoints.app.chatting

import sttp.tapir.*
import endpoints.EndpointsUtils.appBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.ErrorInfo
import modelClasses.app.chatting.Message
import modelClasses.ids.Chatting.MessageId

object MessageEndpoints {

  private val messageBaseEndpoint:
    (String, String, String) => PublicEndpoint[Unit, ErrorInfo, Unit, Any] =
      (name, description, method) => appBaseEndpoint(name, description, "message", method)

  val specificMessageEndpoint: PublicEndpoint[MessageId, ErrorInfo, Message, Any] =
    messageBaseEndpoint(
      "Specific message endpoint", 
      "This endpoint returns a specific message by its Id",
      "GET"
    )
      .in(PathInputs.pathMessageId)
      .out(ChattingOutputs.messageSuccess)

}
