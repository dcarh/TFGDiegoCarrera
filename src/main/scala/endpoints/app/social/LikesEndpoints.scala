package endpoints.app.social

import sttp.tapir.*

import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.app.social.Like
import modelClasses.ids.Social.LikeId

object LikesEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]
  
  private val likesBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "likes")

  private val likeBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "like")
  
  val likesEndpoint: PublicEndpoint[Option[String], Unit, List[Like], Any] =
    likesBaseEndpoint
      .name("Likes endpoint")
      .description("This endpoint returns a list with all the likes in the app")
      .get
      .in(QueryInputs.querySortBy)
      .out(SocialOutputs.jsonLikeListOut)

  val specificLikeEndpoint: PublicEndpoint[LikeId, Unit, Like, Any] =
    likeBaseEndpoint
      .name("Specific like endpoint")
      .description("This endpoint returns a specific like by its Id")
      .get
      .in(PathInputs.pathLikeId)
      .out(SocialOutputs.jsonLikeOut)

}
