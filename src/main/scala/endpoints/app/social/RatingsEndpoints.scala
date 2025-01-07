package endpoints.app.social

import sttp.tapir.*
import endpoints.EndpointsUtils.httpMethodEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.social.Rating
import modelClasses.ids.Social.RatingId

object RatingsEndpoints {

  private val ratingBaseEndpoint:
    (String, String, String) => PublicEndpoint[Unit, UserError, Unit, Any] =
      (name, description, method) => httpMethodEndpoint(name, description, "rating", method)

  val getRating: PublicEndpoint[RatingId, UserError, Rating, Any] =
    ratingBaseEndpoint(
      "Get rating endpoint",
      "This endpoint returns a specific rating by its Id",
      "GET"
    )
      .in(PathInputs.pathRatingId)
      .out(SocialOutputs.ratingOutput)

  val createRating: PublicEndpoint[Rating, UserError, Rating, Any] =
    ratingBaseEndpoint(
      "Create rating endpoint",
      "This endpoint creates a rating and returns it in case of success",
      "POST"
    )
      .in("create")
      .in(JsonInputs.jsonRating)
      .out(SocialOutputs.ratingOutput)

  val editRating: PublicEndpoint[(RatingId, Rating), UserError, Rating, Any] =
    ratingBaseEndpoint(
      "Edit rating endpoint",
      "This endpoint allows to edit a rating and returns it in case of success. Otherwise returns an error message",
      "PUT"
    )
      .in(PathInputs.pathRatingId)
      .in("edit")
      .in(JsonInputs.jsonRating)
      .out(SocialOutputs.ratingOutput)

  val deleteRating: PublicEndpoint[RatingId, UserError, Unit, Any] =
    ratingBaseEndpoint(
      "Delete rating endpoint",
      "This endpoint deletes a rating returns it in case of success",
      "DELETE"
    )
      .in(PathInputs.pathRatingId)
      .in("delete")

}
