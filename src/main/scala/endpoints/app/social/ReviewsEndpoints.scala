package endpoints.app

import sttp.tapir.*
import endpoints.EndpointsUtils.appBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.ErrorInfo
import modelClasses.app.social.Review
import modelClasses.ids.Social.ReviewId

object ReviewsEndpoints {
  
  private val reviewsBaseEndpoint:
    (String, String, String) => PublicEndpoint[Unit, ErrorInfo, Unit, Any] =
      (name, description, method) => appBaseEndpoint(name, description, "reviews", method)

  private val reviewBaseEndpoint:
    (String, String, String) => PublicEndpoint[Unit, ErrorInfo, Unit, Any] =
      (name, description, method) =>
        appBaseEndpoint(name, description, "review", method)
  
  val reviewsEndpoint: PublicEndpoint[Option[String], ErrorInfo, List[Review], Any] =
    reviewsBaseEndpoint(
      "Reviews endpoint",
      "This endpoint returns a list with all the reviews in the app",
      "GET"
    )
      .in(QueryInputs.querySortBy)
      .out(SocialOutputs.listOfReviewsSuccess)

  val specificReviewEndpoint: PublicEndpoint[ReviewId, ErrorInfo, Review, Any] =
    reviewBaseEndpoint(
      "Specific review endpoint",
      "This endpoint returns a specific review by its Id",
      "GET"
    )
      .in(PathInputs.pathReviewId)
      .out(SocialOutputs.reviewSuccess)

}
