package endpoints.app.user

import sttp.tapir.*
import endpoints.app.user.UserEndpointsUtils.userBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.media.{Book, Movie, Season, TVShow, Videogame}
import modelClasses.ids.User.UserId

object UserPendingContentEndpoints {

  private val userPendingBaseEndpoint:
    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
      (name, description, method) => userBaseEndpoint(name, description, method)
        .in(PathInputs.pathUserId)
        .in("pending")
  
  val userPendingListEndpoint: PublicEndpoint[UserId, UserError, List[Movie | TVShow | Season | Videogame | Book], Any] =
    userPendingBaseEndpoint(
      "User's 'Pending' media content endpoint",
      "This endpoint returns a list of all the 'Pending' media content for a user",
      "GET"
    )
      .out(MediaOutputs.listOfPendingSuccess)

//  val userPendingMoviesListEndpoint: PublicEndpoint[UserId, UserError, List[Movie], Any] =
//    userPendingBaseEndpoint(
//      "User's 'Pending' movies endpoint",
//      "This endpoint returns a list of all the 'Pending' movies for a user",
//      "GET"
//    )
//      .in("movies")
//      .out(MediaOutputs.listOfMoviesSuccess)
//
//  val userPendingTVShowsListEndpoint: PublicEndpoint[UserId, UserError, List[TVShow], Any] =
//    userPendingBaseEndpoint(
//      "User's 'Pending' TV shows endpoint",
//      "This endpoint returns a list of all the 'Pending' TV shows for a user",
//      "GET"
//    )
//      .in("tv_shows")
//      .out(MediaOutputs.listOfTvShowsSuccess)
//
//  val userPendingSeasonsListEndpoint: PublicEndpoint[UserId, UserError, List[Season], Any] =
//    userPendingBaseEndpoint(
//      "User's 'Pending' TV seasons endpoint",
//      "This endpoint returns a list of all the 'Pending' TV seasons for a user",
//      "GET"
//    )
//      .in("seasons")
//      .out(MediaOutputs.listOfSeasonsSuccess)
//
//  val userPendingVideogamesListEndpoint: PublicEndpoint[UserId, UserError, List[Videogame], Any] =
//    userPendingBaseEndpoint(
//      "User's 'Pending' videogames endpoint",
//      "This endpoint returns a list of all the 'Pending' videogames for a user",
//      "GET"
//    )
//      .in("videogames")
//      .out(MediaOutputs.listOfVideogamesSuccess)
//
//  val userPendingBooksListEndpoint: PublicEndpoint[UserId, UserError, List[Book], Any] =
//    userPendingBaseEndpoint(
//      "User's 'Pending' books endpoint",
//      "This endpoint returns a list of all the 'Pending' books for a user",
//      "GET"
//    )
//      .in("books")
//      .out(MediaOutputs.listOfBooksSuccess)

}
