package endpoints.app.user

import sttp.tapir.*
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.app.media.*
import modelClasses.app.social.MediaContentList
import modelClasses.ids.User.UserId

object UserAbandonedContentEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val usersBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "users")

  private val userBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "user")

  // TODO: No debe devolver un MediaContentList, sino un List[Movie | TVShow | Videogame ...]
  
  val userAbandonedListEndpoint: PublicEndpoint[UserId, Unit, List[Movie | TVShow | Season | Episode | Videogame | Book], Any] =
    userBaseEndpoint
      .name("User's 'Abandoned' media content endpoint")
      .description("This endpoint returns a list of all the 'Abandoned' media content for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("abandoned")
      .out(MediaOutputs.jsonAllMediaListOut)

  val userAbandonedMoviesListEndpoint: PublicEndpoint[UserId, Unit, List[Movie], Any] =
    userBaseEndpoint
      .name("User's 'Abandoned' movies endpoint")
      .description("This endpoint returns a list of all the 'Abandoned' movies for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("abandoned" / "movies")
      .out(MediaOutputs.jsonMovieListOut)

  val userAbandonedTVShowsListEndpoint: PublicEndpoint[UserId, Unit, List[TVShow], Any] =
    userBaseEndpoint
      .name("User's 'Abandoned' TV shows endpoint")
      .description("This endpoint returns a list of all the 'Abandoned' TV shows for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("abandoned" / "tv_shows")
      .out(MediaOutputs.jsonTVShowListOut)

  val userAbandonedSeasonsListEndpoint: PublicEndpoint[UserId, Unit, List[Season], Any] =
    userBaseEndpoint
      .name("User's 'Abandoned' TV seasons endpoint")
      .description("This endpoint returns a list of all the 'Abandoned' TV seasons for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("abandoned" / "seasons")
      .out(MediaOutputs.jsonSeasonListOut)
  
  val userAbandonedEpisodesListEndpoint: PublicEndpoint[UserId, Unit, List[Episode], Any] =
    userBaseEndpoint
      .name("User's 'Abandoned' TV episodes endpoint")
      .description("This endpoint returns a list of all the 'Abandoned' TV episodes for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("abandoned" / "episodes")
      .out(MediaOutputs.jsonEpisodeListOut)

  val userAbandonedVideogamesListEndpoint: PublicEndpoint[UserId, Unit, List[Videogame], Any] =
    userBaseEndpoint
      .name("User's 'Abandoned' videogames endpoint")
      .description("This endpoint returns a list of all the 'Abandoned' videogames for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("abandoned" / "videogames")
      .out(MediaOutputs.jsonVideogameListOut)

  val userAbandonedBooksListEndpoint: PublicEndpoint[UserId, Unit, List[Book], Any] =
    userBaseEndpoint
      .name("User's 'Abandoned' books endpoint")
      .description("This endpoint returns a list of all the 'Abandoned' books for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("abandoned" / "books")
      .out(MediaOutputs.jsonBookListOut)

}
