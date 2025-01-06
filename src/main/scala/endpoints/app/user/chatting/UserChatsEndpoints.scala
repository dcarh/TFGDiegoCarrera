package endpoints.app.user.chatting

import sttp.tapir.*
import endpoints.app.user.UserEndpointsUtils.userBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.ids.Chatting.ChatId
import modelClasses.ids.User.UserId

object UserChatsEndpoints {

  private val userChatsBaseEndpoint:
    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
      (name, description, method) => userBaseEndpoint(name, description, method)
        .in(PathInputs.pathUserId)
        .in("chats")

  val getUserChats: PublicEndpoint[(UserId, Option[String]), UserError, List[ChatId], Any] =
    userChatsBaseEndpoint(
      "User's chats endpoint",
      "This endpoint returns a list containing the IDs of the chats of the user",
      "GET"
    )
      .in(QueryInputs.querySortBy)
      .out(ChattingOutputs.listOfChatIdsOutput)

}
