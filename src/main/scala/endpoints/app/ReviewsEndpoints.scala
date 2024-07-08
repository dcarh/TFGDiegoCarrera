package endpoints.app

import sttp.tapir._

import endpoints.inputs.Common._
import endpoints.outputs.Common._
import modelClasses.app.social.Review

object ReviewsEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]
  
  private val reviewsBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "reviews")

  private val reviewBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "review")
  
  val reviewsEndpoint: PublicEndpoint[Option[String], Unit, List[Review], Any] =
    reviewsBaseEndpoint
      .name("Reviews endpoint")
      .description("This endpoint returns a list with all the reviews in the app")
      .get
      .in(QueryInputs.querySortBy)
      .out(SocialOutputs.jsonReviewListOut)

  val specificReviewEndpoint: PublicEndpoint[Review.Id, Unit, Review, Any] =
    reviewBaseEndpoint
      .name("Specific review endpoint")
      .description("This endpoint returns a specific review by its Id")
      .get
      .in(PathInputs.pathReviewId)
      .out(SocialOutputs.jsonReviewOut)

}
