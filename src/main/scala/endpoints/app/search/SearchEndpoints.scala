package endpoints.app.search

import endpoints.EndpointsUtils.httpMethodEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.app.media.{Book, Movie, TvShow, Videogame}
import modelClasses.app.social.MediaList
import modelClasses.app.user.User
import modelClasses.errors.UserError.*
import sttp.tapir.*

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
      .in("movie")
      .in(QueryInputs.querySortBy)
      .out(MediaOutputs.listOfMoviesOutput)

  val searchTVShow: PublicEndpoint[(String, Option[String]), UserError, List[TvShow], Any] =
    searchBaseEndpoint(
      "Search TV show endpoint",
      "This endpoint searches any TV show on the app based on text coincidence"
    )
      .in("tv_show")
      .in(QueryInputs.querySortBy)
      .out(MediaOutputs.listOfTvShowsOutput)

  val searchVideogame: PublicEndpoint[(String, Option[String]), UserError, List[Videogame], Any] =
    searchBaseEndpoint(
      "Search videogame endpoint",
      "This endpoint searches any videogame on the app based on text coincidence"
    )
      .in("videogame")
      .in(QueryInputs.querySortBy)
      .out(MediaOutputs.listOfVideogamesOutput)

  val searchBook: PublicEndpoint[(String, Option[String]), UserError, List[Book], Any] =
    searchBaseEndpoint(
      "Search book endpoint",
      "This endpoint searches any book on the app based on text coincidence"
    )
      .in("book")
      .in(QueryInputs.querySortBy)
      .out(MediaOutputs.listOfBooksOutput)

  val searchMediaList: PublicEndpoint[String, UserError, List[MediaList], Any] =
    searchBaseEndpoint(
      "Search list endpoint",
      "This endpoint searches any list on the app based on text coincidence"
    )
      .in("list")
      .out(SocialOutputs.listOfMediaListsOutput)

  val searchUser: PublicEndpoint[String, UserError, List[User], Any] =
    searchBaseEndpoint(
      "Search user endpoint",
      "This endpoint searches any user on the app based on text coincidence"
    )
      .in("user")
      .out(UserOutputs.listOfUsersOutput)

}
