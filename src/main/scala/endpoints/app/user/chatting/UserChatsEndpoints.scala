package endpoints.app.user.chatting

import sttp.tapir.*

import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.app.chatting.Chat
import modelClasses.ids.User.UserId

object UserChatsEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val usersBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "users")

  private val userBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "user")

  val userChatList: PublicEndpoint[UserId, Unit, List[Chat], Any] =
    userBaseEndpoint
      .name("User's chats endpoint")
      .description("This endpoint returns the chats of the user")
      .get
      .in(PathInputs.pathUserId)
      .in("chats")
      .out(ChattingOutputs.jsonChatListOut)

  val userChat: PublicEndpoint[UserId, Unit, Chat, Any] =
    userBaseEndpoint
      .name("User's specific chat endpoint")
      .description("This endpoint returns a specific chat of the user by the ID of the chat")
      .get
      .in(PathInputs.pathUserId)
      .in("chat")
      .out(ChattingOutputs.jsonChatOut)

  val userChatWithOtherUser: PublicEndpoint[UserId, Unit, Chat, Any] =
    userBaseEndpoint
      .name("User's chat with other user endpoint")
      .description("This endpoint returns the chat between the user and another specific user")
      .get
      .in(PathInputs.pathUserId)
      .in("chat")
      .out(ChattingOutputs.jsonChatOut)

}
