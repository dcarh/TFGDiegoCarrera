package endpoints.app.user

import sttp.tapir.*
import endpoints.app.user.UserEndpointsUtils.userBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.media.{Book, Season, TVShow, Videogame}
import modelClasses.ids.User.UserId

object UserInProgressContentEndpoints {
  
  val userInProgressListEndpoint: PublicEndpoint[UserId, UserError, List[TVShow | Season | Videogame | Book], Any] =
    userBaseEndpoint(
      "User's 'In Progress' media content endpoint",
      "This endpoint returns a list of all the 'In Progress' media content for a user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("in_progress")
      .out(MediaOutputs.listOfProgressSuccess)

  val userInProgressTVShowsListEndpoint: PublicEndpoint[UserId, UserError, List[TVShow], Any] =
    userBaseEndpoint(
      "User's 'In Progress' TV shows endpoint",
      "This endpoint returns a list of all the 'In Progress' TV shows for a user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("in_progress" / "tv_shows")
      .out(MediaOutputs.listOfTvShowsSuccess)

  val userInProgressSeasonsListEndpoint: PublicEndpoint[UserId, UserError, List[Season], Any] =
    userBaseEndpoint(
      "User's 'In Progress' TV seasons endpoint",
      "This endpoint returns a list of all the 'In Progress' TV seasons for a user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("in_progress" / "seasons")
      .out(MediaOutputs.listOfSeasonsSuccess)

  val userInProgressVideogamesListEndpoint: PublicEndpoint[UserId, UserError, List[Videogame], Any] =
    userBaseEndpoint(
      "User's 'In Progress' videogames endpoint",
      "This endpoint returns a list of all the 'In Progress' videogames for a user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("in_progress" / "videogames")
      .out(MediaOutputs.listOfVideogamesSuccess)

  val userInProgressBooksListEndpoint: PublicEndpoint[UserId, UserError, List[Book], Any] =
    userBaseEndpoint(
      "User's 'In Progress' books endpoint",
      "This endpoint returns a list of all the 'In Progress' books for a user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("in_progress" / "books")
      .out(MediaOutputs.listOfBooksSuccess)

}
