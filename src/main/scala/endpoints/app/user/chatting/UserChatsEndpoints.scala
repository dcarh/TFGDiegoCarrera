package endpoints.app.user.chatting

import sttp.tapir.*
import endpoints.app.user.UserEndpointsUtils.userBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.chatting.Chat
import modelClasses.ids.Chatting.ChatId
import modelClasses.ids.User.UserId

object UserChatsEndpoints {

//  private val userChatBaseEndpoint:
//    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
//      (name, description, method) => userBaseEndpoint(name, description, method)
//        .in(PathInputs.pathUserId)
//        .in("chat")

  private val userChatsBaseEndpoint:
    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
      (name, description, method) => userBaseEndpoint(name, description, method)
        .in(PathInputs.pathUserId)
        .in("chats")

  val getUserChats: PublicEndpoint[(UserId, Option[String]), UserError, List[Chat], Any] =
    userChatsBaseEndpoint(
      "User's chats endpoint",
      "This endpoint returns the chats of the user",
      "GET"
    )
      .in(QueryInputs.querySortBy)
      .out(ChattingOutputs.listOfChatsSuccess)

//  val userChat: PublicEndpoint[(UserId, ChatId), UserError, Chat, Any] =
//    userChatBaseEndpoint(
//      "User's specific chat endpoint",
//      "This endpoint returns a specific chat of the user by the ID of the chat",
//      "GET"
//    )
//      .in(PathInputs.pathChatId)
//      .out(ChattingOutputs.chatSuccess)
//
//  val userChatWithOtherUser: PublicEndpoint[(UserId, UserId), UserError, Chat, Any] =
//    userChatBaseEndpoint(
//      "User's chat with other user endpoint",
//      "This endpoint returns the chat between the user and another specific user",
//      "GET"
//    )
//      .in(PathInputs.pathUserId)
//      .out(ChattingOutputs.chatSuccess)

// TODO: Dudo que hagan falta estos dos endpoints. La lógica, según lo entiendo yo, de acceder a una chat del usuario, debería ser: 
  //  TODO: 1. Accedo a todos los chats del usuario. 
  //        2. Dados esos chats, accedo, según su ID al chat deseado.
  //        Por tanto, para lo segundo, ya tenemos el endpoint de chatting.chat

}
