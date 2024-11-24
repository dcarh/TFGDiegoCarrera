package endpoints.app.chatting

import sttp.tapir.*

import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.app.chatting.Chat
import modelClasses.ids.Chatting.ChatId

object ChatsEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]
  
  // private val chatsBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
  //   endpoint.in("api" / "chats")

  private val chatBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "chat")
  
  // val chatsEndpoint: PublicEndpoint[Option[String], Unit, List[Chat], Any] =
  //   chatsBaseEndpoint
  //     .name("Chats endpoint")
  //     .description("This endpoint returns a list with all the chats in the app")
  //     .get
  //     .in(QueryInputs.querySortBy)
  //     .out(SocialOutputs.jsonChatListOut)

  val specificChatEndpoint: PublicEndpoint[ChatId, Unit, Chat, Any] =
    chatBaseEndpoint
      .name("Specific chat endpoint")
      .description("This endpoint returns a specific chat by its Id")
      .get
      .in(PathInputs.pathChatId)
      .out(ChattingOutputs.jsonChatOut)

}
