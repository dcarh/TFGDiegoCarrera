package endpoints.app

import sttp.tapir._

import endpoints.common.Inputs._
import endpoints.common.Outputs._
import modelClasses.app.social.MediaContentList

object SearchEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val searchBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "search")

  val searchEndpoint: PublicEndpoint[(Option[String], String, Option[String]), Unit, MediaContentList, Any] =
    searchBaseEndpoint
      .name("Search endpoint")
      .description("This endpoint searches any element on the app based on text coincidence")
      .get
      .in(QueryInputs.querySortBy)
      .in(QueryInputs.querySearch)
      .in(QueryInputs.queryCategories)
      .out(SocialOutputs.jsonMediaContentListOut)

  // val searchMovieEndpoint: PublicEndpoint[(String, Option[String]), Unit, List[Movie], Any] =
  //   searchBaseEndpoint
  //     .name("Search movie endpoint")
  //     .description("This endpoint searches any movie on the app based on text coincidence")
  //     .get
  //     .in(pathSearch)
  //     .in(querySortBy)
  //     .out(outputs.jsonMovieListOut)
//
  // val searchTVShowEndpoint: PublicEndpoint[(String, Option[String]), Unit, List[TVShow], Any] =
  //   searchBaseEndpoint
  //     .name("Search TV show endpoint")
  //     .description("This endpoint searches any TV show on the app based on text coincidence")
  //     .get
  //     .in(pathSearch)
  //     .in(querySortBy)
  //     .out(outputs.jsonTVShowListOut)
//
  // val searchVideogameEndpoint: PublicEndpoint[(String, Option[String]), Unit, List[Videogame], Any] =
  //   searchBaseEndpoint
  //     .name("Search videogame endpoint")
  //     .description("This endpoint searches any videogame on the app based on text coincidence")
  //     .get
  //     .in(pathSearch)
  //     .in(querySortBy)
  //     .out(outputs.jsonVideogameListOut)
//
  // val searchBookEndpoint: PublicEndpoint[(String, Option[String]), Unit, List[Book], Any] =
  //   searchBaseEndpoint
  //     .name("Search book endpoint")
  //     .description("This endpoint searches any book on the app based on text coincidence")
  //     .get
  //     .in(pathSearch)
  //     .in(querySortBy)
  //     .out(outputs.jsonBookListOut)
//
  // val searchMediaContentListEndpoint: PublicEndpoint[(String, Option[String]), Unit, List[MediaContentList], Any] =
  //   searchBaseEndpoint
  //     .name("Search list endpoint")
  //     .description("This endpoint searches any list on the app based on text coincidence")
  //     .get
  //     .in(pathSearch)
  //     .in(querySortBy)
  //     .out(outputs.jsonListOfMediaContentListOut)
//
  // val searchUserEndpoint: PublicEndpoint[(String, Option[String]), Unit, List[User], Any] =
  //   searchBaseEndpoint
  //     .name("Search user endpoint")
  //     .description("This endpoint searches any user on the app based on text coincidence")
  //     .get
  //     .in(pathSearch)
  //     .in(querySortBy)
  //     .out(outputs.jsonUserListOut)

}
