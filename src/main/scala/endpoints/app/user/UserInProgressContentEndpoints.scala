package endpoints.app.user

import sttp.tapir.*
import endpoints.inputs.Common._
import endpoints.outputs.Common._
import modelClasses.app.media.{Book, Movie, Season, TVShow, Videogame}
import modelClasses.app.social.MediaContentList

object UserInProgressContentEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val usersBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "users")

  private val userBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "user")

  // TODO: No debe devolver un MediaContentList, sino un List[Movie | TVShow | Videogame ...]
  val userInProgressListEndpoint: PublicEndpoint[String, Unit, MediaContentList, Any] =
    userBaseEndpoint
      .name("User's 'In Progress' media content endpoint")
      .description("This endpoint returns a list of all the 'In Progress' media content for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("in_progress")
      .out(SocialOutputs.jsonMediaContentListOut)

  val userInProgressMoviesListEndpoint: PublicEndpoint[String, Unit, List[Movie], Any] =
    userBaseEndpoint
      .name("User's 'In Progress' movies endpoint")
      .description("This endpoint returns a list of all the 'In Progress' movies for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("in_progress" / "movies")
      .out(MediaOutputs.jsonMovieListOut)

  val userInProgressTVShowsListEndpoint: PublicEndpoint[String, Unit, List[TVShow], Any] =
    userBaseEndpoint
      .name("User's 'In Progress' TV shows endpoint")
      .description("This endpoint returns a list of all the 'In Progress' TV shows for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("in_progress" / "tv_shows")
      .out(MediaOutputs.jsonTVShowListOut)

  val userInProgressSeasonsListEndpoint: PublicEndpoint[String, Unit, List[Season], Any] =
    userBaseEndpoint
      .name("User's 'In Progress' TV seasons endpoint")
      .description("This endpoint returns a list of all the 'In Progress' TV seasons for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("in_progress" / "seasons")
      .out(MediaOutputs.jsonSeasonListOut)

  val userInProgressVideogamesListEndpoint: PublicEndpoint[String, Unit, List[Videogame], Any] =
    userBaseEndpoint
      .name("User's 'In Progress' videogames endpoint")
      .description("This endpoint returns a list of all the 'In Progress' videogames for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("in_progress" / "videogames")
      .out(MediaOutputs.jsonVideogameListOut)

  val userInProgressBooksListEndpoint: PublicEndpoint[String, Unit, List[Book], Any] =
    userBaseEndpoint
      .name("User's 'In Progress' books endpoint")
      .description("This endpoint returns a list of all the 'In Progress' books for a user")
      .get
      .in(PathInputs.pathUsername)
      .in("in_progress" / "books")
      .out(MediaOutputs.jsonBookListOut)

}
