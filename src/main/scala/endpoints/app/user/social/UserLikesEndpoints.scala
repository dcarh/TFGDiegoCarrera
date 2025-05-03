package endpoints.app.user.social

import sttp.tapir.*
import endpoints.app.user.UserEndpointsUtils.specificUserBaseEndpoint
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.ids.Social.LikeId
import modelClasses.ids.User.UserId

object UserLikesEndpoints {

  private val userLikesBaseEndpoint:
    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
      (name, description, method) => specificUserBaseEndpoint(name, description, method)
        .in("likes")
  
  val getUserLikes: PublicEndpoint[UserId, UserError, List[LikeId], Any] =
    userLikesBaseEndpoint(
      "getUserLikes",
      "This endpoint returns a list of all the likes for a user",
      "GET"
    )
      .out(SocialOutputs.listOfLikesIdsOutput)
}
