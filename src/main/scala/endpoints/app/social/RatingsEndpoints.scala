package endpoints.app.social

import sttp.tapir.*
import endpoints.EndpointsUtils.appBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.ErrorInfo
import modelClasses.app.social.Rating
import modelClasses.ids.Social.RatingId

object RatingsEndpoints {
  
//  private val ratingsBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
//    endpoint.in("ratings")

  private val ratingBaseEndpoint:
    (String, String, String) => PublicEndpoint[Unit, ErrorInfo, Unit, Any] =
      (name, description, method) => appBaseEndpoint(name, description, "rating", method)
  
//  val ratingsEndpoint: PublicEndpoint[Option[String], Unit, List[Rating], Any] =
//    ratingsBaseEndpoint
//      .name("Ratings endpoint")
//      .description("This endpoint returns a list with all the ratings in the app")
//      .get
//      .in(QueryInputs.querySortBy)
//      .out(SocialOutputs.jsonRatingListOut)

  val specificRatingEndpoint: PublicEndpoint[RatingId, ErrorInfo, Rating, Any] =
    ratingBaseEndpoint(
      "Specific rating endpoint",
      "This endpoint returns a specific rating by its Id",
      "GET"
    )
      .in(PathInputs.pathRatingId)
      .out(SocialOutputs.ratingSuccess)

}
