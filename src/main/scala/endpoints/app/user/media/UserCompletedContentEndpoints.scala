package endpoints.app.user

import sttp.tapir.*
import endpoints.app.user.UserEndpointsUtils.userBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.media.{Book, Episode, Movie, Season, TVShow, Videogame}
import modelClasses.ids.User.UserId

object UserCompletedContentEndpoints {
  
  val userCompletedListEndpoint: PublicEndpoint[UserId, UserError, List[Movie | TVShow | Season | Episode | Videogame | Book], Any] =
    userBaseEndpoint(
      "User's 'Completed' media content endpoint",
      "This endpoint returns a list of all the 'Completed' media content for a user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("completed")
      .out(MediaOutputs.listOfAllMediaSuccess)

  val userCompletedMoviesListEndpoint: PublicEndpoint[UserId, UserError, List[Movie], Any] =
    userBaseEndpoint(
      "User's 'Completed' movies endpoint",
      "This endpoint returns a list of all the 'Completed' movies for a user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("completed" / "movies")
      .out(MediaOutputs.listOfMoviesSuccess)

  val userCompletedTVShowsListEndpoint: PublicEndpoint[UserId, UserError, List[TVShow], Any] =
    userBaseEndpoint(
      "User's 'Completed' TV shows endpoint",
      "This endpoint returns a list of all the 'Completed' TV shows for a user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("completed" / "tv_shows")
      .out(MediaOutputs.listOfTvShowsSuccess)

  val userCompletedSeasonsListEndpoint: PublicEndpoint[UserId, UserError, List[Season], Any] =
    userBaseEndpoint(
      "User's 'Completed' seasons endpoint",
      "This endpoint returns a list of all the 'Completed' seasons for a user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("completed" / "seasons")
      .out(MediaOutputs.listOfSeasonsSuccess)

  val userCompletedEpisodesListEndpoint: PublicEndpoint[UserId, UserError, List[Episode], Any] =
    userBaseEndpoint(
      "User's 'Completed' episodes endpoint",
      "This endpoint returns a list of all the 'Completed' episodes for a user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("completed" / "episodes")
      .out(MediaOutputs.listOfEpisodesSuccess)

  val userCompletedVideogamesListEndpoint: PublicEndpoint[UserId, UserError, List[Videogame], Any] =
    userBaseEndpoint(
      "User's 'Completed' videogames endpoint",
      "This endpoint returns a list of all the 'Completed' videogames for a user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("completed" / "videogames")
      .out(MediaOutputs.listOfVideogamesSuccess)

  val userCompletedBooksListEndpoint: PublicEndpoint[UserId, UserError, List[Book], Any] =
    userBaseEndpoint(
      "User's 'Completed' books endpoint",
      "This endpoint returns a list of all the 'Completed' books for a user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("completed" / "books")
      .out(MediaOutputs.listOfBooksSuccess)

}
