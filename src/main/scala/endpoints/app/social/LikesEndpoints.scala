package endpoints.app.social

import sttp.tapir.*
import endpoints.EndpointsUtils.httpMethodEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import domain.errors.UserError.*
import domain.app.social.Like
import domain.ids.Social.LikeId

object LikesEndpoints {

  private val likeBaseEndpoint:
    (String, String, String) => PublicEndpoint[Unit, UserError, Unit, Any] =
      (name, description, method) => httpMethodEndpoint(name, description, "like", method)

  val getLike: PublicEndpoint[LikeId, UserError, Like, Any] =
    likeBaseEndpoint(
      "getLike", 
      "This endpoint returns a like by its Id",
      "GET"
    )
      .in(PathInputs.pathLikeId)
      .out(SocialOutputs.likeOutput)

  val createLike: PublicEndpoint[Like, UserError, Like, Any] =
    likeBaseEndpoint(
      "createLike",
      "This endpoint creates a like",
      "POST"
    )
      .in("create")
      .in(JsonInputs.jsonLike)
      .out(SocialOutputs.likeOutput)

  val deleteLike: PublicEndpoint[LikeId, UserError, Unit, Any] =
    likeBaseEndpoint(
      "deleteLike",
      "This endpoint deletes a like",
      "DELETE"
    )
      .in(PathInputs.pathLikeId)
      .in("delete")

}
