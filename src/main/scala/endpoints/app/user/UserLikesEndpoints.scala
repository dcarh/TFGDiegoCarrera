package endpoints.app.user

import sttp.tapir.*
import endpoints.inputs.Common._
import endpoints.outputs.Common._
import modelClasses.app.media.{Book, Episode, Movie, Season, TVShow, Videogame}
import modelClasses.app.social.{MediaContentList, Reply, Review}

object UserLikesEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val usersBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "users")

  private val userBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "user")

  // TODO: No debe devolver un MediaContentList, sino un List[Movie | TVShow | Videogame ...] 
  //  (y, adicionalmente debería haber otro que incluya todos los elementos que incluyen los siguientes endpoints) 
  //  (y, además, también uno que devuelva los propios objetos de Like)
  val userLikesListEndpoint: PublicEndpoint[String, Unit, MediaContentList, Any] =
    userBaseEndpoint
      .name("User's liked media content endpoint")
      .description("This endpoint returns a list of all the liked media content for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("likes")
      .out(SocialOutputs.jsonMediaContentListOut)

  val userLikedMoviesListEndpoint: PublicEndpoint[String, Unit, List[Movie], Any] =
    userBaseEndpoint
      .name("User's liked movies endpoint")
      .description("This endpoint returns a list of all the liked movies for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("likes" / "movies")
      .out(MediaOutputs.jsonMovieListOut)

  val userLikedTVShowsListEndpoint: PublicEndpoint[String, Unit, List[TVShow], Any] =
    userBaseEndpoint
      .name("User's liked TV shows endpoint")
      .description("This endpoint returns a list of all the liked TV shows for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("likes" / "tv_shows")
      .out(MediaOutputs.jsonTVShowListOut)

  val userLikedSeasonsListEndpoint: PublicEndpoint[String, Unit, List[Season], Any] =
    userBaseEndpoint
      .name("User's liked TV seasons endpoint")
      .description("This endpoint returns a list of all the liked TV seasons for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("likes" / "seasons")
      .out(MediaOutputs.jsonSeasonListOut)

  val userLikedEpisodesListEndpoint: PublicEndpoint[String, Unit, List[Episode], Any] =
    userBaseEndpoint
      .name("User's liked TV episodes endpoint")
      .description("This endpoint returns a list of all the liked TV episodes for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("likes" / "episodes")
      .out(MediaOutputs.jsonEpisodeListOut)

  val userLikedVideogamesListEndpoint: PublicEndpoint[String, Unit, List[Videogame], Any] =
    userBaseEndpoint
      .name("User's liked videogames endpoint")
      .description("This endpoint returns a list of all the liked videogames for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("likes" / "videogames")
      .out(MediaOutputs.jsonVideogameListOut)

  val userLikedBooksListEndpoint: PublicEndpoint[String, Unit, List[Book], Any] =
    userBaseEndpoint
      .name("User's liked books endpoint")
      .description("This endpoint returns a list of all the liked books for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("likes" / "books")
      .out(MediaOutputs.jsonBookListOut)

  val userLikedReviewsListEndpoint: PublicEndpoint[String, Unit, List[Review], Any] =
    userBaseEndpoint
      .name("User's liked reviews endpoint")
      .description("This endpoint returns a list of all the liked reviews for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("likes" / "reviews")
      .out(SocialOutputs.jsonReviewListOut)

  val userLikedCommentsListEndpoint: PublicEndpoint[String, Unit, List[Reply], Any] =
    userBaseEndpoint
      .name("User's liked replies endpoint")
      .description("This endpoint returns a list of all the liked replies for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("likes" / "replies")
      .out(SocialOutputs.jsonReplyListOut)

  val userLikedListsListEndpoint: PublicEndpoint[String, Unit, List[MediaContentList], Any] =
    userBaseEndpoint
      .name("User's liked lists endpoint")
      .description("This endpoint returns a list of all the liked lists for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("likes" / "lists")
      .out(SocialOutputs.jsonListOfMediaContentListOut)

  val userLikedMovieListsListEndpoint: PublicEndpoint[String, Unit, List[MediaContentList], Any] =
    userBaseEndpoint
      .name("User's liked movie lists endpoint")
      .description("This endpoint returns a list of all the liked movie lists for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("likes" / "lists" / "movies")
      .out(SocialOutputs.jsonListOfMediaContentListOut)

  val userLikedTVShowsListsListEndpoint: PublicEndpoint[String, Unit, List[MediaContentList], Any] =
    userBaseEndpoint
      .name("User's liked TV shows lists endpoint")
      .description("This endpoint returns a list of all the liked TV shows lists for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("likes" / "lists" / "tv_shows")
      .out(SocialOutputs.jsonListOfMediaContentListOut)

  val userLikedSeasonsListsListEndpoint: PublicEndpoint[String, Unit, List[MediaContentList], Any] =
    userBaseEndpoint
      .name("User's liked TV seasons lists endpoint")
      .description("This endpoint returns a list of all the liked TV seasons lists for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("likes" / "lists" / "seasons")
      .out(SocialOutputs.jsonListOfMediaContentListOut)

  val userLikedEpisodesListsListEndpoint: PublicEndpoint[String, Unit, List[MediaContentList], Any] =
    userBaseEndpoint
      .name("User's liked TV episodes lists endpoint")
      .description("This endpoint returns a list of all the liked TV episodes lists for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("likes" / "lists" / "episodes")
      .out(SocialOutputs.jsonListOfMediaContentListOut)

  val userLikedVideogamesListsListEndpoint: PublicEndpoint[String, Unit, List[MediaContentList], Any] =
    userBaseEndpoint
      .name("User's liked videogames lists endpoint")
      .description("This endpoint returns a list of all the liked videogames lists for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("likes" / "lists" / "videogames")
      .out(SocialOutputs.jsonListOfMediaContentListOut)

  val userLikedBooksListsListEndpoint: PublicEndpoint[String, Unit, List[MediaContentList], Any] =
    userBaseEndpoint
      .name("User's liked books lists endpoint")
      .description("This endpoint returns a list of all the liked books lists for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("likes" / "lists" / "books")
      .out(SocialOutputs.jsonListOfMediaContentListOut)

}
