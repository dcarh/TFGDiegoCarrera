package api

import io.circe.generic.auto._
import modelClasses.ErrorInfo
import modelClasses.media._
import modelClasses.user.User
import modelClasses.social.MediaContentList
import sttp.model.StatusCode
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._


class ListEndpoints {

  // implicit val sUser: Schema[User] = Schema.derived

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val pathListId: EndpointInput[MediaContentList.Id] =
    path[MediaContentList.Id]("list_id")

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

  private val jsonListOfElementListOut: EndpointOutput[List[MediaContentList]] =
    jsonBody[List[MediaContentList]]

  private val jsonElementListOut: EndpointOutput[MediaContentList] =
    jsonBody[MediaContentList]

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

  private val jsonErrorInfoOut: EndpointOutput[ErrorInfo] =
    jsonBody[ErrorInfo]

  val listsEndpoint: PublicEndpoint[(String, String), Unit, List[MediaContentList], Any] =
    listsBaseEndpoint
      .name("List of elements endpoint")
      .description("This endpoint returns a list of elements, whether it may be of all elements or a specific type of element")
      .get
      .in(queryType)
      .in(queryOrderBy)
      .out(jsonListOfElementListOut)
    
    
  // TODO: Decidir lógica de la aplicación Para discernir entre pelis, series y demás, ¿Path o Query?
  val listOfMovieListsEndpoint: PublicEndpoint[(String, String), Unit, List[MediaContentList], Any] =
    listsBaseEndpoint
      .name("List of movies lists endpoint")
      .description("This endpoint returns a list of movies lists")
      .get
      .in(pathMovies)
      .in(queryOrderBy)
      .out(jsonListOfElementListOut)

  val listOfTVShowListsEndpoint: PublicEndpoint[(String, String), Unit, List[MediaContentList], Any] =
    listsBaseEndpoint
      .name("List of TV shows lists endpoint")
      .description("This endpoint returns a list of TV shows lists")
      .get
      .in(pathTVShows)
      .in(queryOrderBy)
      .out(jsonListOfElementListOut)

  val listOfSeasonListsEndpoint: PublicEndpoint[(String, String), Unit, List[MediaContentList], Any] =
    listsBaseEndpoint
      .name("List of TV seasons lists endpoint")
      .description("This endpoint returns a list of TV seasons lists")
      .get
      .in(pathSeasons)
      .in(queryOrderBy)
      .out(jsonListOfElementListOut)
  
  val listOfEpisodesListsEndpoint: PublicEndpoint[(String, String), Unit, List[MediaContentList], Any] =
    listsBaseEndpoint
      .name("List of TV episodes lists endpoint")
      .description("This endpoint returns a list of TV episodes lists")
      .get
      .in(pathEpisodes)
      .in(queryOrderBy)
      .out(jsonListOfElementListOut)

  val listOfVideogamesListsEndpoint: PublicEndpoint[(String, String), Unit, List[MediaContentList], Any] =
    listsBaseEndpoint
      .name("List of videogames lists endpoint")
      .description("This endpoint returns a list of videogames lists")
      .get
      .in(pathVideogames)
      .in(queryOrderBy)
      .out(jsonListOfElementListOut)

  val listOfBooksListsEndpoint: PublicEndpoint[(String, String), Unit, List[MediaContentList], Any] =
    listsBaseEndpoint
      .name("List of books lists endpoint")
      .description("This endpoint returns a list of books lists")
      .get
      .in(pathBooks)
      .in(queryOrderBy)
      .out(jsonListOfElementListOut)

  
  val specificListEndpoint: PublicEndpoint[MediaContentList.Id, Unit, MediaContentList, Any] =
    listsBaseEndpoint
      .name("Specific list endpoint")
      .description("This endpoint returns a specific list of elements by its ID")
      .get
      .in(pathListId)
      .out(jsonElementListOut)

  val createListEndpoint: PublicEndpoint[Unit, ErrorInfo, MediaContentList, Any] =
    listBaseEndpoint
      .name("Create list endpoint")
      .description("This endpoint creates a list of elements and returns it in case of success")
      .post
      .in("create")
      .out(jsonElementListOut)
      .errorOut(jsonErrorInfoOut)

  val userEditListEndpoint: PublicEndpoint[MediaContentList.Id, ErrorInfo, Unit, Any] =
    listBaseEndpoint
      .name("Edit list endpoint")
      .description("This endpoint allows to edit a list of elements and returns it in case of success. Otherwise returns an error message")
      .delete
      .in(pathListId)
      .in("delete")
      .out(statusCode(StatusCode.NoContent))
      .errorOut(jsonErrorInfoOut)

  val userDeleteListEndpoint: PublicEndpoint[MediaContentList.Id, ErrorInfo, Unit, Any] =
    listBaseEndpoint
      .name("Delete list endpoint")
      .description("This endpoint deletes a list of elements and returns it in case of success")
      .delete
      .in(pathListId)
      .in("delete")
      .out(statusCode(StatusCode.NoContent))
      .errorOut(jsonErrorInfoOut)
}
