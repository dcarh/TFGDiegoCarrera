package endpoints.app.social

import sttp.tapir.*
import endpoints.EndpointsUtils.httpMethodEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import domain.errors.UserError.*
import domain.app.social.Rating
import domain.ids.Social.RatingId

object RatingsEndpoints {

  private val ratingBaseEndpoint:
    (String, String, String) => PublicEndpoint[Unit, UserError, Unit, Any] =
      (name, description, method) => httpMethodEndpoint(name, description, "rating", method)

  val getRating: PublicEndpoint[RatingId, UserError, Rating, Any] =
    ratingBaseEndpoint(
      "getRating",
      "This endpoint returns a rating by its Id",
      "GET"
    )
      .in(PathInputs.pathRatingId)
      .out(SocialOutputs.ratingOutput)

  val createRating: PublicEndpoint[Rating, UserError, Rating, Any] =
    ratingBaseEndpoint(
      "createRating",
      "This endpoint creates a rating",
      "POST"
    )
      .in("create")
      .in(JsonInputs.jsonRating)
      .out(SocialOutputs.ratingOutput)

  val editRating: PublicEndpoint[(RatingId, Rating), UserError, Rating, Any] =
    ratingBaseEndpoint(
      "editRating",
      "This endpoint edits a rating",
      "PUT"
    )
      .in(PathInputs.pathRatingId)
      .in("edit")
      .in(JsonInputs.jsonRating)
      .out(SocialOutputs.ratingOutput)

  val deleteRating: PublicEndpoint[RatingId, UserError, Unit, Any] =
    ratingBaseEndpoint(
      "deleteRating",
      "This endpoint deletes a rating",
      "DELETE"
    )
      .in(PathInputs.pathRatingId)
      .in("delete")

}
