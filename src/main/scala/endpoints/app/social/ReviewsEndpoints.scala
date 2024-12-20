package endpoints.app

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
  
  val getReviewsEndpoint: PublicEndpoint[Option[String], UserError, List[Review], Any] =
    reviewsBaseEndpoint(
      "Get reviews endpoint",
      "This endpoint returns a list with all the reviews in the app",
      "GET"
    )
      .in(QueryInputs.querySortBy)
      .out(SocialOutputs.listOfReviewsSuccess)

  val getReviewEndpoint: PublicEndpoint[ReviewId, UserError, Review, Any] =
    reviewBaseEndpoint(
      "Get review endpoint",
      "This endpoint returns a specific review by its Id",
      "GET"
    )
      .in(PathInputs.pathReviewId)
      .out(SocialOutputs.reviewSuccess)

  val createLikeEndpoint: PublicEndpoint[Unit, UserError, Review, Any] =
    reviewBaseEndpoint(
      "Create like endpoint",
      "This endpoint creates a like and returns it in case of success",
      "POST"
    )
      .in("create")
      .out(SocialOutputs.reviewSuccess)

  val editLikeEndpoint: PublicEndpoint[ReviewId, UserError, Review, Any] =
    reviewBaseEndpoint(
      "Edit like endpoint",
      "This endpoint allows to edit a like and returns it in case of success. Otherwise returns an error message",
      "PUT"
    )
      .in(PathInputs.pathReviewId)
      .in("edit")
      .out(SocialOutputs.reviewSuccess)

  val deleteLikeEndpoint: PublicEndpoint[ReviewId, UserError, Unit, Any] =
    reviewBaseEndpoint(
      "Delete like endpoint",
      "This endpoint deletes a like  returns it in case of success",
      "DELETE"
    )
      .in(PathInputs.pathReviewId)
      .in("delete")

}
