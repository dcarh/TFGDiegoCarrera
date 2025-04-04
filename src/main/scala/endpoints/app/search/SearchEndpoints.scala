package endpoints.app.search

import endpoints.EndpointsUtils.httpMethodEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import endpoints.outputs.IGDB.*
import modelClasses.app.media.{Book, Movie, TvShow, Videogame}
import modelClasses.app.social.MediaList
import modelClasses.app.user.User
import modelClasses.errors.UserError.*
import modelClasses.tmdb.Common.{Result, Results}
import endpoints.outputs.TMDB.{jsonListOfResultOut, jsonResultsOut}
import endpoints.outputs.GoogleBooks.{jsonSearchedBookListOut, listOfSearchedBooks}
import modelClasses.googleBooks.BooksRequests.{ListOfSearchedBooks, SearchedBook}
import modelClasses.igdb.VideogameRequests.VideogameAllFields
import sttp.tapir.*

object SearchEndpoints {

  private val searchBaseEndpoint:
    (String, String) => PublicEndpoint[String, UserError, Unit, Any] =
      (name, description) => httpMethodEndpoint(name, description, "search", "GET")
        .in(QueryInputs.querySearch)

  val searchMovie: PublicEndpoint[(String, Option[String]), UserError, List[Result], Any] =
    searchBaseEndpoint(
      "searchMovie",
      "This endpoint searches any movie on the app based on text coincidence"
    )
      .in("movie")
      .in(QueryInputs.querySortBy)
      .out(jsonListOfResultOut)

  val searchTvShow: PublicEndpoint[(String, Option[String]), UserError, List[Result], Any] =
    searchBaseEndpoint(
      "searchTvShow",
      "This endpoint searches any TV show on the app based on text coincidence"
    )
      .in("tv_show")
      .in(QueryInputs.querySortBy)
      .out(jsonListOfResultOut)

  val searchVideogame: PublicEndpoint[(String, Option[String]), UserError, List[VideogameAllFields], Any] =
    searchBaseEndpoint(
      "searchVideogame",
      "This endpoint searches any videogame on the app based on text coincidence"
    )
      .in("videogame")
      .in(QueryInputs.querySortBy)
      .out(jsonListRequestedVideogameAllFieldsOut)

  val searchBook: PublicEndpoint[(String, Option[String]), UserError, List[SearchedBook], Any] =
    searchBaseEndpoint(
      "searchBook",
      "This endpoint searches any book on the app based on text coincidence"
    )
      .in("book")
      .in(QueryInputs.querySortBy)
      .out(jsonSearchedBookListOut)

  val searchMediaList: PublicEndpoint[String, UserError, List[MediaList], Any] =
    searchBaseEndpoint(
      "searchMediaList",
      "This endpoint searches any list on the app based on text coincidence"
    )
      .in("list")
      .out(SocialOutputs.listOfMediaListsOutput)

  val searchUser: PublicEndpoint[String, UserError, List[User], Any] =
    searchBaseEndpoint(
      "searchUser",
      "This endpoint searches any user on the app based on text coincidence"
    )
      .in("user")
      .out(UserOutputs.listOfUsersOutput)

}
