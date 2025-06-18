package endpoints.app.user.social

import domain.app.social.Reply
import domain.errors.UserError.*
import domain.ids.User.UserId
import endpoints.app.user.UserEndpointsUtils.specificUserBaseEndpoint
import endpoints.io.outputs.Common.*
import sttp.tapir.*

object UserRepliesEndpoints {

  private val userRepliesBaseEndpoint:
    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
    (name, description, method) => specificUserBaseEndpoint(name, description, method)
      .in("replies")

  val getUserReplies: PublicEndpoint[UserId, UserError, List[Reply], Any] =
    userRepliesBaseEndpoint(
      "getUserReplies",
      "This endpoint returns all the replies made by a user",
      "GET"
    )
      .out(SocialOutputs.listOfRepliesOutput)
}
