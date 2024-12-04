package endpoints.app.user

import sttp.tapir.*
import endpoints.EndpointsUtils.appBaseEndpoint
import endpoints.app.user.UserEndpointsUtils.userBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.ErrorInfo
import modelClasses.app.media.{Book, Movie, Season, TVShow, Videogame}
import modelClasses.ids.User.UserId

object UserPendingContentEndpoints {
  
  val userPendingListEndpoint: PublicEndpoint[UserId, ErrorInfo, List[Movie | TVShow | Season | Videogame | Book], Any] =
    userBaseEndpoint(
      "User's 'Pending' media content endpoint",
      "This endpoint returns a list of all the 'Pending' media content for a user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("pending")
      .out(MediaOutputs.listOfPendingSuccess)

  val userPendingMoviesListEndpoint: PublicEndpoint[UserId, ErrorInfo, List[Movie], Any] =
    userBaseEndpoint(
      "User's 'Pending' movies endpoint",
      "This endpoint returns a list of all the 'Pending' movies for a user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("pending" / "movies")
      .out(MediaOutputs.listOfMoviesSuccess)

  val userPendingTVShowsListEndpoint: PublicEndpoint[UserId, ErrorInfo, List[TVShow], Any] =
    userBaseEndpoint(
      "User's 'Pending' TV shows endpoint",
      "This endpoint returns a list of all the 'Pending' TV shows for a user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("pending" / "tv_shows")
      .out(MediaOutputs.listOfTvShowsSuccess)

  val userPendingSeasonsListEndpoint: PublicEndpoint[UserId, ErrorInfo, List[Season], Any] =
    userBaseEndpoint(
      "User's 'Pending' TV seasons endpoint",
      "This endpoint returns a list of all the 'Pending' TV seasons for a user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("pending" / "seasons")
      .out(MediaOutputs.listOfSeasonsSuccess)

  val userPendingVideogamesListEndpoint: PublicEndpoint[UserId, ErrorInfo, List[Videogame], Any] =
    userBaseEndpoint(
      "User's 'Pending' videogames endpoint",
      "This endpoint returns a list of all the 'Pending' videogames for a user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("pending" / "videogames")
      .out(MediaOutputs.listOfVideogamesSuccess)

  val userPendingBooksListEndpoint: PublicEndpoint[UserId, ErrorInfo, List[Book], Any] =
    userBaseEndpoint(
      "User's 'Pending' books endpoint",
      "This endpoint returns a list of all the 'Pending' books for a user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("pending" / "books")
      .out(MediaOutputs.listOfBooksSuccess)

}
