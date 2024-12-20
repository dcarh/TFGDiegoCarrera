package endpoints.app.social

import sttp.tapir.*
import endpoints.EndpointsUtils.httpMethodEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.social.Rating
import modelClasses.ids.Social.RatingId

object RatingsEndpoints {
  
//  private val ratingsBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
//    endpoint.in("ratings")

  private val ratingBaseEndpoint:
    (String, String, String) => PublicEndpoint[Unit, UserError, Unit, Any] =
      (name, description, method) => httpMethodEndpoint(name, description, "rating", method)
  
//  val ratingsEndpoint: PublicEndpoint[Option[String], Unit, List[Rating], Any] =
//    ratingsBaseEndpoint
//      .name("Ratings endpoint")
//      .description("This endpoint returns a list with all the ratings in the app")
//      .get
//      .in(QueryInputs.querySortBy)
//      .out(SocialOutputs.jsonRatingListOut)

  val getRatingEndpoint: PublicEndpoint[RatingId, UserError, Rating, Any] =
    ratingBaseEndpoint(
      "Get rating endpoint",
      "This endpoint returns a specific rating by its Id",
      "GET"
    )
      .in(PathInputs.pathRatingId)
      .out(SocialOutputs.ratingSuccess)

  val createRatingEndpoint: PublicEndpoint[Unit, UserError, Rating, Any] =
    ratingBaseEndpoint(
      "Create rating endpoint",
      "This endpoint creates a rating and returns it in case of success",
      "POST"
    )
      .in("create")
      .out(SocialOutputs.ratingSuccess)

  val editRatingEndpoint: PublicEndpoint[RatingId, UserError, Rating, Any] =
    ratingBaseEndpoint(
      "Edit rating endpoint",
      "This endpoint allows to edit a rating and returns it in case of success. Otherwise returns an error message",
      "PUT"
    )
      .in(PathInputs.pathRatingId)
      .in("edit")
      .out(SocialOutputs.ratingSuccess)

  val deleteRatingEndpoint: PublicEndpoint[RatingId, UserError, Unit, Any] =
    ratingBaseEndpoint(
      "Delete rating endpoint",
      "This endpoint deletes a rating returns it in case of success",
      "DELETE"
    )
      .in(PathInputs.pathRatingId)
      .in("delete")

}
