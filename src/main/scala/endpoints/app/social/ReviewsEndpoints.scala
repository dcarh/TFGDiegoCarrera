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
      "getAllReviews",
      "This endpoint returns a list with all the reviews in the app",
      "GET"
    )
      .in(QueryInputs.querySortBy)
      .in(QueryInputs.queryCategories)
      .out(SocialOutputs.listOfReviewsOutput)

  val getReview: PublicEndpoint[ReviewId, UserError, Review, Any] =
    reviewBaseEndpoint(
      "getReview",
      "This endpoint returns a review by its Id",
      "GET"
    )
      .in(PathInputs.pathReviewId)
      .out(SocialOutputs.reviewOutput)

  val createReview: PublicEndpoint[Review, UserError, Review, Any] =
    reviewBaseEndpoint(
      "createReview",
      "This endpoint creates a review",
      "POST"
    )
      .in("create")
      .in(JsonInputs.jsonReview)
      .out(SocialOutputs.reviewOutput)

  val editReview: PublicEndpoint[(ReviewId, Review), UserError, Review, Any] =
    reviewBaseEndpoint(
      "editReview",
      "This endpoint edits a review",
      "PUT"
    )
      .in(PathInputs.pathReviewId)
      .in("edit")
      .in(JsonInputs.jsonReview)
      .out(SocialOutputs.reviewOutput)

  val deleteReview: PublicEndpoint[ReviewId, UserError, Unit, Any] =
    reviewBaseEndpoint(
      "deleteReview",
      "This endpoint deletes a review",
      "DELETE"
    )
      .in(PathInputs.pathReviewId)
      .in("delete")

}
