package api

import io.circe.generic.auto._
import modelClasses.{User,Element, Movie, TVShow, Season, Episode, Videogame, Book, ElementList}
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._

class SearchEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val pathMovies: EndpointInput[String] =
    path[String]("movies")

  private val pathTVShows: EndpointInput[String] =
    path[String]("tv_shows")

  private val pathSeasons: EndpointInput[String] =
    path[String]("seasons")

  private val pathEpisodes: EndpointInput[String] =
    path[String]("episodes")

  private val pathVideogames: EndpointInput[String] =
    path[String]("videogames")

  private val pathBooks: EndpointInput[String] =
    path[String]("books")

  private val pathArticles: EndpointInput[String] =
    path[String]("articles")

  private val pathLists: EndpointInput[String] =
    path[String]("lists")

  private val pathUsers: EndpointInput[String] =
    path[String]("users")

  private val queryType: EndpointInput[String] =
    query[String]("type").description("Tipo de elemento a buscar")
  // TODO: Añadir validator para que solo pueda ser: all, movie, tv_show, season, episode, videogame, book

  private val queryOrderBy: EndpointInput[String] =
    query[String]("order_by").description("Ordenar por")

   private val jsonElementListOut: EndpointOutput[ElementList] =
     jsonBody[ElementList]

  private val jsonMovieListOut: EndpointOutput[List[Movie]] =
    jsonBody[List[Movie]]

  private val jsonTVShowListOut: EndpointOutput[List[TVShow]] =
    jsonBody[List[TVShow]]

  private val jsonSeasonListOut: EndpointOutput[List[Season]] =
    jsonBody[List[Season]]

  private val jsonEpisodeListOut: EndpointOutput[List[Episode]] =
    jsonBody[List[Episode]]

  private val jsonVideogameListOut: EndpointOutput[List[Videogame]] =
    jsonBody[List[Videogame]]

  private val jsonBookListOut: EndpointOutput[List[Book]] =
    jsonBody[List[Book]]

  private val jsonUserListOut: EndpointOutput[List[User]] =
    jsonBody[List[User]]

  private val jsonListOfElementListOut: EndpointOutput[List[ElementList]] =
    jsonBody[List[ElementList]]

  private val searchBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "search")


  val searchEndpoint: PublicEndpoint[(String, String), Unit, ElementList, Any] =
    searchBaseEndpoint
      .name("Search endpoint")
      .description("This endpoint searches any element on the app based on text coincidence")
      .get
      .in(queryType)
      .in(queryOrderBy)
      .out(jsonElementListOut)

  val searchMovieEndpoint: PublicEndpoint[(String, String), Unit, List[Movie], Any] =
    searchBaseEndpoint
      .name("Search movie endpoint")
      .description("This endpoint searches any movie on the app based on text coincidence")
      .get
      .in(pathMovies)
      .in(queryOrderBy)
      .out(jsonMovieListOut)

  val searchTVShowEndpoint: PublicEndpoint[(String, String), Unit, List[TVShow], Any] =
    searchBaseEndpoint
      .name("Search TV show endpoint")
      .description("This endpoint searches any TV show on the app based on text coincidence")
      .get
      .in(pathTVShows)
      .in(queryOrderBy)
      .out(jsonTVShowListOut)

  //val searchSeasonEndpoint: PublicEndpoint[(String, String), Unit, List[Season], Any] =
  //  searchBaseEndpoint
  //    .name("Search TV season endpoint")
  //    .description("This endpoint searches any TV season on the app based on text coincidence")
  //    .get
  //    .in(pathEpisodes)
  //    .in(queryOrderBy)
  //    .out(jsonSeasonListOut)
//
  //val searchEpisodeEndpoint: PublicEndpoint[(String, String), Unit, List[Episode], Any] =
  //  searchBaseEndpoint
  //    .name("Search TV episode endpoint")
  //    .description("This endpoint searches any TV episode on the app based on text coincidence")
  //    .get
  //    .in(pathSeasons)
  //    .in(queryOrderBy)
  //    .out(jsonEpisodeListOut)

  val searchVideogameEndpoint: PublicEndpoint[(String, String), Unit, List[Videogame], Any] =
    searchBaseEndpoint
      .name("Search videogame endpoint")
      .description("This endpoint searches any videogame on the app based on text coincidence")
      .get
      .in(pathVideogames)
      .in(queryOrderBy)
      .out(jsonVideogameListOut)

  val searchBookEndpoint: PublicEndpoint[(String, String), Unit, List[Book], Any] =
    searchBaseEndpoint
      .name("Search book endpoint")
      .description("This endpoint searches any book on the app based on text coincidence")
      .get
      .in(pathBooks)
      .in(queryOrderBy)
      .out(jsonBookListOut)

  val searchElementListEndpoint: PublicEndpoint[(String, String), Unit, List[ElementList], Any] =
    searchBaseEndpoint
      .name("Search list endpoint")
      .description("This endpoint searches any list on the app based on text coincidence")
      .get
      .in(pathLists)
      .in(queryOrderBy)
      .out(jsonListOfElementListOut)

  val searchUserEndpoint: PublicEndpoint[(String, String), Unit, List[User], Any] =
    searchBaseEndpoint
      .name("Search user endpoint")
      .description("This endpoint searches any user on the app based on text coincidence")
      .get
      .in(pathUsers)
      .in(queryOrderBy)
      .out(jsonUserListOut)

}
