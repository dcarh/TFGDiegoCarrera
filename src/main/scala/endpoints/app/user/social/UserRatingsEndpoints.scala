package endpoints.app.user.social

import endpoints.app.user.UserEndpointsUtils.specificUserBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.ids.Social.RatingId
import modelClasses.ids.User.UserId
import sttp.tapir.*

object UserRatingsEndpoints {

  private val userRatingsBaseEndpoint:
    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
    (name, description, method) => specificUserBaseEndpoint(name, description, method)
      .in("ratings")

  val getUserRatings: PublicEndpoint[(UserId, Option[List[String]], Option[String]), UserError, List[RatingId], Any] =
    userRatingsBaseEndpoint(
      "User's ratings endpoint",
      "This endpoint returns a list of all the ratings of a user",
      "GET"
    )
      .in(QueryInputs.queryCategories)
      .in(QueryInputs.querySortBy)
      .out(SocialOutputs.listOfRatingsIdsOutput)
}
