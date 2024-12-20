package endpoints.app.social

import sttp.tapir.*
import endpoints.EndpointsUtils.httpMethodEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.social.Like
import modelClasses.ids.Social.LikeId

object LikesEndpoints {

  private val likeBaseEndpoint:
    (String, String, String) => PublicEndpoint[Unit, UserError, Unit, Any] =
      (name, description, method) => httpMethodEndpoint(name, description, "like", method)

  val getLikeEndpoint: PublicEndpoint[LikeId, UserError, Like, Any] =
    likeBaseEndpoint(
      "Get like endpoint", 
      "This endpoint returns a specific like by its Id",
      "GET"
    )
      .in(PathInputs.pathLikeId)
      .out(SocialOutputs.likeSuccess)

  val createLikeEndpoint: PublicEndpoint[Unit, UserError, Like, Any] =
    likeBaseEndpoint(
      "Create like endpoint",
      "This endpoint creates a like and returns it in case of success",
      "POST"
    )
      .in("create")
      .out(SocialOutputs.likeSuccess)

  val editLikeEndpoint: PublicEndpoint[LikeId, UserError, Like, Any] =
    likeBaseEndpoint(
      "Edit like endpoint",
      "This endpoint allows to edit a like and returns it in case of success. Otherwise returns an error message",
      "PUT"
    )
      .in(PathInputs.pathLikeId)
      .in("edit")
      .out(SocialOutputs.likeSuccess)

  val deleteLikeEndpoint: PublicEndpoint[LikeId, UserError, Unit, Any] =
    likeBaseEndpoint(
      "Delete like endpoint",
      "This endpoint deletes a like  returns it in case of success",
      "DELETE"
    )
      .in(PathInputs.pathLikeId)
      .in("delete")

}
