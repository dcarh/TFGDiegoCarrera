package endpoints.app.user

import sttp.tapir.*
import endpoints.app.user.UserEndpointsUtils.userBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.media.*
import modelClasses.ids.User.UserId

object UserAbandonedContentEndpoints {

  private val userAbandonedBaseEndpoint:
    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
      (name, description, method) => userBaseEndpoint(name, description, method)
        .in(PathInputs.pathUserId)
        .in("abandoned")
  
  val userAbandonedListEndpoint: PublicEndpoint[UserId, UserError, List[Movie | TVShow | Season | Episode | Videogame | Book], Any] =
    userAbandonedBaseEndpoint(
      "User's 'Abandoned' media content endpoint",
      "This endpoint returns a list of all the 'Abandoned' media content for a user",
      "GET"
    )
      .out(MediaOutputs.listOfAllMediaSuccess)
    // TODO: Decidir si la lógica de esta sección (y similares) va a ser devolver todo y filtrar/ordenar con query params o si hacerlo con múltiples endpoints como los de abajo (me decanto por lo primero)

//  val userAbandonedMoviesListEndpoint: PublicEndpoint[UserId, UserError, List[Movie], Any] =
//    userAbandonedBaseEndpoint(
//      "User's 'Abandoned' movies endpoint",
//      "This endpoint returns a list of all the 'Abandoned' movies for a user",
//      "GET"
//    )
//      .in("movies")
//      .out(MediaOutputs.listOfMoviesSuccess)
//
//  val userAbandonedTVShowsListEndpoint: PublicEndpoint[UserId, UserError, List[TVShow], Any] =
//    userAbandonedBaseEndpoint(
//      "User's 'Abandoned' TV shows endpoint",
//      "This endpoint returns a list of all the 'Abandoned' TV shows for a user",
//      "GET"
//    )
//      .in("tv_shows")
//      .out(MediaOutputs.listOfTvShowsSuccess)
//
//  val userAbandonedSeasonsListEndpoint: PublicEndpoint[UserId, UserError, List[Season], Any] =
//    userAbandonedBaseEndpoint(
//      "User's 'Abandoned' TV seasons endpoint",
//      "This endpoint returns a list of all the 'Abandoned' TV seasons for a user",
//      "GET"
//    )
//      .in("seasons")
//      .out(MediaOutputs.listOfSeasonsSuccess)
//  
//  val userAbandonedEpisodesListEndpoint: PublicEndpoint[UserId, UserError, List[Episode], Any] =
//    userAbandonedBaseEndpoint(
//      "User's 'Abandoned' TV episodes endpoint",
//      "This endpoint returns a list of all the 'Abandoned' TV episodes for a user",
//      "GET"
//    )
//      .in("episodes")
//      .out(MediaOutputs.listOfEpisodesSuccess)
//
//  val userAbandonedVideogamesListEndpoint: PublicEndpoint[UserId, UserError, List[Videogame], Any] =
//    userAbandonedBaseEndpoint(
//      "User's 'Abandoned' videogames endpoint",
//      "This endpoint returns a list of all the 'Abandoned' videogames for a user",
//      "GET"
//    )
//      .in("videogames")
//      .out(MediaOutputs.listOfVideogamesSuccess)
//
//  val userAbandonedBooksListEndpoint: PublicEndpoint[UserId, UserError, List[Book], Any] =
//    userAbandonedBaseEndpoint(
//      "User's 'Abandoned' books endpoint",
//      "This endpoint returns a list of all the 'Abandoned' books for a user",
//      "GET"
//    )
//      .in("books")
//      .out(MediaOutputs.listOfBooksSuccess)

}
