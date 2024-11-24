package endpoints.app.user

import sttp.tapir.*
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.app.media.{Book, Episode, Movie, Season, TVShow, Videogame}
import modelClasses.app.social.MediaContentList
import modelClasses.ids.User.UserId

object UserPendingContentEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val usersBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "users")

  private val userBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "user")
  
  val userPendingListEndpoint: PublicEndpoint[UserId, Unit, List[Movie | TVShow | Season | Videogame | Book], Any] =
    userBaseEndpoint
      .name("User's 'Pending' media content endpoint")
      .description("This endpoint returns a list of all the 'Pending' media content for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("pending")
      .out(MediaOutputs.jsonMediaListOut2)

  val userPendingMoviesListEndpoint: PublicEndpoint[UserId, Unit, List[Movie], Any] =
    userBaseEndpoint
      .name("User's 'Pending' movies endpoint")
      .description("This endpoint returns a list of all the 'Pending' movies for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("pending" / "movies")
      .out(MediaOutputs.jsonMovieListOut)

  val userPendingTVShowsListEndpoint: PublicEndpoint[UserId, Unit, List[TVShow], Any] =
    userBaseEndpoint
      .name("User's 'Pending' TV shows endpoint")
      .description("This endpoint returns a list of all the 'Pending' TV shows for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("pending" / "tv_shows")
      .out(MediaOutputs.jsonTVShowListOut)

  val userPendingSeasonsListEndpoint: PublicEndpoint[UserId, Unit, List[Season], Any] =
    userBaseEndpoint
      .name("User's 'Pending' TV seasons endpoint")
      .description("This endpoint returns a list of all the 'Pending' TV seasons for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("pending" / "seasons")
      .out(MediaOutputs.jsonSeasonListOut)

  val userPendingVideogamesListEndpoint: PublicEndpoint[UserId, Unit, List[Videogame], Any] =
    userBaseEndpoint
      .name("User's 'Pending' videogames endpoint")
      .description("This endpoint returns a list of all the 'Pending' videogames for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("pending" / "videogames")
      .out(MediaOutputs.jsonVideogameListOut)

  val userPendingBooksListEndpoint: PublicEndpoint[UserId, Unit, List[Book], Any] =
    userBaseEndpoint
      .name("User's 'Pending' books endpoint")
      .description("This endpoint returns a list of all the 'Pending' books for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("pending" / "books")
      .out(MediaOutputs.jsonBookListOut)

}
