package endpoints.app.social

import sttp.tapir.*

import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.app.social.Rating
import modelClasses.ids.Social.RatingId

object RatingsEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]
  
  private val ratingsBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "ratings")

  private val ratingBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "rating")
  
  val ratingsEndpoint: PublicEndpoint[Option[String], Unit, List[Rating], Any] =
    ratingsBaseEndpoint
      .name("Ratings endpoint")
      .description("This endpoint returns a list with all the ratings in the app")
      .get
      .in(QueryInputs.querySortBy)
      .out(SocialOutputs.jsonRatingListOut)

  val specificRatingEndpoint: PublicEndpoint[RatingId, Unit, Rating, Any] =
    ratingBaseEndpoint
      .name("Specific rating endpoint")
      .description("This endpoint returns a specific rating by its Id")
      .get
      .in(PathInputs.pathRatingId)
      .out(SocialOutputs.jsonRatingOut)

}
