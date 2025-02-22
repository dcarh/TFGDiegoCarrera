package endpoints.app.user.social

import sttp.tapir.*
import endpoints.app.user.UserEndpointsUtils.specificUserBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.media.{Book, TvEpisode, Movie, TvSeason, TvShow, Videogame}
import modelClasses.app.social.{Like, MediaList, Reply, Review}
import modelClasses.ids.Social.LikeId
import modelClasses.ids.User.UserId

object UserLikesEndpoints {

  private val userLikesBaseEndpoint:
    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
      (name, description, method) => specificUserBaseEndpoint(name, description, method)
        .in("likes")
  
  val getUserLikes: PublicEndpoint[(UserId, Option[List[String]], Option[String]), UserError, List[LikeId], Any] =
    userLikesBaseEndpoint(
      "User's likes endpoint",
      "This endpoint returns a list of all the likes for a user",
      "GET"
    )
      .in(QueryInputs.queryCategories)
      .in(QueryInputs.querySortBy)
      .out(SocialOutputs.listOfLikesIdsOutput)
}
