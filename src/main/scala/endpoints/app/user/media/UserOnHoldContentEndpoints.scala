package endpoints.app.user

import sttp.tapir.*
import endpoints.EndpointsUtils.appBaseEndpoint
import endpoints.app.user.UserEndpointsUtils.userBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.ErrorInfo
import modelClasses.app.media.{Book, Season, TVShow, Videogame}
import modelClasses.ids.User.UserId

object UserOnHoldContentEndpoints {
  
  val userOnHoldListEndpoint: PublicEndpoint[UserId, ErrorInfo, List[TVShow | Season | Videogame | Book], Any] =
    userBaseEndpoint(
      "User's 'On Hold' media content endpoint",
      "This endpoint returns a list of all the 'On Hold' media content for a user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("on_hold")
      .out(MediaOutputs.listOfProgressSuccess)

  val userOnHoldTVShowsListEndpoint: PublicEndpoint[UserId, ErrorInfo, List[TVShow], Any] =
    userBaseEndpoint(
      "User's 'On Hold' TV shows endpoint",
      "This endpoint returns a list of all the 'On Hold' TV shows for a user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("on_hold" / "tv_shows")
      .out(MediaOutputs.listOfTvShowsSuccess)

  val userOnHoldSeasonsListEndpoint: PublicEndpoint[UserId, ErrorInfo, List[Season], Any] =
    userBaseEndpoint(
      "User's 'On Hold' TV seasons endpoint",
      "This endpoint returns a list of all the 'On Hold' TV seasons for a user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("on_hold" / "seasons")
      .out(MediaOutputs.listOfSeasonsSuccess)

  val userOnHoldVideogamesListEndpoint: PublicEndpoint[UserId, ErrorInfo, List[Videogame], Any] =
    userBaseEndpoint(
      "User's 'On Hold' videogames endpoint",
      "This endpoint returns a list of all the 'On Hold' videogames for a user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("on_hold" / "videogames")
      .out(MediaOutputs.listOfVideogamesSuccess)

  val userOnHoldBooksListEndpoint: PublicEndpoint[UserId, ErrorInfo, List[Book], Any] =
    userBaseEndpoint(
      "User's 'On Hold' books endpoint",
      "This endpoint returns a list of all the 'On Hold' books for a user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("on_hold" / "books")
      .out(MediaOutputs.listOfBooksSuccess)

}
