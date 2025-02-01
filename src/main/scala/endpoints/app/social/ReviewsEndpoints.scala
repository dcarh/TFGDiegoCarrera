package endpoints.app.social

import sttp.tapir.*
import endpoints.EndpointsUtils.httpMethodEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.social.Review
import modelClasses.ids.Social.ReviewId

object ReviewsEndpoints {
  
  private val reviewsBaseEndpoint:
    (String, String, String) => PublicEndpoint[Unit, UserError, Unit, Any] =
      (name, description, method) => httpMethodEndpoint(name, description, "reviews", method)

  private val reviewBaseEndpoint:
    (String, String, String) => PublicEndpoint[Unit, UserError, Unit, Any] =
      (name, description, method) =>
        httpMethodEndpoint(name, description, "review", method)
  
  val getAllReviews: PublicEndpoint[(Option[String], Option[List[String]]), UserError, List[Review], Any] =
    reviewsBaseEndpoint(
      "Get reviews endpoint",
      "This endpoint returns a list with all the reviews in the app",
      "GET"
    )
      .in(QueryInputs.querySortBy)
      .in(QueryInputs.queryCategories)
      .out(SocialOutputs.listOfReviewsOutput)

  val getReview: PublicEndpoint[ReviewId, UserError, Review, Any] =
    reviewBaseEndpoint(
      "Get review endpoint",
      "This endpoint returns a specific review by its Id",
      "GET"
    )
      .in(PathInputs.pathReviewId)
      .out(SocialOutputs.reviewOutput)

  val createReview: PublicEndpoint[Review, UserError, Review, Any] =
    reviewBaseEndpoint(
      "Create review endpoint",
      "This endpoint creates a review and returns it in case of success",
      "POST"
    )
      .in("create")
      .in(JsonInputs.jsonReview)
      .out(SocialOutputs.reviewOutput)

  val editReview: PublicEndpoint[(ReviewId, Review), UserError, Review, Any] =
    reviewBaseEndpoint(
      "Edit review endpoint",
      "This endpoint allows to edit a review and returns it in case of success. Otherwise returns an error message",
      "PUT"
    )
      .in(PathInputs.pathReviewId)
      .in("edit")
      .in(JsonInputs.jsonReview)
      .out(SocialOutputs.reviewOutput)

  val deleteReview: PublicEndpoint[ReviewId, UserError, Unit, Any] =
    reviewBaseEndpoint(
      "Delete review endpoint",
      "This endpoint deletes a review  returns it in case of success",
      "DELETE"
    )
      .in(PathInputs.pathReviewId)
      .in("delete")

}
