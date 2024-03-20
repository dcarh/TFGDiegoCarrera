package api

import io.circe.generic.auto._
import modelClasses.{User, Movie, TVShow, Season, Episode, Videogame, Book, ElementList, Article}
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._

class ListEndpoints {

  implicit val sUser: Schema[User] = Schema.derived

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

  private val listsBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "lists")

  private val listBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "list")



  // val userBaseEndpoint: PublicEndpoint[Unit, ErrorInfo, Unit, Any] =
  //  endpoint.in("api" / "v1.0").errorOut(jsonBody[ErrorInfo])

  private val jsonListOfElementListOut: EndpointOutput[Seq[ElementList]] =
    jsonBody[Seq[ElementList]]

  private val jsonElementListOut: EndpointOutput[ElementList] =
    jsonBody[ElementList]

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

  val listsEndpoint: PublicEndpoint[(String, String), Unit, Seq[ElementList], Any] =
    listsBaseEndpoint
      .in(queryType)
      .in(queryOrderBy)
      .out(jsonListOfElementListOut)
    
    
  // TODO: Decidir lógica de la aplicación Para discernir entre pelis, series y demás, ¿Path o Query?
  val listOfMovieListsEndpoint: PublicEndpoint[(String, String), Unit, Seq[ElementList], Any] =
    listsBaseEndpoint
      .in(pathMovies)
      .in(queryOrderBy)
      .out(jsonListOfElementListOut)

  val listOfTVShowListsEndpoint: PublicEndpoint[(String, String), Unit, Seq[ElementList], Any] =
    listsBaseEndpoint
      .in(pathTVShows)
      .in(queryOrderBy)
      .out(jsonListOfElementListOut)

  val listOfSeasonListsEndpoint: PublicEndpoint[(String, String), Unit, Seq[ElementList], Any] =
    listsBaseEndpoint
      .in(pathSeasons)
      .in(queryOrderBy)
      .out(jsonListOfElementListOut)
  
  val listOfEpisodesListsEndpoint: PublicEndpoint[(String, String), Unit, Seq[ElementList], Any] =
    listsBaseEndpoint
      .in(pathEpisodes)
      .in(queryOrderBy)
      .out(jsonListOfElementListOut)

  val listOfVideogamesListsEndpoint: PublicEndpoint[(String, String), Unit, Seq[ElementList], Any] =
    listsBaseEndpoint
      .in(pathVideogames)
      .in(queryOrderBy)
      .out(jsonListOfElementListOut)

  val listOfBooksListsEndpoint: PublicEndpoint[(String, String), Unit, Seq[ElementList], Any] =
    listsBaseEndpoint
      .in(pathBooks)
      .in(queryOrderBy)
      .out(jsonListOfElementListOut)
  
  
  
  val specificListEndpoint: PublicEndpoint[Int, Unit, ElementList, Any] =
    listsBaseEndpoint
      .in(pathListId)
      .out(jsonElementListOut)
}
