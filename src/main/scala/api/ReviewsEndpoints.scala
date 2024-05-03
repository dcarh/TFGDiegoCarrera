package api

import sttp.tapir._

import modelClasses.social.Review
import api.common.Inputs.inputs
import api.common.Outputs.outputs

class ReviewsEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]
  
  private val reviewsBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "reviews")

  private val reviewBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "review")
  
  val reviewsEndpoint: PublicEndpoint[String, Unit, List[Review], Any] =
    reviewsBaseEndpoint
      .name("Reviews endpoint")
      .description("This endpoint returns a list with all the reviews in the app")
      .get
      .in(inputs.queryOrderBy)
      .out(outputs.jsonReviewListOut)

  val specificReviewEndpoint: PublicEndpoint[Review.Id, Unit, Review, Any] =
    reviewBaseEndpoint
      .name("Specific review endpoint")
      .description("This endpoint returns a specific review by its Id")
      .get
      .in(inputs.pathReviewId)
      .out(outputs.jsonReviewOut)

}
