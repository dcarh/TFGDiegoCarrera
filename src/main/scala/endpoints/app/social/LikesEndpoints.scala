package endpoints.app.social

import sttp.tapir.*
import endpoints.EndpointsUtils.appBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.ErrorInfo
import modelClasses.app.social.Like
import modelClasses.ids.Social.LikeId

object LikesEndpoints {

  private val likeBaseEndpoint:
    (String, String, String) => PublicEndpoint[Unit, ErrorInfo, Unit, Any] =
      (name, description, method) => appBaseEndpoint(name, description, "like", method)

  val specificLikeEndpoint: PublicEndpoint[LikeId, ErrorInfo, Like, Any] =
    likeBaseEndpoint(
      "Specific like endpoint", 
      "This endpoint returns a specific like by its Id",
      "GET"
    )
      .in(PathInputs.pathLikeId)
      .out(SocialOutputs.likeSuccess)

}
