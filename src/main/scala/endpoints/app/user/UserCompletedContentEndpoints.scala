package endpoints.app.user

import sttp.tapir.*
import endpoints.inputs.Common._
import endpoints.outputs.Common._
import modelClasses.app.media.{Book, Episode, Movie, Season, TVShow, Videogame}
import modelClasses.app.social.MediaContentList

object UserCompletedContentEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val usersBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "users")

  private val userBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "user")

  // TODO: No debe devolver un MediaContentList, sino un List[Movie | TVShow | Videogame ...]
  val userCompletedListEndpoint: PublicEndpoint[String, Unit, MediaContentList, Any] =
    userBaseEndpoint
      .name("User's 'Completed' media content endpoint")
      .description("This endpoint returns a list of all the 'Completed' media content for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("completed")
      .out(SocialOutputs.jsonMediaContentListOut)

  val userCompletedMoviesListEndpoint: PublicEndpoint[String, Unit, List[Movie], Any] =
    userBaseEndpoint
      .name("User's 'Completed' movies endpoint")
      .description("This endpoint returns a list of all the 'Completed' movies for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("completed" / "movies")
      .out(MediaOutputs.jsonMovieListOut)

  val userCompletedTVShowsListEndpoint: PublicEndpoint[String, Unit, List[TVShow], Any] =
    userBaseEndpoint
      .name("User's 'Completed' TV shows endpoint")
      .description("This endpoint returns a list of all the 'Completed' TV shows for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("completed" / "tv_shows")
      .out(MediaOutputs.jsonTVShowListOut)

  val userCompletedSeasonsListEndpoint: PublicEndpoint[String, Unit, List[Season], Any] =
    userBaseEndpoint
      .name("User's 'Completed' seasons endpoint")
      .description("This endpoint returns a list of all the 'Completed' seasons for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("completed" / "seasons")
      .out(MediaOutputs.jsonSeasonListOut)

  val userCompletedEpisodesListEndpoint: PublicEndpoint[String, Unit, List[Episode], Any] =
    userBaseEndpoint
      .name("User's 'Completed' episodes endpoint")
      .description("This endpoint returns a list of all the 'Completed' episodes for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("completed" / "episodes")
      .out(MediaOutputs.jsonEpisodeListOut)

  val userCompletedVideogamesListEndpoint: PublicEndpoint[String, Unit, List[Videogame], Any] =
    userBaseEndpoint
      .name("User's 'Completed' videogames endpoint")
      .description("This endpoint returns a list of all the 'Completed' videogames for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("completed" / "videogames")
      .out(MediaOutputs.jsonVideogameListOut)

  val userCompletedBooksListEndpoint: PublicEndpoint[String, Unit, List[Book], Any] =
    userBaseEndpoint
      .name("User's 'Completed' books endpoint")
      .description("This endpoint returns a list of all the 'Completed' books for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("completed" / "books")
      .out(MediaOutputs.jsonBookListOut)

}
