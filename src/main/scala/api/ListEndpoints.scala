package api

import cats.effect.*
import io.circe.Printer
import io.circe.generic.auto.*
import io.circe.syntax.*
import model.{User,Element, Movie, TVShow, Season, Episode, Videogame, Book, ElementList, Log, Comment, Article}
import sttp.tapir.*
import sttp.tapir.generic.auto.*
import sttp.tapir.json.circe.*
import sttp.tapir.model.UsernamePassword

class ListEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val pathListId: EndpointInput[Int] =
    path[Int]("list_id")

  private val queryType: EndpointInput[String] =
    query[String]("type")
  // TODO: Añadir validator para que solo pueda ser: movie, tv_show, season, episode, videogame, book

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

  private val pathLists: EndpointInput[String] =
    path[String]("lists")

  private val queryOrderBy: EndpointInput[String] =
    query[String]("order_by")

  private val listBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "lists")



  // val userBaseEndpoint: PublicEndpoint[Unit, ErrorInfo, Unit, Any] =
  //  endpoint.in("api" / "v1.0").errorOut(jsonBody[ErrorInfo])

  private val jsonListOfElementListOut: EndpointOutput[List[ElementList]] =
    jsonBody[List[ElementList]]

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

  private val jsonArticleListOut: EndpointOutput[List[Article]] =
    jsonBody[List[Article]]

  private val jsonUserListOut: EndpointOutput[List[User]] =
    jsonBody[List[User]]

  val listsEndpoint: PublicEndpoint[(String, String), Unit, List[ElementList], Any] =
    listBaseEndpoint
      .in(queryType)
      .in(queryOrderBy)
      .out(jsonListOfElementListOut)
    
    
  // TODO: Decidir lógica de la aplicación Para discernir entre pelis, series y demás, ¿Path o Query?
  val listOfMovieListsEndpoint: PublicEndpoint[(String, String), Unit, List[ElementList], Any] =
    listBaseEndpoint
      .in(pathMovies)
      .in(queryOrderBy)
      .out(jsonListOfElementListOut)

  val listOfTVShowListsEndpoint: PublicEndpoint[(String, String), Unit, List[ElementList], Any] =
    listBaseEndpoint
      .in(pathTVShows)
      .in(queryOrderBy)
      .out(jsonListOfElementListOut)

  val listOfSeasonListsEndpoint: PublicEndpoint[(String, String), Unit, List[ElementList], Any] =
    listBaseEndpoint
      .in(pathSeasons)
      .in(queryOrderBy)
      .out(jsonListOfElementListOut)
  
  val listOfEpisodesListsEndpoint: PublicEndpoint[(String, String), Unit, List[ElementList], Any] =
    listBaseEndpoint
      .in(pathEpisodes)
      .in(queryOrderBy)
      .out(jsonListOfElementListOut)

  val listOfVideogamesListsEndpoint: PublicEndpoint[(String, String), Unit, List[ElementList], Any] =
    listBaseEndpoint
      .in(pathVideogames)
      .in(queryOrderBy)
      .out(jsonListOfElementListOut)

  val listOfBooksListsEndpoint: PublicEndpoint[(String, String), Unit, List[ElementList], Any] =
    listBaseEndpoint
      .in(pathBooks)
      .in(queryOrderBy)
      .out(jsonListOfElementListOut)
  
  
  
  val specificListEndpoint: PublicEndpoint[Int, Unit, ElementList, Any] =
    listBaseEndpoint
      .in(pathListId)
      .out(jsonElementListOut)
}
