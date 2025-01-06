package endpoints.app.user.social

import endpoints.app.user.UserEndpointsUtils.userBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.ids.Social.RatingId
import modelClasses.ids.User.UserId
import sttp.tapir.*

object UserRatingsEndpoints {

  private val userRatingsBaseEndpoint:
    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
    (name, description, method) => userBaseEndpoint(name, description, method)
      .in(PathInputs.pathUserId)
      .in("ratings")

  val getUserRatings: PublicEndpoint[UserId, UserError, List[RatingId], Any] =
    userRatingsBaseEndpoint(
      "User's ratings endpoint",
      "This endpoint returns a list of all the ratings of a user",
      "GET"
    )
      .out(SocialOutputs.listOfRatingsIdsOutput)
}
