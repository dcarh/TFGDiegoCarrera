package api.user

import sttp.tapir._

import modelClasses.media._
import modelClasses.social.MediaContentList
import api.common.Inputs.inputs
import api.common.Outputs.outputs

class UserOnHoldContentEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val usersBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "users")

  private val userBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "user")

  val userOnHoldListEndpoint: PublicEndpoint[String, Unit, MediaContentList, Any] =
    userBaseEndpoint
      .name("User's 'On Hold' media content endpoint")
      .description("This endpoint returns a list of all the 'On Hold' media content for a user")
      .get
      .in(inputs.pathUsername)
      .in("on_hold")
      .out(outputs.jsonMediaContentListOut)

  val userOnHoldMoviesListEndpoint: PublicEndpoint[String, Unit, List[Movie], Any] =
    userBaseEndpoint
      .name("User's 'On Hold' movies endpoint")
      .description("This endpoint returns a list of all the 'On Hold' movies for a user")
      .get
      .in(inputs.pathUsername)
      .in("on_hold" / "movies")
      .out(outputs.jsonMovieListOut)

  val userOnHoldTVShowsListEndpoint: PublicEndpoint[String, Unit, List[TVShow], Any] =
    userBaseEndpoint
      .name("User's 'On Hold' TV shows endpoint")
      .description("This endpoint returns a list of all the 'On Hold' TV shows for a user")
      .get
      .in(inputs.pathUsername)
      .in("on_hold" / "tv_shows")
      .out(outputs.jsonTVShowListOut)

  val userOnHoldSeasonsListEndpoint: PublicEndpoint[String, Unit, List[Season], Any] =
    userBaseEndpoint
      .name("User's 'On Hold' TV seasons endpoint")
      .description("This endpoint returns a list of all the 'On Hold' TV seasons for a user")
      .get
      .in(inputs.pathUsername)
      .in("on_hold" / "seasons")
      .out(outputs.jsonSeasonListOut)

  val userOnHoldVideogamesListEndpoint: PublicEndpoint[String, Unit, List[Videogame], Any] =
    userBaseEndpoint
      .name("User's 'On Hold' videogames endpoint")
      .description("This endpoint returns a list of all the 'On Hold' videogames for a user")
      .get
      .in(inputs.pathUsername)
      .in("on_hold" / "videogames")
      .out(outputs.jsonVideogameListOut)

  val userOnHoldBooksListEndpoint: PublicEndpoint[String, Unit, List[Book], Any] =
    userBaseEndpoint
      .name("User's 'On Hold' books endpoint")
      .description("This endpoint returns a list of all the 'On Hold' books for a user")
      .get
      .in(inputs.pathUsername)
      .in("on_hold" / "books")
      .out(outputs.jsonBookListOut)

}
