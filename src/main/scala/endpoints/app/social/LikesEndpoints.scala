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

  val getLike: PublicEndpoint[LikeId, UserError, Like, Any] =
    likeBaseEndpoint(
      "getLike", 
      "This endpoint returns a specific like by its Id",
      "GET"
    )
      .in(PathInputs.pathLikeId)
      .out(SocialOutputs.likeOutput)

  val createLike: PublicEndpoint[Like, UserError, Like, Any] =
    likeBaseEndpoint(
      "createLike",
      "This endpoint creates a like and returns it in case of success",
      "POST"
    )
      .in("create")
      .in(JsonInputs.jsonLike)
      .out(SocialOutputs.likeOutput)

  val deleteLike: PublicEndpoint[LikeId, UserError, Unit, Any] =
    likeBaseEndpoint(
      "deleteLike",
      "This endpoint deletes a like  returns it in case of success",
      "DELETE"
    )
      .in(PathInputs.pathLikeId)
      .in("delete")

}
