package endpoints.app

import sttp.tapir._

import endpoints.inputs.Common._
import endpoints.outputs.Common._
import modelClasses.app.social.MediaContentList
import modelClasses.app.media.{Book, Movie, TVShow, Videogame}
import modelClasses.app.user.User

object SearchEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val searchBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "search")

  // val searchEndpoint: PublicEndpoint[(Option[String], String, Option[String]), Unit, MediaContentList, Any] =
  //   searchBaseEndpoint
  //     .name("Search endpoint")
  //     .description("This endpoint searches any element on the app based on text coincidence")
  //     .get
  //     .in(QueryInputs.querySortBy)
  //     .in(QueryInputs.querySearch)
  //     .in(QueryInputs.queryCategories)
  //     .out(SocialOutputs.jsonMediaContentListOut)

  // TODO: Esto está mal pensado. El endpoint de arriba no devuelve cualquier elemeneto de la App, sino una
  // TODO: MediaContentList. Habría que descomentar los métodos de abajo, que son más apropiados, o crear un tipo que
  // TODO: englobe todo lo que puede devolver este endpoint y que la búsqueda no sea específica de un solo tipo de
  // TODO: elemento.

  val searchMovieEndpoint: PublicEndpoint[(String, Option[String]), Unit, List[Movie], Any] =
    searchBaseEndpoint
      .name("Search movie endpoint")
      .description("This endpoint searches any movie on the app based on text coincidence")
      .get
      .in(QueryInputs.querySearch)
      .in(QueryInputs.querySortBy)
      .out(MediaOutputs.jsonMovieListOut)

  val searchTVShowEndpoint: PublicEndpoint[(String, Option[String]), Unit, List[TVShow], Any] =
    searchBaseEndpoint
      .name("Search TV show endpoint")
      .description("This endpoint searches any TV show on the app based on text coincidence")
      .get
      .in(QueryInputs.querySearch)
      .in(QueryInputs.querySortBy)
      .out(MediaOutputs.jsonTVShowListOut)

  val searchVideogameEndpoint: PublicEndpoint[(String, Option[String]), Unit, List[Videogame], Any] =
    searchBaseEndpoint
      .name("Search videogame endpoint")
      .description("This endpoint searches any videogame on the app based on text coincidence")
      .get
      .in(QueryInputs.querySearch)
      .in(QueryInputs.querySortBy)
      .out(MediaOutputs.jsonVideogameListOut)

  val searchBookEndpoint: PublicEndpoint[(String, Option[String]), Unit, List[Book], Any] =
    searchBaseEndpoint
      .name("Search book endpoint")
      .description("This endpoint searches any book on the app based on text coincidence")
      .get
      .in(QueryInputs.querySearch)
      .in(QueryInputs.querySortBy)
      .out(MediaOutputs.jsonBookListOut)

  val searchMediaContentListEndpoint: PublicEndpoint[(String, Option[String]), Unit, List[MediaContentList], Any] =
    searchBaseEndpoint
      .name("Search list endpoint")
      .description("This endpoint searches any list on the app based on text coincidence")
      .get
      .in(QueryInputs.querySearch)
      .in(QueryInputs.querySortBy)
      .out(SocialOutputs.jsonListOfMediaContentListOut)

  val searchUserEndpoint: PublicEndpoint[(String, Option[String]), Unit, List[User], Any] =
    searchBaseEndpoint
      .name("Search user endpoint")
      .description("This endpoint searches any user on the app based on text coincidence")
      .get
      .in(QueryInputs.querySearch)
      .in(QueryInputs.querySortBy)
      .out(UserOutputs.jsonUserListOut)

}
