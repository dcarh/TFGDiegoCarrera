package endpoints.app

import sttp.tapir._
import sttp.model.StatusCode

import modelClasses.ErrorInfo

import endpoints.EndpointsUtils.appBaseEndpoint
import endpoints.inputs.Common._
import endpoints.outputs.Common._

import modelClasses.app.social.MediaContentList
import modelClasses.ids.Social.MediaContentListId


object MediaContentListsEndpoints {
    
  private val listsBaseEndpoint:
    (String, String, String) => PublicEndpoint[Unit, ErrorInfo, Unit, Any] =
      (name, description, method) => appBaseEndpoint(name, description, "lists", method)

  private val listBaseEndpoint:
    (String, String, String) => PublicEndpoint[Unit, ErrorInfo, Unit, Any] =
      (name, description, method) =>
        appBaseEndpoint(name, description, "list", method)

  val listsEndpoint: PublicEndpoint[Option[String], ErrorInfo, List[MediaContentList], Any] =
    listsBaseEndpoint(
      "Lists endpoint", 
      "This endpoint returns a list with all the lists in the app",
      "GET"
    )
      .in(QueryInputs.querySortBy)
      .out(SocialOutputs.listOfMediaContentListSuccess)

  val specificListEndpoint: PublicEndpoint[(MediaContentListId, Option[String]), ErrorInfo, MediaContentList, Any] =
    listsBaseEndpoint(
      "Specific list endpoint", 
      "This endpoint returns a specific list of elements by its ID",
      "GET"
    )
      .in(PathInputs.pathListId)
      .in(QueryInputs.querySortBy)
      .out(SocialOutputs.mediaContentListSucess)

  val createListEndpoint: PublicEndpoint[Unit, ErrorInfo, MediaContentList, Any] =
    listBaseEndpoint(
      "Create list endpoint", 
      "This endpoint creates a list of elements and returns it in case of success",
      "POST"
    )
      .in("create")
      .out(SocialOutputs.mediaContentListSucess)

  val userEditListEndpoint: PublicEndpoint[MediaContentListId, ErrorInfo, MediaContentList, Any] =
    listBaseEndpoint(
      "Edit list endpoint", 
      "This endpoint allows to edit a list of elements and returns it in case of success. Otherwise returns an error message",
      "PUT"
    )
      .in(PathInputs.pathListId)
      .in("edit")
      .out(SocialOutputs.mediaContentListSucess)

  val userDeleteListEndpoint: PublicEndpoint[MediaContentListId, ErrorInfo, Unit, Any] =
    listBaseEndpoint(
      "Delete list endpoint", 
      "This endpoint deletes a list of elements and returns it in case of success",
      "DELETE"
    )
      .in(PathInputs.pathListId)
      .in("delete")
      .errorOut(
        oneOf[ErrorInfo](
          oneOfVariant(StatusCode.NotFound, ErrorOutputs.notFound),
          oneOfVariant(StatusCode.BadRequest, ErrorOutputs.invalidRequest)
        )
      )
      .out(statusCode(StatusCode.NoContent))
}
