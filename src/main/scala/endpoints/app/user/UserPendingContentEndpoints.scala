package endpoints.app.user

import sttp.tapir.*
import endpoints.common.Inputs.*
import endpoints.common.Outputs.*
import modelClasses.app.media.{Book, Movie, Season, TVShow, Videogame}
import modelClasses.app.social.MediaContentList

object UserPendingContentEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val usersBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "users")

  private val userBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "user")
    
  // TODO: No debe devolver un MediaContentList, sino un List[Movie | TVShow | Videogame ...]
  val userPendingListEndpoint: PublicEndpoint[String, Unit, MediaContentList, Any] =
    userBaseEndpoint
      .name("User's 'Pending' media content endpoint")
      .description("This endpoint returns a list of all the 'Pending' media content for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("pending")
      .out(SocialOutputs.jsonMediaContentListOut)

  val userPendingMoviesListEndpoint: PublicEndpoint[String, Unit, List[Movie], Any] =
    userBaseEndpoint
      .name("User's 'Pending' movies endpoint")
      .description("This endpoint returns a list of all the 'Pending' movies for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("pending" / "movies")
      .out(MediaOutputs.jsonMovieListOut)

  val userPendingTVShowsListEndpoint: PublicEndpoint[String, Unit, List[TVShow], Any] =
    userBaseEndpoint
      .name("User's 'Pending' TV shows endpoint")
      .description("This endpoint returns a list of all the 'Pending' TV shows for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("pending" / "tv_shows")
      .out(MediaOutputs.jsonTVShowListOut)

  val userPendingSeasonsListEndpoint: PublicEndpoint[String, Unit, List[Season], Any] =
    userBaseEndpoint
      .name("User's 'Pending' TV seasons endpoint")
      .description("This endpoint returns a list of all the 'Pending' TV seasons for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("pending" / "seasons")
      .out(MediaOutputs.jsonSeasonListOut)

  val userPendingVideogamesListEndpoint: PublicEndpoint[String, Unit, List[Videogame], Any] =
    userBaseEndpoint
      .name("User's 'Pending' videogames endpoint")
      .description("This endpoint returns a list of all the 'Pending' videogames for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("pending" / "videogames")
      .out(MediaOutputs.jsonVideogameListOut)

  val userPendingBooksListEndpoint: PublicEndpoint[String, Unit, List[Book], Any] =
    userBaseEndpoint
      .name("User's 'Pending' books endpoint")
      .description("This endpoint returns a list of all the 'Pending' books for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("pending" / "books")
      .out(MediaOutputs.jsonBookListOut)

}
