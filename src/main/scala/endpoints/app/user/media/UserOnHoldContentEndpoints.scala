package endpoints.app.user

import sttp.tapir.*
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.app.media.{Book, Movie, Season, TVShow, Videogame}
import modelClasses.app.social.MediaContentList
import modelClasses.ids.User.UserId

object UserOnHoldContentEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val usersBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "users")

  private val userBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "user")
  
  val userOnHoldListEndpoint: PublicEndpoint[UserId, Unit, List[TVShow | Season | Videogame | Book], Any] =
    userBaseEndpoint
      .name("User's 'On Hold' media content endpoint")
      .description("This endpoint returns a list of all the 'On Hold' media content for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("on_hold")
      .out(MediaOutputs.jsonMediaListOut1)

  val userOnHoldTVShowsListEndpoint: PublicEndpoint[UserId, Unit, List[TVShow], Any] =
    userBaseEndpoint
      .name("User's 'On Hold' TV shows endpoint")
      .description("This endpoint returns a list of all the 'On Hold' TV shows for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("on_hold" / "tv_shows")
      .out(MediaOutputs.jsonTVShowListOut)

  val userOnHoldSeasonsListEndpoint: PublicEndpoint[UserId, Unit, List[Season], Any] =
    userBaseEndpoint
      .name("User's 'On Hold' TV seasons endpoint")
      .description("This endpoint returns a list of all the 'On Hold' TV seasons for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("on_hold" / "seasons")
      .out(MediaOutputs.jsonSeasonListOut)

  val userOnHoldVideogamesListEndpoint: PublicEndpoint[UserId, Unit, List[Videogame], Any] =
    userBaseEndpoint
      .name("User's 'On Hold' videogames endpoint")
      .description("This endpoint returns a list of all the 'On Hold' videogames for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("on_hold" / "videogames")
      .out(MediaOutputs.jsonVideogameListOut)

  val userOnHoldBooksListEndpoint: PublicEndpoint[UserId, Unit, List[Book], Any] =
    userBaseEndpoint
      .name("User's 'On Hold' books endpoint")
      .description("This endpoint returns a list of all the 'On Hold' books for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("on_hold" / "books")
      .out(MediaOutputs.jsonBookListOut)

}
