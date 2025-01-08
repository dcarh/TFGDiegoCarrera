package endpoints.app.user.social

import sttp.tapir.*
import endpoints.app.user.UserEndpointsUtils.userBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.media.{Book, Episode, Movie, Season, TvShow, Videogame}
import modelClasses.app.social.{Like, MediaList, Reply, Review}
import modelClasses.ids.Social.LikeId
import modelClasses.ids.User.UserId

object UserLikesEndpoints {

  private val userLikesBaseEndpoint:
    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
      (name, description, method) => userBaseEndpoint(name, description, method)
        .in(PathInputs.pathUserId)
        .in("likes")
  
  val getUserLikes: PublicEndpoint[UserId, UserError, List[LikeId], Any] =
    userLikesBaseEndpoint(
      "User's likes endpoint",
      "This endpoint returns a list of all the likes for a user",
      "GET"
    )
      .out(SocialOutputs.listOfLikesIdsOutput)
  
//  val userLikedElementsListEndpoint: PublicEndpoint[UserId, UserError, List[MediaContentList | Review | Reply], Any] =
//    userLikesBaseEndpoint(
//      "User's liked content endpoint",
//      "This endpoint returns a list of all the liked content for a user",
//      "GET"
//    )
//      .out(SocialOutputs.listOfLikeableObjectsOutput)

//  val userLikedListsListEndpoint: PublicEndpoint[UserId, UserError, List[MediaContentList], Any] =
//    userLikesBaseEndpoint(
//      "User's liked lists endpoint",
//      "This endpoint returns a list of all the liked lists for a user",
//      "GET"
//    )
//      .in("lists")
//      .out(SocialOutputs.listOfMediaContentListOutput)
//
//  val userLikedReviewsListEndpoint: PublicEndpoint[UserId, UserError, List[Review], Any] =
//    userLikesBaseEndpoint(
//      "User's liked reviews endpoint",
//      "This endpoint returns a list of all the liked reviews for a user",
//      "GET"
//    )
//      .in("reviews")
//      .out(SocialOutputs.listOfReviewsOutput)
//
//  val userLikedCommentsListEndpoint: PublicEndpoint[UserId, UserError, List[Reply], Any] =
//    userLikesBaseEndpoint(
//      "User's liked replies endpoint",
//      "This endpoint returns a list of all the liked replies for a user",
//      "GET"
//    )
//      .in("replies")
//      .out(SocialOutputs.listOfRepliesOutput)

//  val userLikedMoviesListEndpoint: PublicEndpoint[UserId, ErrorInfo, List[Movie], Any] =
//    userBaseEndpoint(
//      "User's liked movies endpoint",
//      "This endpoint returns a list of all the liked movies for a user",
//      "GET"
//    )
//      .in(PathInputs.pathUserId)
//      .in("likes" / "movies")
//      .out(MediaOutputs.listOfMoviesOutput)
//
//  val userLikedTVShowsListEndpoint: PublicEndpoint[UserId, ErrorInfo, List[TVShow], Any] =
//    userBaseEndpoint(
//      "User's liked TV shows endpoint",
//      "This endpoint returns a list of all the liked TV shows for a user",
//      "GET"
//    )
//      .in(PathInputs.pathUserId)
//      .in("likes" / "tv_shows")
//      .out(MediaOutputs.listOfTvShowsOutput)
//
//  val userLikedSeasonsListEndpoint: PublicEndpoint[UserId, ErrorInfo, List[Season], Any] =
//    userBaseEndpoint(
//      "User's liked TV seasons endpoint",
//      "This endpoint returns a list of all the liked TV seasons for a user",
//      "GET"
//    )
//      .in(PathInputs.pathUserId)
//      .in("likes" / "seasons")
//      .out(MediaOutputs.listOfSeasonsOutput)
//
//  val userLikedEpisodesListEndpoint: PublicEndpoint[UserId, ErrorInfo, List[Episode], Any] =
//    userBaseEndpoint(
//      "User's liked TV episodes endpoint",
//      "This endpoint returns a list of all the liked TV episodes for a user",
//      "GET"
//    )
//      .in(PathInputs.pathUserId)
//      .in("likes" / "episodes")
//      .out(MediaOutputs.listOfEpisodesOutput)
//
//  val userLikedVideogamesListEndpoint: PublicEndpoint[UserId, ErrorInfo, List[Videogame], Any] =
//    userBaseEndpoint(
//      "User's liked videogames endpoint",
//      "This endpoint returns a list of all the liked videogames for a user",
//      "GET"
//    )
//      .in(PathInputs.pathUserId)
//      .in("likes" / "videogames")
//      .out(MediaOutputs.listOfVideogamesOutput)
//
//  val userLikedBooksListEndpoint: PublicEndpoint[UserId, ErrorInfo, List[Book], Any] =
//    userBaseEndpoint(
//      "User's liked books endpoint",
//      "This endpoint returns a list of all the liked books for a user",
//      "GET"
//    )
//      .in(PathInputs.pathUserId)
//      .in("likes" / "books")
//      .out(MediaOutputs.listOfBooksOutput)

}
