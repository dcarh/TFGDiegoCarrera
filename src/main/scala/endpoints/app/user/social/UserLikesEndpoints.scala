package endpoints.app.user

import sttp.tapir.*
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.app.media.{Book, Episode, Movie, Season, TVShow, Videogame}
import modelClasses.app.social.{Like, MediaContentList, Reply, Review}
import modelClasses.ids.User.UserId

object UserLikesEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val usersBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "users")

  private val userBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "user")
  
  val userLikesListEndpoint: PublicEndpoint[UserId, Unit, List[Like], Any] =
    userBaseEndpoint
      .name("User's likes endpoint")
      .description("This endpoint returns a list of all the likes for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("likes")
      .out(SocialOutputs.jsonLikeListOut)
  
  val userLikedElementsListEndpoint: PublicEndpoint[UserId, Unit, List[Movie | TVShow | Season | Episode | Videogame | Book | MediaContentList | Review | Reply], Any] =
    userBaseEndpoint
      .name("User's liked content endpoint")
      .description("This endpoint returns a list of all the liked content for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("likes")
      .out(OtherOutputs.jsonListLikeablesOutput)

  val userLikedMoviesListEndpoint: PublicEndpoint[UserId, Unit, List[Movie], Any] =
    userBaseEndpoint
      .name("User's liked movies endpoint")
      .description("This endpoint returns a list of all the liked movies for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("likes" / "movies")
      .out(MediaOutputs.jsonMovieListOut)

  val userLikedTVShowsListEndpoint: PublicEndpoint[UserId, Unit, List[TVShow], Any] =
    userBaseEndpoint
      .name("User's liked TV shows endpoint")
      .description("This endpoint returns a list of all the liked TV shows for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("likes" / "tv_shows")
      .out(MediaOutputs.jsonTVShowListOut)

  val userLikedSeasonsListEndpoint: PublicEndpoint[UserId, Unit, List[Season], Any] =
    userBaseEndpoint
      .name("User's liked TV seasons endpoint")
      .description("This endpoint returns a list of all the liked TV seasons for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("likes" / "seasons")
      .out(MediaOutputs.jsonSeasonListOut)

  val userLikedEpisodesListEndpoint: PublicEndpoint[UserId, Unit, List[Episode], Any] =
    userBaseEndpoint
      .name("User's liked TV episodes endpoint")
      .description("This endpoint returns a list of all the liked TV episodes for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("likes" / "episodes")
      .out(MediaOutputs.jsonEpisodeListOut)

  val userLikedVideogamesListEndpoint: PublicEndpoint[UserId, Unit, List[Videogame], Any] =
    userBaseEndpoint
      .name("User's liked videogames endpoint")
      .description("This endpoint returns a list of all the liked videogames for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("likes" / "videogames")
      .out(MediaOutputs.jsonVideogameListOut)

  val userLikedBooksListEndpoint: PublicEndpoint[UserId, Unit, List[Book], Any] =
    userBaseEndpoint
      .name("User's liked books endpoint")
      .description("This endpoint returns a list of all the liked books for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("likes" / "books")
      .out(MediaOutputs.jsonBookListOut)

  val userLikedListsListEndpoint: PublicEndpoint[UserId, Unit, List[MediaContentList], Any] =
    userBaseEndpoint
      .name("User's liked lists endpoint")
      .description("This endpoint returns a list of all the liked lists for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("likes" / "lists")
      .out(SocialOutputs.jsonListOfMediaContentListOut)

  val userLikedReviewsListEndpoint: PublicEndpoint[UserId, Unit, List[Review], Any] =
    userBaseEndpoint
      .name("User's liked reviews endpoint")
      .description("This endpoint returns a list of all the liked reviews for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("likes" / "reviews")
      .out(SocialOutputs.jsonReviewListOut)

  val userLikedCommentsListEndpoint: PublicEndpoint[UserId, Unit, List[Reply], Any] =
    userBaseEndpoint
      .name("User's liked replies endpoint")
      .description("This endpoint returns a list of all the liked replies for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("likes" / "replies")
      .out(SocialOutputs.jsonReplyListOut)

}
