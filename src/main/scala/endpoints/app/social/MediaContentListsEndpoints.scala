package endpoints.app

import sttp.tapir.*
import endpoints.EndpointsUtils.httpMethodEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.social.MediaContentList
import modelClasses.ids.Social.MediaContentListId


object MediaContentListsEndpoints {
    
  private val listsBaseEndpoint:
    (String, String, String) => PublicEndpoint[Unit, UserError, Unit, Any] =
      (name, description, method) => httpMethodEndpoint(name, description, "lists", method)

  private val listBaseEndpoint:
    (String, String, String) => PublicEndpoint[Unit, UserError, Unit, Any] =
      (name, description, method) =>
        httpMethodEndpoint(name, description, "list", method)

  val getListsEndpoint: PublicEndpoint[Option[String], UserError, List[MediaContentList], Any] =
    listsBaseEndpoint(
      "Get lists endpoint", 
      "This endpoint returns a list with all the lists in the app",
      "GET"
    )
      .in(QueryInputs.querySortBy)
      .out(SocialOutputs.listOfMediaContentListSuccess)

  val getListEndpoint: PublicEndpoint[(MediaContentListId, Option[String]), UserError, MediaContentList, Any] =
    listsBaseEndpoint(
      "Get list endpoint", 
      "This endpoint returns a specific list of elements by its ID",
      "GET"
    )
      .in(PathInputs.pathListId)
      .in(QueryInputs.querySortBy)
      .out(SocialOutputs.mediaContentListSucess)

  val createListEndpoint: PublicEndpoint[Unit, UserError, MediaContentList, Any] =
    listBaseEndpoint(
      "Create list endpoint", 
      "This endpoint creates a list of elements and returns it in case of success",
      "POST"
    )
      .in("create")
      .out(SocialOutputs.mediaContentListSucess)

  val editListEndpoint: PublicEndpoint[MediaContentListId, UserError, MediaContentList, Any] =
    listBaseEndpoint(
      "Edit list endpoint", 
      "This endpoint allows to edit a list of elements and returns it in case of success. Otherwise returns an error message",
      "PUT"
    )
      .in(PathInputs.pathListId)
      .in("edit")
      .out(SocialOutputs.mediaContentListSucess)

  val deleteListEndpoint: PublicEndpoint[MediaContentListId, UserError, Unit, Any] =
    listBaseEndpoint(
      "Delete list endpoint",
      "This endpoint deletes a list of elements and returns it in case of success",
      "DELETE"
    )
      .in(PathInputs.pathListId)
      .in("delete")
}
