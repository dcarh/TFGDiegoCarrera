package api

import io.circe.generic.auto._
import modelClasses.{User,Element, Movie, TVShow, Season, Episode, Videogame, Book, ElementList, Article}
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

  private val jsonElementListOut: EndpointOutput[Seq[Element]] =
    jsonBody[Seq[Element]]

  private val jsonMovieListOut: EndpointOutput[Seq[Movie]] =
    jsonBody[Seq[Movie]]

  private val jsonTVShowListOut: EndpointOutput[Seq[TVShow]] =
    jsonBody[Seq[TVShow]]

  private val jsonSeasonListOut: EndpointOutput[Seq[Season]] =
    jsonBody[Seq[Season]]

  private val jsonEpisodeListOut: EndpointOutput[Seq[Episode]] =
    jsonBody[Seq[Episode]]

  private val jsonVideogameListOut: EndpointOutput[Seq[Videogame]] =
    jsonBody[Seq[Videogame]]

  private val jsonBookListOut: EndpointOutput[Seq[Book]] =
    jsonBody[Seq[Book]]

  private val jsonArticleListOut: EndpointOutput[Seq[Article]] =
    jsonBody[Seq[Article]]

  private val jsonUserListOut: EndpointOutput[Seq[User]] =
    jsonBody[Seq[User]]

  private val jsonListOfElementListOut: EndpointOutput[Seq[ElementList]] =
    jsonBody[Seq[ElementList]]

  private val searchBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "search")


  val searchEnpoint: PublicEndpoint[(String, String), Unit, Seq[Element], Any] =
    searchBaseEndpoint
      .in(queryType)
      .in(queryOrderBy)
      .out(jsonElementListOut)

  val searchMovieEndpoint: PublicEndpoint[(String, String), Unit, Seq[Movie], Any] =
    searchBaseEndpoint
      .in(pathMovies)
      .in(queryOrderBy)
      .out(jsonMovieListOut)

  val searchTVShowEndpoint: PublicEndpoint[(String, String), Unit, Seq[TVShow], Any] =
    searchBaseEndpoint
      .in(pathTVShows)
      .in(queryOrderBy)
      .out(jsonTVShowListOut)

  val searchSeasonEndpoint: PublicEndpoint[(String, String), Unit, Seq[Season], Any] =
    searchBaseEndpoint
      .in(pathEpisodes)
      .in(queryOrderBy)
      .out(jsonSeasonListOut)

  val searchEpisodeEndpoint: PublicEndpoint[(String, String), Unit, Seq[Episode], Any] =
    searchBaseEndpoint
      .in(pathSeasons)
      .in(queryOrderBy)
      .out(jsonEpisodeListOut)

  val searchVideogameEndpoint: PublicEndpoint[(String, String), Unit, Seq[Videogame], Any] =
    searchBaseEndpoint
      .in(pathVideogames)
      .in(queryOrderBy)
      .out(jsonVideogameListOut)

  val searchBookEndpoint: PublicEndpoint[(String, String), Unit, Seq[Book], Any] =
    searchBaseEndpoint
      .in(pathBooks)
      .in(queryOrderBy)
      .out(jsonBookListOut)

  val searchElementListEndpoint: PublicEndpoint[(String, String), Unit, Seq[ElementList], Any] =
    searchBaseEndpoint
      .in(pathLists)
      .in(queryOrderBy)
      .out(jsonListOfElementListOut)

  val searchArticleEndpoint: PublicEndpoint[(String, String), Unit, Seq[Article], Any] =
    searchBaseEndpoint
      .in(pathArticles)
      .in(queryOrderBy)
      .out(jsonArticleListOut)

  val searchUserEndpoint: PublicEndpoint[(String, String), Unit, Seq[User], Any] =
    searchBaseEndpoint
      .in(pathUsers)
      .in(queryOrderBy)
      .out(jsonUserListOut)

}
