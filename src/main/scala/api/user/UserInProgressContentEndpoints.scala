package api.user

import sttp.tapir._

import modelClasses.media._
import modelClasses.social.MediaContentList
import api.common.Inputs.inputs
import api.common.Outputs.outputs

class UserInProgressContentEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val usersBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "users")

  private val userBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "user")

  val userInProgressListEndpoint: PublicEndpoint[String, Unit, MediaContentList, Any] =
    userBaseEndpoint
      .name("User's 'In Progress' elements endpoint")
      .description("This endpoint returns a list of all the 'In Progress' elements for a user")
      .get
      .in(inputs.pathUsername)
      .in("in_progress")
      .out(outputs.jsonMediaContentListOut)

  val userInProgressMoviesListEndpoint: PublicEndpoint[String, Unit, List[Movie], Any] =
    userBaseEndpoint
      .name("User's 'In Progress' movies endpoint")
      .description("This endpoint returns a list of all the 'In Progress' movies for a user")
      .get
      .in(inputs.pathUsername)
      .in("in_progress" / "movies")
      .out(outputs.jsonMovieListOut)

  val userInProgressTVShowsListEndpoint: PublicEndpoint[String, Unit, List[TVShow], Any] =
    userBaseEndpoint
      .name("User's 'In Progress' TV shows endpoint")
      .description("This endpoint returns a list of all the 'In Progress' TV shows for a user")
      .get
      .in(inputs.pathUsername)
      .in("in_progress" / "tv_shows")
      .out(outputs.jsonTVShowListOut)

  val userInProgressSeasonsListEndpoint: PublicEndpoint[String, Unit, List[Season], Any] =
    userBaseEndpoint
      .name("User's 'In Progress' TV seasons endpoint")
      .description("This endpoint returns a list of all the 'In Progress' TV seasons for a user")
      .get
      .in(inputs.pathUsername)
      .in("in_progress" / "seasons")
      .out(outputs.jsonSeasonListOut)

  val userInProgressVideogamesListEndpoint: PublicEndpoint[String, Unit, List[Videogame], Any] =
    userBaseEndpoint
      .name("User's 'In Progress' videogames endpoint")
      .description("This endpoint returns a list of all the 'In Progress' videogames for a user")
      .get
      .in(inputs.pathUsername)
      .in("in_progress" / "videogames")
      .out(outputs.jsonVideogameListOut)

  val userInProgressBooksListEndpoint: PublicEndpoint[String, Unit, List[Book], Any] =
    userBaseEndpoint
      .name("User's 'In Progress' books endpoint")
      .description("This endpoint returns a list of all the 'In Progress' books for a user")
      .get
      .in(inputs.pathUsername)
      .in("in_progress" / "books")
      .out(outputs.jsonBookListOut)

}
