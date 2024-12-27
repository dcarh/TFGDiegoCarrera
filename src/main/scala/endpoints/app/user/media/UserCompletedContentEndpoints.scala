package endpoints.app.user

import sttp.tapir.*
import endpoints.app.user.UserEndpointsUtils.userBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.media.{Book, Episode, Movie, Season, TVShow, Videogame}
import modelClasses.ids.User.UserId

object UserCompletedContentEndpoints {

  private val userCompletedBaseEndpoint:
    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
      (name, description, method) => userBaseEndpoint(name, description, method)
        .in(PathInputs.pathUserId)
        .in("completed")
  
  val userCompletedListEndpoint: PublicEndpoint[UserId, UserError, List[Movie | TVShow | Season | Episode | Videogame | Book], Any] =
    userCompletedBaseEndpoint(
      "User's 'Completed' media content endpoint",
      "This endpoint returns a list of all the 'Completed' media content for a user",
      "GET"
    )
      .out(MediaOutputs.listOfAllMediaSuccess)

//  val userCompletedMoviesListEndpoint: PublicEndpoint[UserId, UserError, List[Movie], Any] =
//    userCompletedBaseEndpoint(
//      "User's 'Completed' movies endpoint",
//      "This endpoint returns a list of all the 'Completed' movies for a user",
//      "GET"
//    )
//      .in("movies")
//      .out(MediaOutputs.listOfMoviesSuccess)
//
//  val userCompletedTVShowsListEndpoint: PublicEndpoint[UserId, UserError, List[TVShow], Any] =
//    userCompletedBaseEndpoint(
//      "User's 'Completed' TV shows endpoint",
//      "This endpoint returns a list of all the 'Completed' TV shows for a user",
//      "GET"
//    )
//      .in("tv_shows")
//      .out(MediaOutputs.listOfTvShowsSuccess)
//
//  val userCompletedSeasonsListEndpoint: PublicEndpoint[UserId, UserError, List[Season], Any] =
//    userCompletedBaseEndpoint(
//      "User's 'Completed' seasons endpoint",
//      "This endpoint returns a list of all the 'Completed' seasons for a user",
//      "GET"
//    )
//      .in("seasons")
//      .out(MediaOutputs.listOfSeasonsSuccess)
//
//  val userCompletedEpisodesListEndpoint: PublicEndpoint[UserId, UserError, List[Episode], Any] =
//    userCompletedBaseEndpoint(
//      "User's 'Completed' episodes endpoint",
//      "This endpoint returns a list of all the 'Completed' episodes for a user",
//      "GET"
//    )
//      .in("episodes")
//      .out(MediaOutputs.listOfEpisodesSuccess)
//
//  val userCompletedVideogamesListEndpoint: PublicEndpoint[UserId, UserError, List[Videogame], Any] =
//    userCompletedBaseEndpoint(
//      "User's 'Completed' videogames endpoint",
//      "This endpoint returns a list of all the 'Completed' videogames for a user",
//      "GET"
//    )
//      .in("videogames")
//      .out(MediaOutputs.listOfVideogamesSuccess)
//
//  val userCompletedBooksListEndpoint: PublicEndpoint[UserId, UserError, List[Book], Any] =
//    userCompletedBaseEndpoint(
//      "User's 'Completed' books endpoint",
//      "This endpoint returns a list of all the 'Completed' books for a user",
//      "GET"
//    )
//      .in("books")
//      .out(MediaOutputs.listOfBooksSuccess)

}
