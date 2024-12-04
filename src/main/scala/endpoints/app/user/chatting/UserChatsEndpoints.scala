package endpoints.app.user.chatting

import sttp.tapir.*
import endpoints.EndpointsUtils.appBaseEndpoint
import endpoints.app.user.UserEndpointsUtils.userBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.ErrorInfo
import modelClasses.app.chatting.Chat
import modelClasses.ids.User.UserId

object UserChatsEndpoints {

  val userChatList: PublicEndpoint[UserId, ErrorInfo, List[Chat], Any] =
    userBaseEndpoint(
      "User's chats endpoint",
      "This endpoint returns the chats of the user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("chats")
      .out(ChattingOutputs.listOfChatsSuccess)

  val userChat: PublicEndpoint[UserId, ErrorInfo, Chat, Any] =
    userBaseEndpoint(
      "User's specific chat endpoint",
      "This endpoint returns a specific chat of the user by the ID of the chat",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("chat")
      .out(ChattingOutputs.chatSuccess)

  val userChatWithOtherUser: PublicEndpoint[UserId, ErrorInfo, Chat, Any] =
    userBaseEndpoint(
      "User's chat with other user endpoint",
      "This endpoint returns the chat between the user and another specific user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("chat")
      .out(ChattingOutputs.chatSuccess)

}
