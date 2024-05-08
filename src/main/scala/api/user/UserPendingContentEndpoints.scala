package api.user

import sttp.tapir._

import modelClasses.media._
import modelClasses.social.MediaContentList
import api.common.Inputs.inputs
import api.common.Outputs.outputs

class UserPendingContentEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val usersBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "users")

  private val userBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "user")
    
  val userPendingListEndpoint: PublicEndpoint[String, Unit, MediaContentList, Any] =
    userBaseEndpoint
      .name("User's 'Pending' media content endpoint")
      .description("This endpoint returns a list of all the 'Pending' media content for a user")
      .get
      .in(inputs.pathUsername)
      .in("pending")
      .out(outputs.jsonMediaContentListOut)

  val userPendingMoviesListEndpoint: PublicEndpoint[String, Unit, List[Movie], Any] =
    userBaseEndpoint
      .name("User's 'Pending' movies endpoint")
      .description("This endpoint returns a list of all the 'Pending' movies for a user")
      .get
      .in(inputs.pathUsername)
      .in("pending" / "movies")
      .out(outputs.jsonMovieListOut)

  val userPendingTVShowsListEndpoint: PublicEndpoint[String, Unit, List[TVShow], Any] =
    userBaseEndpoint
      .name("User's 'Pending' TV shows endpoint")
      .description("This endpoint returns a list of all the 'Pending' TV shows for a user")
      .get
      .in(inputs.pathUsername)
      .in("pending" / "tv_shows")
      .out(outputs.jsonTVShowListOut)

  val userPendingSeasonsListEndpoint: PublicEndpoint[String, Unit, List[Season], Any] =
    userBaseEndpoint
      .name("User's 'Pending' TV seasons endpoint")
      .description("This endpoint returns a list of all the 'Pending' TV seasons for a user")
      .get
      .in(inputs.pathUsername)
      .in("pending" / "seasons")
      .out(outputs.jsonSeasonListOut)

  val userPendingVideogamesListEndpoint: PublicEndpoint[String, Unit, List[Videogame], Any] =
    userBaseEndpoint
      .name("User's 'Pending' videogames endpoint")
      .description("This endpoint returns a list of all the 'Pending' videogames for a user")
      .get
      .in(inputs.pathUsername)
      .in("pending" / "videogames")
      .out(outputs.jsonVideogameListOut)

  val userPendingBooksListEndpoint: PublicEndpoint[String, Unit, List[Book], Any] =
    userBaseEndpoint
      .name("User's 'Pending' books endpoint")
      .description("This endpoint returns a list of all the 'Pending' books for a user")
      .get
      .in(inputs.pathUsername)
      .in("pending" / "books")
      .out(outputs.jsonBookListOut)

}
