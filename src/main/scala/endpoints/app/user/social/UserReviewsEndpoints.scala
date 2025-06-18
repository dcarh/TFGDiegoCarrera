package endpoints.app.user.social

import domain.app.social.Review
import domain.errors.UserError.*
import domain.ids.User.UserId
import endpoints.app.user.UserEndpointsUtils.specificUserBaseEndpoint
import endpoints.io.outputs.Common.*
import sttp.tapir.*

object UserReviewsEndpoints {

  private val userReviewsBaseEndpoint:
    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
    (name, description, method) => specificUserBaseEndpoint(name, description, method)
      .in("reviews")

  val getUserReviews: PublicEndpoint[UserId, UserError, List[Review], Any] =
    userReviewsBaseEndpoint(
      "getUserReviews",
      "This endpoint returns all the reviews written by a user",
      "GET"
    )
      .out(SocialOutputs.listOfReviewsOutput)
}
