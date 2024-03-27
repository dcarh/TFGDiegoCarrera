package api

import io.circe.generic.auto._
import modelClasses.Review
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._

class ReviewsEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val pathReviewId: EndpointInput[Int] =
    path[Int]("review_id")

  private val queryOrderBy: EndpointInput[String] =
    query[String]("order_by").description("Ordenar por")

  private val jsonReviewListOut: EndpointOutput[Seq[Review]] =
    jsonBody[Seq[Review]]

  private val jsonReviewOut: EndpointOutput[Review] =
    jsonBody[Review]


  private val reviewsBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "reviews")

  private val reviewBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "review")


  val reviewsEndpoint: PublicEndpoint[String, Unit, Seq[Review], Any] =
    reviewsBaseEndpoint
      .name("Reviews endpoint")
      .description("This endpoint returns a list with all the reviews in the app")
      .get
      .in(queryOrderBy)
      .out(jsonReviewListOut)

  val specificReviewEndpoint: PublicEndpoint[Int, Unit, Review, Any] =
    reviewBaseEndpoint
      .name("Specific review endpoint")
      .description("This endpoint returns a specific review by its Id")
      .get
      .in(pathReviewId)
      .out(jsonReviewOut)

}
