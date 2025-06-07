package endpoints.app.search

import endpoints.EndpointsUtils.httpMethodEndpoint
import endpoints.io.inputs.Common.*
import endpoints.io.outputs.Common.*
import endpoints.io.outputs.IGDB.*
import domain.app.social.MediaList
import domain.app.user.User
import domain.errors.UserError.*
import domain.apis.tmdb.Common.Result
import endpoints.io.outputs.TMDB.jsonListOfResultOut
import endpoints.io.outputs.GoogleBooks.jsonSearchedBookListOut
import domain.apis.googleBooks.BooksRequests.SearchedBook
import domain.apis.igdb.VideogameRequests.VideogameFromIGDB
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

  val searchVideogame: PublicEndpoint[(String, Option[String]), UserError, List[VideogameFromIGDB], Any] =
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

  val searchMediaList: PublicEndpoint[(String, Option[String]), UserError, List[MediaList], Any] =
    searchBaseEndpoint(
      "searchMediaList",
      "This endpoint searches any list on the app based on text coincidence"
    )
      .in("list")
      .in(QueryInputs.querySortBy)
      .out(SocialOutputs.listOfMediaListsOutput)

  val searchUser: PublicEndpoint[(String, Option[String]), UserError, List[User], Any] =
    searchBaseEndpoint(
      "searchUser",
      "This endpoint searches any user on the app based on text coincidence"
    )
      .in("user")
      .in(QueryInputs.querySortBy)
      .out(UserOutputs.listOfUsersOutput)

}
