package endpoints.app.chatting

import sttp.tapir.*
import endpoints.EndpointsUtils.httpMethodEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.chatting.Message
import modelClasses.ids.Chatting.MessageId

object MessagesEndpoints {

  private val messageBaseEndpoint:
    (String, String, String) => PublicEndpoint[Unit, UserError, Unit, Any] =
      (name, description, method) => httpMethodEndpoint(name, description, "message", method)

  val getMessageEndpoint: PublicEndpoint[MessageId, UserError, Message, Any] =
    messageBaseEndpoint(
      "Get message endpoint", 
      "This endpoint returns a specific message by its Id",
      "GET"
    )
      .in(PathInputs.pathMessageId)
      .out(ChattingOutputs.messageSuccess)

}
