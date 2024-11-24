package endpoints.app.chatting

import sttp.tapir.*

import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.app.chatting.Message
import modelClasses.ids.Chatting.MessageId

object MessageEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]
  
  // private val messagesBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
  //   endpoint.in("api" / "messages")

  private val messageBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "message")
  
  // val messagesEndpoint: PublicEndpoint[Option[String], Unit, List[Message], Any] =
  //   messagesBaseEndpoint
  //     .name("Messages endpoint")
  //     .description("This endpoint returns a list with all the messages in the app")
  //     .get
  //     .in(QueryInputs.querySortBy)
  //     .out(ChattingOutputs.jsonMessageListOut)

  val specificMessageEndpoint: PublicEndpoint[MessageId, Unit, Message, Any] =
    messageBaseEndpoint
      .name("Specific message endpoint")
      .description("This endpoint returns a specific message by its Id")
      .get
      .in(PathInputs.pathMessageId)
      .out(ChattingOutputs.jsonMessageOut)

}
