package endpoints.app.user

import sttp.tapir.*
import endpoints.app.user.UserEndpointsUtils.userBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.media.{Book, Episode, Movie, Season, TVShow, Videogame}
import modelClasses.app.social.{Like, MediaContentList, Reply, Review}
import modelClasses.ids.User.UserId

object UserLikesEndpoints {
  
  val userLikesListEndpoint: PublicEndpoint[UserId, UserError, List[Like], Any] =
    userBaseEndpoint(
      "User's likes endpoint",
      "This endpoint returns a list of all the likes for a user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("likes")
      .out(SocialOutputs.listOfLikesSuccess)
  
  val userLikedElementsListEndpoint: PublicEndpoint[UserId, UserError, List[MediaContentList | Review | Reply], Any] =
    userBaseEndpoint(
      "User's liked content endpoint",
      "This endpoint returns a list of all the liked content for a user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("likes")
      .out(SocialOutputs.listOfLikeableObjectsSuccess)

//  val userLikedMoviesListEndpoint: PublicEndpoint[UserId, ErrorInfo, List[Movie], Any] =
//    userBaseEndpoint(
//      "User's liked movies endpoint",
//      "This endpoint returns a list of all the liked movies for a user",
//      "GET"
//    )
//      .in(PathInputs.pathUserId)
//      .in("likes" / "movies")
//      .out(MediaOutputs.listOfMoviesSuccess)
//
//  val userLikedTVShowsListEndpoint: PublicEndpoint[UserId, ErrorInfo, List[TVShow], Any] =
//    userBaseEndpoint(
//      "User's liked TV shows endpoint",
//      "This endpoint returns a list of all the liked TV shows for a user",
//      "GET"
//    )
//      .in(PathInputs.pathUserId)
//      .in("likes" / "tv_shows")
//      .out(MediaOutputs.listOfTvShowsSuccess)
//
//  val userLikedSeasonsListEndpoint: PublicEndpoint[UserId, ErrorInfo, List[Season], Any] =
//    userBaseEndpoint(
//      "User's liked TV seasons endpoint",
//      "This endpoint returns a list of all the liked TV seasons for a user",
//      "GET"
//    )
//      .in(PathInputs.pathUserId)
//      .in("likes" / "seasons")
//      .out(MediaOutputs.listOfSeasonsSuccess)
//
//  val userLikedEpisodesListEndpoint: PublicEndpoint[UserId, ErrorInfo, List[Episode], Any] =
//    userBaseEndpoint(
//      "User's liked TV episodes endpoint",
//      "This endpoint returns a list of all the liked TV episodes for a user",
//      "GET"
//    )
//      .in(PathInputs.pathUserId)
//      .in("likes" / "episodes")
//      .out(MediaOutputs.listOfEpisodesSuccess)
//
//  val userLikedVideogamesListEndpoint: PublicEndpoint[UserId, ErrorInfo, List[Videogame], Any] =
//    userBaseEndpoint(
//      "User's liked videogames endpoint",
//      "This endpoint returns a list of all the liked videogames for a user",
//      "GET"
//    )
//      .in(PathInputs.pathUserId)
//      .in("likes" / "videogames")
//      .out(MediaOutputs.listOfVideogamesSuccess)
//
//  val userLikedBooksListEndpoint: PublicEndpoint[UserId, ErrorInfo, List[Book], Any] =
//    userBaseEndpoint(
//      "User's liked books endpoint",
//      "This endpoint returns a list of all the liked books for a user",
//      "GET"
//    )
//      .in(PathInputs.pathUserId)
//      .in("likes" / "books")
//      .out(MediaOutputs.listOfBooksSuccess)

  val userLikedListsListEndpoint: PublicEndpoint[UserId, UserError, List[MediaContentList], Any] =
    userBaseEndpoint(
      "User's liked lists endpoint",
      "This endpoint returns a list of all the liked lists for a user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("likes" / "lists")
      .out(SocialOutputs.listOfMediaContentListSuccess)

  val userLikedReviewsListEndpoint: PublicEndpoint[UserId, UserError, List[Review], Any] =
    userBaseEndpoint(
      "User's liked reviews endpoint",
      "This endpoint returns a list of all the liked reviews for a user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("likes" / "reviews")
      .out(SocialOutputs.listOfReviewsSuccess)

  val userLikedCommentsListEndpoint: PublicEndpoint[UserId, UserError, List[Reply], Any] =
    userBaseEndpoint(
      "User's liked replies endpoint",
      "This endpoint returns a list of all the liked replies for a user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("likes" / "replies")
      .out(SocialOutputs.listOfRepliesSuccess)

}
