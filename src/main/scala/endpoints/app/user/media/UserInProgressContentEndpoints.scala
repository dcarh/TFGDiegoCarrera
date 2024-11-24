package endpoints.app.user

import sttp.tapir.*
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.app.media.{Book, Movie, Season, TVShow, Videogame}
import modelClasses.app.social.MediaContentList
import modelClasses.ids.User.UserId

object UserInProgressContentEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val usersBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "users")

  private val userBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "user")

  // TODO: No debe devolver un MediaContentList, sino un List[Movie | TVShow | Videogame ...]
  
  val userInProgressListEndpoint: PublicEndpoint[UserId, Unit, List[TVShow | Season | Videogame | Book], Any] =
    userBaseEndpoint
      .name("User's 'In Progress' media content endpoint")
      .description("This endpoint returns a list of all the 'In Progress' media content for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("in_progress")
      .out(MediaOutputs.jsonMediaListOut1)

  val userInProgressTVShowsListEndpoint: PublicEndpoint[UserId, Unit, List[TVShow], Any] =
    userBaseEndpoint
      .name("User's 'In Progress' TV shows endpoint")
      .description("This endpoint returns a list of all the 'In Progress' TV shows for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("in_progress" / "tv_shows")
      .out(MediaOutputs.jsonTVShowListOut)

  val userInProgressSeasonsListEndpoint: PublicEndpoint[UserId, Unit, List[Season], Any] =
    userBaseEndpoint
      .name("User's 'In Progress' TV seasons endpoint")
      .description("This endpoint returns a list of all the 'In Progress' TV seasons for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("in_progress" / "seasons")
      .out(MediaOutputs.jsonSeasonListOut)

  val userInProgressVideogamesListEndpoint: PublicEndpoint[UserId, Unit, List[Videogame], Any] =
    userBaseEndpoint
      .name("User's 'In Progress' videogames endpoint")
      .description("This endpoint returns a list of all the 'In Progress' videogames for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("in_progress" / "videogames")
      .out(MediaOutputs.jsonVideogameListOut)

  val userInProgressBooksListEndpoint: PublicEndpoint[UserId, Unit, List[Book], Any] =
    userBaseEndpoint
      .name("User's 'In Progress' books endpoint")
      .description("This endpoint returns a list of all the 'In Progress' books for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("in_progress" / "books")
      .out(MediaOutputs.jsonBookListOut)

}
