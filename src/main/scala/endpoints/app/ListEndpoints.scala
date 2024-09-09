package endpoints.app

import sttp.tapir._
import sttp.model.StatusCode

import modelClasses.ErrorInfo

import endpoints.inputs.Common._
import endpoints.outputs.Common._

import modelClasses.app.social.MediaContentList
import modelClasses.ids.Social.MediaContentListId


object ListEndpoints {

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
      .in(QueryInputs.querySortBy)
      .out(SocialOutputs.jsonListOfMediaContentListOut)
  
  val listOfMovieListsEndpoint: PublicEndpoint[Option[String], Unit, List[MediaContentList], Any] =
    listsBaseEndpoint
      .name("List of movies lists endpoint")
      .description("This endpoint returns a list of movies lists")
      .get
      .in(QueryInputs.querySortBy)
      .out(SocialOutputs.jsonListOfMediaContentListOut)

  val listOfTVShowListsEndpoint: PublicEndpoint[Option[String], Unit, List[MediaContentList], Any] =
    listsBaseEndpoint
      .name("List of TV shows lists endpoint")
      .description("This endpoint returns a list of TV shows lists")
      .get
      .in(QueryInputs.querySortBy)
      .out(SocialOutputs.jsonListOfMediaContentListOut)

  val listOfSeasonListsEndpoint: PublicEndpoint[Option[String], Unit, List[MediaContentList], Any] =
    listsBaseEndpoint
      .name("List of TV seasons lists endpoint")
      .description("This endpoint returns a list of TV seasons lists")
      .get
      .in(QueryInputs.querySortBy)
      .out(SocialOutputs.jsonListOfMediaContentListOut)
  
  val listOfEpisodesListsEndpoint: PublicEndpoint[Option[String], Unit, List[MediaContentList], Any] =
    listsBaseEndpoint
      .name("List of TV episodes lists endpoint")
      .description("This endpoint returns a list of TV episodes lists")
      .get
      .in(QueryInputs.querySortBy)
      .out(SocialOutputs.jsonListOfMediaContentListOut)

  val listOfVideogamesListsEndpoint: PublicEndpoint[Option[String], Unit, List[MediaContentList], Any] =
    listsBaseEndpoint
      .name("List of videogames lists endpoint")
      .description("This endpoint returns a list of videogames lists")
      .get
      .in(QueryInputs.querySortBy)
      .out(SocialOutputs.jsonListOfMediaContentListOut)

  val listOfBooksListsEndpoint: PublicEndpoint[Option[String], Unit, List[MediaContentList], Any] =
    listsBaseEndpoint
      .name("List of books lists endpoint")
      .description("This endpoint returns a list of books lists")
      .get
      .in(QueryInputs.querySortBy)
      .out(SocialOutputs.jsonListOfMediaContentListOut)
  
  val specificListEndpoint: PublicEndpoint[MediaContentListId, Unit, MediaContentList, Any] =
    listsBaseEndpoint
      .name("Specific list endpoint")
      .description("This endpoint returns a specific list of elements by its ID")
      .get
      .in(PathInputs.pathListId)
      .out(SocialOutputs.jsonMediaContentListOut)

  val createListEndpoint: PublicEndpoint[Unit, ErrorInfo, MediaContentList, Any] =
    listBaseEndpoint
      .name("Create list endpoint")
      .description("This endpoint creates a list of elements and returns it in case of success")
      .post
      .in("create")
      .out(SocialOutputs.jsonMediaContentListOut)
      .errorOut(ApiOutputs.jsonErrorInfoOut)

  val userEditListEndpoint: PublicEndpoint[MediaContentListId, ErrorInfo, Unit, Any] =
    listBaseEndpoint
      .name("Edit list endpoint")
      .description("This endpoint allows to edit a list of elements and returns it in case of success. Otherwise returns an error message")
      .delete
      .in(PathInputs.pathListId)
      .in("delete")
      .out(statusCode(StatusCode.NoContent))
      .errorOut(ApiOutputs.jsonErrorInfoOut)

  val userDeleteListEndpoint: PublicEndpoint[MediaContentListId, ErrorInfo, Unit, Any] =
    listBaseEndpoint
      .name("Delete list endpoint")
      .description("This endpoint deletes a list of elements and returns it in case of success")
      .delete
      .in(PathInputs.pathListId)
      .in("delete")
      .out(statusCode(StatusCode.NoContent))
      .errorOut(ApiOutputs.jsonErrorInfoOut)
}
