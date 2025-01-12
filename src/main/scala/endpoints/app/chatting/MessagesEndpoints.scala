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

  val getMessage: PublicEndpoint[MessageId, UserError, Message, Any] =
    messageBaseEndpoint(
      "Get message endpoint", 
      "This endpoint returns a specific message by its Id",
      "GET"
    )
      .in(PathInputs.pathMessageId)
      .out(ChattingOutputs.messageOutput)

  val createMessage: PublicEndpoint[Message, UserError, Message, Any] =
    messageBaseEndpoint(
      "Create message endpoint", 
      "This endpoint creates a specific message and returns it",
      "POST"
    )
      .in("create")
      .in(JsonInputs.jsonMessage)
      .out(ChattingOutputs.messageOutput)

  val editMessage: PublicEndpoint[(MessageId, Message), UserError, Message, Any] =
    messageBaseEndpoint(
      "Edit message endpoint",
      "This endpoint edits a specific message and returns it",
      "PUT"
    )
      .in(PathInputs.pathMessageId)
      .in("edit")
      .in(JsonInputs.jsonMessage)
      .out(ChattingOutputs.messageOutput)

  val deleteMessage: PublicEndpoint[MessageId, UserError, Unit, Any] =
    messageBaseEndpoint(
      "Delete message endpoint", 
      "This endpoint deletes a specific message by its Id",
      "DELETE"
    )
      .in(PathInputs.pathMessageId)
      .in("delete")

}
