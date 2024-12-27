package endpoints.app.user

import sttp.tapir.*
import endpoints.app.user.UserEndpointsUtils.userBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.media.{Book, Season, TVShow, Videogame}
import modelClasses.ids.User.UserId

object UserOnHoldContentEndpoints {

  private val userOnHoldBaseEndpoint:
    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
      (name, description, method) => userBaseEndpoint(name, description, method)
        .in(PathInputs.pathUserId)
        .in("on_hold")
  
  val userOnHoldListEndpoint: PublicEndpoint[UserId, UserError, List[TVShow | Season | Videogame | Book], Any] =
    userOnHoldBaseEndpoint(
      "User's 'On Hold' media content endpoint",
      "This endpoint returns a list of all the 'On Hold' media content for a user",
      "GET"
    )
      .out(MediaOutputs.listOfProgressSuccess)

//  val userOnHoldTVShowsListEndpoint: PublicEndpoint[UserId, UserError, List[TVShow], Any] =
//    userOnHoldBaseEndpoint(
//      "User's 'On Hold' TV shows endpoint",
//      "This endpoint returns a list of all the 'On Hold' TV shows for a user",
//      "GET"
//    )
//      .in("tv_shows")
//      .out(MediaOutputs.listOfTvShowsSuccess)
//
//  val userOnHoldSeasonsListEndpoint: PublicEndpoint[UserId, UserError, List[Season], Any] =
//    userOnHoldBaseEndpoint(
//      "User's 'On Hold' TV seasons endpoint",
//      "This endpoint returns a list of all the 'On Hold' TV seasons for a user",
//      "GET"
//    )
//      .in("seasons")
//      .out(MediaOutputs.listOfSeasonsSuccess)
//
//  val userOnHoldVideogamesListEndpoint: PublicEndpoint[UserId, UserError, List[Videogame], Any] =
//    userOnHoldBaseEndpoint(
//      "User's 'On Hold' videogames endpoint",
//      "This endpoint returns a list of all the 'On Hold' videogames for a user",
//      "GET"
//    )
//      .in("videogames")
//      .out(MediaOutputs.listOfVideogamesSuccess)
//
//  val userOnHoldBooksListEndpoint: PublicEndpoint[UserId, UserError, List[Book], Any] =
//    userOnHoldBaseEndpoint(
//      "User's 'On Hold' books endpoint",
//      "This endpoint returns a list of all the 'On Hold' books for a user",
//      "GET"
//    )
//      .in("books")
//      .out(MediaOutputs.listOfBooksSuccess)

}
