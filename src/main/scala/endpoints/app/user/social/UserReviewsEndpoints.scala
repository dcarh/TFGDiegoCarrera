package endpoints.app.user.social

import endpoints.app.user.UserEndpointsUtils.specificUserBaseEndpoint
import sttp.tapir.*
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.ids.Social.ReviewId
import modelClasses.ids.User.UserId

object UserReviewsEndpoints {

  private val userReviewsBaseEndpoint:
    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
    (name, description, method) => specificUserBaseEndpoint(name, description, method)
      .in("reviews")

  val getUserReviews: PublicEndpoint[UserId, UserError, List[ReviewId], Any] =
    userReviewsBaseEndpoint(
      "User's reviews endpoint",
      "This endpoint returns a list of all the reviews written by a user",
      "GET"
    )
      .out(SocialOutputs.listOfReviewsIdsOutput)
}
