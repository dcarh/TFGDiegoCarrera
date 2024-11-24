package endpoints.app.user

import sttp.tapir.*
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.app.media.{Book, Episode, Movie, Season, TVShow, Videogame}
import modelClasses.app.social.MediaContentList
import modelClasses.ids.User.UserId

object UserCompletedContentEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val usersBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "users")

  private val userBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "user")

  // TODO: No debe devolver un MediaContentList, sino un List[Movie | TVShow | Videogame ...]
  
  val userCompletedListEndpoint: PublicEndpoint[UserId, Unit, List[Movie | TVShow | Season | Episode | Videogame | Book], Any] =
    userBaseEndpoint
      .name("User's 'Completed' media content endpoint")
      .description("This endpoint returns a list of all the 'Completed' media content for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("completed")
      .out(MediaOutputs.jsonAllMediaListOut)

  val userCompletedMoviesListEndpoint: PublicEndpoint[UserId, Unit, List[Movie], Any] =
    userBaseEndpoint
      .name("User's 'Completed' movies endpoint")
      .description("This endpoint returns a list of all the 'Completed' movies for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("completed" / "movies")
      .out(MediaOutputs.jsonMovieListOut)

  val userCompletedTVShowsListEndpoint: PublicEndpoint[UserId, Unit, List[TVShow], Any] =
    userBaseEndpoint
      .name("User's 'Completed' TV shows endpoint")
      .description("This endpoint returns a list of all the 'Completed' TV shows for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("completed" / "tv_shows")
      .out(MediaOutputs.jsonTVShowListOut)

  val userCompletedSeasonsListEndpoint: PublicEndpoint[UserId, Unit, List[Season], Any] =
    userBaseEndpoint
      .name("User's 'Completed' seasons endpoint")
      .description("This endpoint returns a list of all the 'Completed' seasons for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("completed" / "seasons")
      .out(MediaOutputs.jsonSeasonListOut)

  val userCompletedEpisodesListEndpoint: PublicEndpoint[UserId, Unit, List[Episode], Any] =
    userBaseEndpoint
      .name("User's 'Completed' episodes endpoint")
      .description("This endpoint returns a list of all the 'Completed' episodes for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("completed" / "episodes")
      .out(MediaOutputs.jsonEpisodeListOut)

  val userCompletedVideogamesListEndpoint: PublicEndpoint[UserId, Unit, List[Videogame], Any] =
    userBaseEndpoint
      .name("User's 'Completed' videogames endpoint")
      .description("This endpoint returns a list of all the 'Completed' videogames for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("completed" / "videogames")
      .out(MediaOutputs.jsonVideogameListOut)

  val userCompletedBooksListEndpoint: PublicEndpoint[UserId, Unit, List[Book], Any] =
    userBaseEndpoint
      .name("User's 'Completed' books endpoint")
      .description("This endpoint returns a list of all the 'Completed' books for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("completed" / "books")
      .out(MediaOutputs.jsonBookListOut)

}
