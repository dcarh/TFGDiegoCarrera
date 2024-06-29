package endpoints.app.user

import sttp.tapir._

import endpoints.inputs.Common._
import endpoints.outputs.Common._
import modelClasses.app.chatting.Chat
import modelClasses.app.user.User

object UserSocialEndpoints {

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
      .in(PathInputs.pathUsername)
      .in("followers")
      .out(UserOutputs.jsonUserListOut)

  val userFollowingList: PublicEndpoint[String, Unit, List[User], Any] =
    userBaseEndpoint
      .name("User's following endpoint")
      .description("This endpoint returns the people followed by the user")
      .get
      .in(PathInputs.pathUsername)
      .in("following")
      .out(UserOutputs.jsonUserListOut)

  val userChatList: PublicEndpoint[String, Unit, List[Chat], Any] =
    userBaseEndpoint
      .name("User's chats endpoint")
      .description("This endpoint returns the chats of the user")
      .get
      .in(PathInputs.pathUsername)
      .in("chats")
      .out(SocialOutputs.jsonChatListOut)

  val userChat: PublicEndpoint[String, Unit, Chat, Any] =
    userBaseEndpoint
      .name("User's specific chat endpoint")
      .description("This endpoint returns a specific chat of the user by the ID of the chat")
      .get
      .in(PathInputs.pathUsername)
      .in("chat")
      .out(SocialOutputs.jsonChatOut)

  val userChatWithOtherUser: PublicEndpoint[String, Unit, Chat, Any] =
    userBaseEndpoint
      .name("User's chat with other user endpoint")
      .description("This endpoint returns the chat between the user and another specific user")
      .get
      .in(PathInputs.pathUsername)
      .in("chat")
      .out(SocialOutputs.jsonChatOut)

}
