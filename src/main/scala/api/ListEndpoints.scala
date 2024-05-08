package api

import sttp.tapir._
import sttp.model.StatusCode

import modelClasses.ErrorInfo
import modelClasses.social.MediaContentList
import api.common.Inputs.inputs
import api.common.Outputs.outputs


class ListEndpoints {

  // implicit val sUser: Schema[User] = Schema.derived

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]
    
  private val listsBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "lists")

  private val listBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "list")

  val listsEndpoint: PublicEndpoint[Option[String], Unit, List[MediaContentList], Any] =
    listsBaseEndpoint
      .name("List of elements endpoint")
      .description("This endpoint returns a list of elements, whether it may be of all elements or a specific type of element")
      .get
      .in(inputs.querySortBy)
      .out(outputs.jsonListOfMediaContentListOut)
  
  val listOfMovieListsEndpoint: PublicEndpoint[Option[String], Unit, List[MediaContentList], Any] =
    listsBaseEndpoint
      .name("List of movies lists endpoint")
      .description("This endpoint returns a list of movies lists")
      .get
      .in(inputs.querySortBy)
      .out(outputs.jsonListOfMediaContentListOut)

  val listOfTVShowListsEndpoint: PublicEndpoint[Option[String], Unit, List[MediaContentList], Any] =
    listsBaseEndpoint
      .name("List of TV shows lists endpoint")
      .description("This endpoint returns a list of TV shows lists")
      .get
      .in(inputs.querySortBy)
      .out(outputs.jsonListOfMediaContentListOut)

  val listOfSeasonListsEndpoint: PublicEndpoint[Option[String], Unit, List[MediaContentList], Any] =
    listsBaseEndpoint
      .name("List of TV seasons lists endpoint")
      .description("This endpoint returns a list of TV seasons lists")
      .get
      .in(inputs.querySortBy)
      .out(outputs.jsonListOfMediaContentListOut)
  
  val listOfEpisodesListsEndpoint: PublicEndpoint[Option[String], Unit, List[MediaContentList], Any] =
    listsBaseEndpoint
      .name("List of TV episodes lists endpoint")
      .description("This endpoint returns a list of TV episodes lists")
      .get
      .in(inputs.querySortBy)
      .out(outputs.jsonListOfMediaContentListOut)

  val listOfVideogamesListsEndpoint: PublicEndpoint[Option[String], Unit, List[MediaContentList], Any] =
    listsBaseEndpoint
      .name("List of videogames lists endpoint")
      .description("This endpoint returns a list of videogames lists")
      .get
      .in(inputs.querySortBy)
      .out(outputs.jsonListOfMediaContentListOut)

  val listOfBooksListsEndpoint: PublicEndpoint[Option[String], Unit, List[MediaContentList], Any] =
    listsBaseEndpoint
      .name("List of books lists endpoint")
      .description("This endpoint returns a list of books lists")
      .get
      .in(inputs.querySortBy)
      .out(outputs.jsonListOfMediaContentListOut)
  
  val specificListEndpoint: PublicEndpoint[MediaContentList.Id, Unit, MediaContentList, Any] =
    listsBaseEndpoint
      .name("Specific list endpoint")
      .description("This endpoint returns a specific list of elements by its ID")
      .get
      .in(inputs.pathListId)
      .out(outputs.jsonMediaContentListOut)

  val createListEndpoint: PublicEndpoint[Unit, ErrorInfo, MediaContentList, Any] =
    listBaseEndpoint
      .name("Create list endpoint")
      .description("This endpoint creates a list of elements and returns it in case of success")
      .post
      .in("create")
      .out(outputs.jsonMediaContentListOut)
      .errorOut(outputs.jsonErrorInfoOut)

  val userEditListEndpoint: PublicEndpoint[MediaContentList.Id, ErrorInfo, Unit, Any] =
    listBaseEndpoint
      .name("Edit list endpoint")
      .description("This endpoint allows to edit a list of elements and returns it in case of success. Otherwise returns an error message")
      .delete
      .in(inputs.pathListId)
      .in("delete")
      .out(statusCode(StatusCode.NoContent))
      .errorOut(outputs.jsonErrorInfoOut)

  val userDeleteListEndpoint: PublicEndpoint[MediaContentList.Id, ErrorInfo, Unit, Any] =
    listBaseEndpoint
      .name("Delete list endpoint")
      .description("This endpoint deletes a list of elements and returns it in case of success")
      .delete
      .in(inputs.pathListId)
      .in("delete")
      .out(statusCode(StatusCode.NoContent))
      .errorOut(outputs.jsonErrorInfoOut)
}
