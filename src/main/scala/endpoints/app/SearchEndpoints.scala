package endpoints.app

import sttp.tapir.*
import endpoints.EndpointsUtils.httpMethodEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.social.MediaContentList
import modelClasses.app.media.{Book, Movie, TvShow, Videogame}
import modelClasses.app.user.User

object SearchEndpoints {

  private val searchBaseEndpoint:
    (String, String) => PublicEndpoint[String, UserError, Unit, Any] =
      (name, description) => httpMethodEndpoint(name, description, "search", "GET")
        .in(QueryInputs.querySearch)

  val searchMovie: PublicEndpoint[(String, Option[String]), UserError, List[Movie], Any] =
    searchBaseEndpoint(
      "Search movie endpoint",
      "This endpoint searches any movie on the app based on text coincidence"
    )
      .in(QueryInputs.querySortBy)
      .out(MediaOutputs.listOfMoviesOutput)

  val searchTVShow: PublicEndpoint[(String, Option[String]), UserError, List[TvShow], Any] =
    searchBaseEndpoint(
      "Search TV show endpoint",
      "This endpoint searches any TV show on the app based on text coincidence"
    )
      .in(QueryInputs.querySortBy)
      .out(MediaOutputs.listOfTvShowsOutput)

  val searchVideogame: PublicEndpoint[(String, Option[String]), UserError, List[Videogame], Any] =
    searchBaseEndpoint(
      "Search videogame endpoint",
      "This endpoint searches any videogame on the app based on text coincidence"
    )
      .in(QueryInputs.querySortBy)
      .out(MediaOutputs.listOfVideogamesOutput)

  val searchBook: PublicEndpoint[(String, Option[String]), UserError, List[Book], Any] =
    searchBaseEndpoint(
      "Search book endpoint",
      "This endpoint searches any book on the app based on text coincidence"
    )
      .in(QueryInputs.querySortBy)
      .out(MediaOutputs.listOfBooksOutput)

  val searchMediaContentList: PublicEndpoint[String, UserError, List[MediaContentList], Any] =
    searchBaseEndpoint(
      "Search list endpoint",
      "This endpoint searches any list on the app based on text coincidence"
    )
      .out(SocialOutputs.listOfMediaContentListsOutput)

  val searchUser: PublicEndpoint[String, UserError, List[User], Any] =
    searchBaseEndpoint(
      "Search user endpoint",
      "This endpoint searches any user on the app based on text coincidence"
    )
      .out(UserOutputs.listOfUsersOutput)

}
