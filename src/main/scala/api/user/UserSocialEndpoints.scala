package api.user

import sttp.tapir._

import modelClasses.user.User
import modelClasses.chatting.Chat
import api.common.Inputs.inputs
import api.common.Outputs.outputs

class UserSocialEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val usersBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "users")

  private val userBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "user")

  val userFollowerList: PublicEndpoint[String, Unit, List[User], Any] =
    userBaseEndpoint
      .name("User's followers endpoint")
      .description("This endpoint returns the followers of the user")
      .get
      .in(inputs.pathUsername)
      .in("followers")
      .out(outputs.jsonUserListOut)

  val userFollowingList: PublicEndpoint[String, Unit, List[User], Any] =
    userBaseEndpoint
      .name("User's people following endpoint")
      .description("This endpoint returns the people followed by the user")
      .get
      .in(inputs.pathUsername)
      .in("following")
      .out(outputs.jsonUserListOut)

  val userChatList: PublicEndpoint[String, Unit, List[Chat], Any] =
    userBaseEndpoint
      .name("User's chats endpoint")
      .description("This endpoint returns the chats of the user")
      .get
      .in(inputs.pathUsername)
      .in("chats")
      .out(outputs.jsonChatListOut)

  val userChat: PublicEndpoint[String, Unit, Chat, Any] =
    userBaseEndpoint
      .name("User's specific chat endpoint")
      .description("This endpoint returns a specific chat of the user by the ID of the chat")
      .get
      .in(inputs.pathUsername)
      .in("chat")
      .out(outputs.jsonChatOut)

  val userChatWithOtherUser: PublicEndpoint[String, Unit, Chat, Any] =
    userBaseEndpoint
      .name("User's chat with other user endpoint")
      .description("This endpoint returns a the chat between the user and another specific user")
      .get
      .in(inputs.pathUsername)
      .in("chat")
      .out(outputs.jsonChatOut)

}
