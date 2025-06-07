package endpoints.app.user.social

import sttp.tapir.*
import endpoints.app.user.UserEndpointsUtils.specificUserBaseEndpoint
import endpoints.outputs.Common.*
import domain.errors.UserError.*
import domain.ids.Social.ReplyId
import domain.ids.User.UserId

object UserRepliesEndpoints {

  private val userRepliesBaseEndpoint:
    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
    (name, description, method) => specificUserBaseEndpoint(name, description, method)
      .in("replies")

  val getUserReplies: PublicEndpoint[UserId, UserError, List[ReplyId], Any] =
    userRepliesBaseEndpoint(
      "getUserReplies",
      "This endpoint returns all the replies made by a user",
      "GET"
    )
      .out(SocialOutputs.listOfRepliesIdsOutput)
}
