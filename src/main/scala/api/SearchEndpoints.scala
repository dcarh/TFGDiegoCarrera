package api

import io.circe.generic.auto._
import model.{User,Element, Movie, TVShow, Season, Episode, Videogame, Book, ElementList, Log, Comment, Article,
  Settings, Review, ErrorInfo}
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import sttp.tapir.model.UsernamePassword
import sttp.model.StatusCode

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

  private val jsonElementListOut: EndpointOutput[List[Element]] =
    jsonBody[List[Element]]

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

  private val jsonArticleListOut: EndpointOutput[List[Article]] =
    jsonBody[List[Article]]

  private val jsonUserListOut: EndpointOutput[List[User]] =
    jsonBody[List[User]]

  private val jsonListOfElementListOut: EndpointOutput[List[ElementList]] =
    jsonBody[List[ElementList]]

  private val searchBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "search")


  val searchEnpoint: PublicEndpoint[(String, String), Unit, List[Element], Any] =
    searchBaseEndpoint
      .in(queryType)
      .in(queryOrderBy)
      .out(jsonElementListOut)

  val searchMovieEndpoint: PublicEndpoint[(String, String), Unit, List[Movie], Any] =
    searchBaseEndpoint
      .in(pathMovies)
      .in(queryOrderBy)
      .out(jsonMovieListOut)

  val searchTVShowEndpoint: PublicEndpoint[(String, String), Unit, List[TVShow], Any] =
    searchBaseEndpoint
      .in(pathTVShows)
      .in(queryOrderBy)
      .out(jsonTVShowListOut)

  val searchSeasonEndpoint: PublicEndpoint[(String, String), Unit, List[Season], Any] =
    searchBaseEndpoint
      .in(pathEpisodes)
      .in(queryOrderBy)
      .out(jsonSeasonListOut)

  val searchEpisodeEndpoint: PublicEndpoint[(String, String), Unit, List[Episode], Any] =
    searchBaseEndpoint
      .in(pathSeasons)
      .in(queryOrderBy)
      .out(jsonEpisodeListOut)

  val searchVideogameEndpoint: PublicEndpoint[(String, String), Unit, List[Videogame], Any] =
    searchBaseEndpoint
      .in(pathVideogames)
      .in(queryOrderBy)
      .out(jsonVideogameListOut)

  val searchBookEndpoint: PublicEndpoint[(String, String), Unit, List[Book], Any] =
    searchBaseEndpoint
      .in(pathBooks)
      .in(queryOrderBy)
      .out(jsonBookListOut)

  val searchElementListEndpoint: PublicEndpoint[(String, String), Unit, List[ElementList], Any] =
    searchBaseEndpoint
      .in(pathLists)
      .in(queryOrderBy)
      .out(jsonListOfElementListOut)

  val searchArticleEndpoint: PublicEndpoint[(String, String), Unit, List[Article], Any] =
    searchBaseEndpoint
      .in(pathArticles)
      .in(queryOrderBy)
      .out(jsonArticleListOut)

  val searchUserEndpoint: PublicEndpoint[(String, String), Unit, List[User], Any] =
    searchBaseEndpoint
      .in(pathUsers)
      .in(queryOrderBy)
      .out(jsonUserListOut)

}
