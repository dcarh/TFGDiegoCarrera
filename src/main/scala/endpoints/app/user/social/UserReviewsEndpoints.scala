package endpoints.app.user.social

import endpoints.app.user.UserEndpointsUtils.specificUserBaseEndpoint
import sttp.tapir.*
import endpoints.io.outputs.Common.*
import domain.errors.UserError.*
import domain.ids.Social.ReviewId
import domain.ids.User.UserId

object UserReviewsEndpoints {

  private val userReviewsBaseEndpoint:
    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
    (name, description, method) => specificUserBaseEndpoint(name, description, method)
      .in("reviews")

  val getUserReviews: PublicEndpoint[UserId, UserError, List[ReviewId], Any] =
    userReviewsBaseEndpoint(
      "getUserReviews",
      "This endpoint returns all the reviews written by a user",
      "GET"
    )
      .out(SocialOutputs.listOfReviewsIdsOutput)
}
