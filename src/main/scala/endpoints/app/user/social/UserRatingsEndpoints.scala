package endpoints.app.user.social

import endpoints.app.user.UserEndpointsUtils.specificUserBaseEndpoint
import endpoints.io.outputs.Common.*
import domain.errors.UserError.*
import domain.ids.Social.RatingId
import domain.ids.User.UserId
import sttp.tapir.*

object UserRatingsEndpoints {

  private val userRatingsBaseEndpoint:
    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
    (name, description, method) => specificUserBaseEndpoint(name, description, method)
      .in("ratings")

  val getUserRatings: PublicEndpoint[UserId, UserError, List[RatingId], Any] =
    userRatingsBaseEndpoint(
      "getUserRatings",
      "This endpoint returns all the ratings of a user",
      "GET"
    )
      .out(SocialOutputs.listOfRatingsIdsOutput)
}
