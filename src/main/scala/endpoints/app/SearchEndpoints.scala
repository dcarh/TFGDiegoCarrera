package endpoints.app

import sttp.tapir.*
import endpoints.EndpointsUtils.httpMethodEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.social.MediaContentList
import modelClasses.app.media.{Book, Movie, TVShow, Videogame}
import modelClasses.app.user.User

object SearchEndpoints {

  private val searchBaseEndpoint:
    (String, String, String) => PublicEndpoint[Unit, UserError, Unit, Any] =
      (name, description, method) => httpMethodEndpoint(name, description, "search", method)

  val searchMovieEndpoint: PublicEndpoint[(String, Option[String]), UserError, List[Movie], Any] =
    searchBaseEndpoint(
      "Search movie endpoint",
      "This endpoint searches any movie on the app based on text coincidence",
      "GET"
    )
      .in(QueryInputs.querySearch)
      .in(QueryInputs.querySortBy)
      .out(MediaOutputs.listOfMoviesSuccess)

  val searchTVShowEndpoint: PublicEndpoint[(String, Option[String]), UserError, List[TVShow], Any] =
    searchBaseEndpoint(
      "Search TV show endpoint",
      "This endpoint searches any TV show on the app based on text coincidence",
      "GET"
    )
      .in(QueryInputs.querySearch)
      .in(QueryInputs.querySortBy)
      .out(MediaOutputs.listOfTvShowsSuccess)

  val searchVideogameEndpoint: PublicEndpoint[(String, Option[String]), UserError, List[Videogame], Any] =
    searchBaseEndpoint(
      "Search videogame endpoint",
      "This endpoint searches any videogame on the app based on text coincidence",
      "GET"
    )
      .in(QueryInputs.querySearch)
      .in(QueryInputs.querySortBy)
      .out(MediaOutputs.listOfVideogamesSuccess)

  val searchBookEndpoint: PublicEndpoint[(String, Option[String]), UserError, List[Book], Any] =
    searchBaseEndpoint(
      "Search book endpoint",
      "This endpoint searches any book on the app based on text coincidence",
      "GET"
    )
      .in(QueryInputs.querySearch)
      .in(QueryInputs.querySortBy)
      .out(MediaOutputs.listOfBooksSuccess)

  val searchMediaContentListEndpoint: PublicEndpoint[String, UserError, List[MediaContentList], Any] =
    searchBaseEndpoint(
      "Search list endpoint",
      "This endpoint searches any list on the app based on text coincidence",
      "GET"
    )
      .in(QueryInputs.querySearch)
      .out(SocialOutputs.listOfMediaContentListSuccess)

  val searchUserEndpoint: PublicEndpoint[String, UserError, List[User], Any] =
    searchBaseEndpoint(
      "Search user endpoint",
      "This endpoint searches any user on the app based on text coincidence",
      "GET"
    )
      .in(QueryInputs.querySearch)
      .out(UserOutputs.listOfUsersSuccess)

}
