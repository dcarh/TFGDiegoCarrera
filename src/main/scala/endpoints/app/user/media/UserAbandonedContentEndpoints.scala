package endpoints.app.user

import sttp.tapir.*
import endpoints.EndpointsUtils.appBaseEndpoint
import endpoints.app.user.UserEndpointsUtils.userBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.ErrorInfo
import modelClasses.app.media.*
import modelClasses.ids.User.UserId

object UserAbandonedContentEndpoints {
  
  val userAbandonedListEndpoint: PublicEndpoint[UserId, ErrorInfo, List[Movie | TVShow | Season | Episode | Videogame | Book], Any] =
    userBaseEndpoint(
      "User's 'Abandoned' media content endpoint",
      "This endpoint returns a list of all the 'Abandoned' media content for a user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("abandoned")
      .out(MediaOutputs.listOfAllMediaSuccess)

  val userAbandonedMoviesListEndpoint: PublicEndpoint[UserId, ErrorInfo, List[Movie], Any] =
    userBaseEndpoint(
      "User's 'Abandoned' movies endpoint",
      "This endpoint returns a list of all the 'Abandoned' movies for a user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("abandoned" / "movies")
      .out(MediaOutputs.listOfMoviesSuccess)

  val userAbandonedTVShowsListEndpoint: PublicEndpoint[UserId, ErrorInfo, List[TVShow], Any] =
    userBaseEndpoint(
      "User's 'Abandoned' TV shows endpoint",
      "This endpoint returns a list of all the 'Abandoned' TV shows for a user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("abandoned" / "tv_shows")
      .out(MediaOutputs.listOfTvShowsSuccess)

  val userAbandonedSeasonsListEndpoint: PublicEndpoint[UserId, ErrorInfo, List[Season], Any] =
    userBaseEndpoint(
      "User's 'Abandoned' TV seasons endpoint",
      "This endpoint returns a list of all the 'Abandoned' TV seasons for a user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("abandoned" / "seasons")
      .out(MediaOutputs.listOfSeasonsSuccess)
  
  val userAbandonedEpisodesListEndpoint: PublicEndpoint[UserId, ErrorInfo, List[Episode], Any] =
    userBaseEndpoint(
      "User's 'Abandoned' TV episodes endpoint",
      "This endpoint returns a list of all the 'Abandoned' TV episodes for a user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("abandoned" / "episodes")
      .out(MediaOutputs.listOfEpisodesSuccess)

  val userAbandonedVideogamesListEndpoint: PublicEndpoint[UserId, ErrorInfo, List[Videogame], Any] =
    userBaseEndpoint(
      "User's 'Abandoned' videogames endpoint",
      "This endpoint returns a list of all the 'Abandoned' videogames for a user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("abandoned" / "videogames")
      .out(MediaOutputs.listOfVideogamesSuccess)

  val userAbandonedBooksListEndpoint: PublicEndpoint[UserId, ErrorInfo, List[Book], Any] =
    userBaseEndpoint(
      "User's 'Abandoned' books endpoint",
      "This endpoint returns a list of all the 'Abandoned' books for a user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("abandoned" / "books")
      .out(MediaOutputs.listOfBooksSuccess)

}
