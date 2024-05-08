package api.user

import sttp.tapir._

import modelClasses.media._
import modelClasses.social.{MediaContentList, Review, Reply}
import api.common.Inputs.inputs
import api.common.Outputs.outputs

class UserLikesEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val usersBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "users")

  private val userBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "user")

  val userLikesListEndpoint: PublicEndpoint[String, Unit, MediaContentList, Any] =
    userBaseEndpoint
      .name("User's liked media content endpoint")
      .description("This endpoint returns a list of all the liked media content for a user")
      .get
      .in(inputs.pathUsername)
      .in("likes")
      .out(outputs.jsonMediaContentListOut)

  val userLikedMoviesListEndpoint: PublicEndpoint[String, Unit, List[Movie], Any] =
    userBaseEndpoint
      .name("User's liked movies endpoint")
      .description("This endpoint returns a list of all the liked movies for a user")
      .get
      .in(inputs.pathUsername)
      .in("likes" / "movies")
      .out(outputs.jsonMovieListOut)

  val userLikedTVShowsListEndpoint: PublicEndpoint[String, Unit, List[TVShow], Any] =
    userBaseEndpoint
      .name("User's liked TV shows endpoint")
      .description("This endpoint returns a list of all the liked TV shows for a user")
      .get
      .in(inputs.pathUsername)
      .in("likes" / "tv_shows")
      .out(outputs.jsonTVShowListOut)

  val userLikedSeasonsListEndpoint: PublicEndpoint[String, Unit, List[Season], Any] =
    userBaseEndpoint
      .name("User's liked TV seasons endpoint")
      .description("This endpoint returns a list of all the liked TV seasons for a user")
      .get
      .in(inputs.pathUsername)
      .in("likes" / "seasons")
      .out(outputs.jsonSeasonListOut)

  val userLikedEpisodesListEndpoint: PublicEndpoint[String, Unit, List[Episode], Any] =
    userBaseEndpoint
      .name("User's liked TV episodes endpoint")
      .description("This endpoint returns a list of all the liked TV episodes for a user")
      .get
      .in(inputs.pathUsername)
      .in("likes" / "episodes")
      .out(outputs.jsonEpisodeListOut)

  val userLikedVideogamesListEndpoint: PublicEndpoint[String, Unit, List[Videogame], Any] =
    userBaseEndpoint
      .name("User's liked videogames endpoint")
      .description("This endpoint returns a list of all the liked videogames for a user")
      .get
      .in(inputs.pathUsername)
      .in("likes" / "videogames")
      .out(outputs.jsonVideogameListOut)

  val userLikedBooksListEndpoint: PublicEndpoint[String, Unit, List[Book], Any] =
    userBaseEndpoint
      .name("User's liked books endpoint")
      .description("This endpoint returns a list of all the liked books for a user")
      .get
      .in(inputs.pathUsername)
      .in("likes" / "books")
      .out(outputs.jsonBookListOut)

  val userLikedReviewsListEndpoint: PublicEndpoint[String, Unit, List[Review], Any] =
    userBaseEndpoint
      .name("User's liked reviews endpoint")
      .description("This endpoint returns a list of all the liked reviews for a user")
      .get
      .in(inputs.pathUsername)
      .in("likes" / "reviews")
      .out(outputs.jsonReviewListOut)

  val userLikedCommentsListEndpoint: PublicEndpoint[String, Unit, List[Reply], Any] =
    userBaseEndpoint
      .name("User's liked replies endpoint")
      .description("This endpoint returns a list of all the liked replies for a user")
      .get
      .in(inputs.pathUsername)
      .in("likes" / "replies")
      .out(outputs.jsonReplyListOut)

  val userLikedListsListEndpoint: PublicEndpoint[String, Unit, List[MediaContentList], Any] =
    userBaseEndpoint
      .name("User's liked lists endpoint")
      .description("This endpoint returns a list of all the liked lists for a user")
      .get
      .in(inputs.pathUsername)
      .in("likes" / "lists")
      .out(outputs.jsonListOfMediaContentListOut)

  val userLikedMovieListsListEndpoint: PublicEndpoint[String, Unit, List[MediaContentList], Any] =
    userBaseEndpoint
      .name("User's liked movie lists endpoint")
      .description("This endpoint returns a list of all the liked movie lists for a user")
      .get
      .in(inputs.pathUsername)
      .in("likes" / "lists" / "movies")
      .out(outputs.jsonListOfMediaContentListOut)

  val userLikedTVShowsListsListEndpoint: PublicEndpoint[String, Unit, List[MediaContentList], Any] =
    userBaseEndpoint
      .name("User's liked TV shows lists endpoint")
      .description("This endpoint returns a list of all the liked TV shows lists for a user")
      .get
      .in(inputs.pathUsername)
      .in("likes" / "lists" / "tv_shows")
      .out(outputs.jsonListOfMediaContentListOut)

  val userLikedSeasonsListsListEndpoint: PublicEndpoint[String, Unit, List[MediaContentList], Any] =
    userBaseEndpoint
      .name("User's liked TV seasons lists endpoint")
      .description("This endpoint returns a list of all the liked TV seasons lists for a user")
      .get
      .in(inputs.pathUsername)
      .in("likes" / "lists" / "seasons")
      .out(outputs.jsonListOfMediaContentListOut)

  val userLikedEpisodesListsListEndpoint: PublicEndpoint[String, Unit, List[MediaContentList], Any] =
    userBaseEndpoint
      .name("User's liked TV episodes lists endpoint")
      .description("This endpoint returns a list of all the liked TV episodes lists for a user")
      .get
      .in(inputs.pathUsername)
      .in("likes" / "lists" / "episodes")
      .out(outputs.jsonListOfMediaContentListOut)

  val userLikedVideogamesListsListEndpoint: PublicEndpoint[String, Unit, List[MediaContentList], Any] =
    userBaseEndpoint
      .name("User's liked videogames lists endpoint")
      .description("This endpoint returns a list of all the liked videogames lists for a user")
      .get
      .in(inputs.pathUsername)
      .in("likes" / "lists" / "videogames")
      .out(outputs.jsonListOfMediaContentListOut)

  val userLikedBooksListsListEndpoint: PublicEndpoint[String, Unit, List[MediaContentList], Any] =
    userBaseEndpoint
      .name("User's liked books lists endpoint")
      .description("This endpoint returns a list of all the liked books lists for a user")
      .get
      .in(inputs.pathUsername)
      .in("likes" / "lists" / "books")
      .out(outputs.jsonListOfMediaContentListOut)

}
