package endpoints.app.user.social

import domain.app.social.Like
import domain.errors.UserError.*
import domain.ids.User.UserId
import endpoints.app.user.UserEndpointsUtils.specificUserBaseEndpoint
import endpoints.io.outputs.Common.*
import sttp.tapir.*

object UserLikesEndpoints {

  private val userLikesBaseEndpoint:
    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
      (name, description, method) => specificUserBaseEndpoint(name, description, method)
        .in("likes")
  
  val getUserLikes: PublicEndpoint[UserId, UserError, List[Like], Any] =
    userLikesBaseEndpoint(
      "getUserLikes",
      "This endpoint returns all the likes of a user",
      "GET"
    )
      .out(SocialOutputs.listOfLikesOutput)
}
