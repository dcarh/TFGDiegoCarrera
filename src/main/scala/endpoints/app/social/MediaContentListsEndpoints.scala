package endpoints.app.social

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

  val getAllMediaContentLists: PublicEndpoint[Option[String], UserError, List[MediaContentList], Any] =
    listsBaseEndpoint(
      "Get all media content lists endpoint", 
      "This endpoint returns a list with all the media content lists in the app",
      "GET"
    )
      .in(QueryInputs.querySortBy)
      .out(SocialOutputs.listOfMediaContentListsOutput)

  val getMediaContentList: PublicEndpoint[MediaContentListId, UserError, MediaContentList, Any] =
    listsBaseEndpoint(
      "Get media content list endpoint", 
      "This endpoint returns a specific media content list by its ID",
      "GET"
    )
      .in(PathInputs.pathListId)
      .out(SocialOutputs.mediaContentListOutput)

  val createMediaContentList: PublicEndpoint[MediaContentList, UserError, MediaContentList, Any] =
    listBaseEndpoint(
      "Create media content list endpoint", 
      "This endpoint creates a media content list and returns it in case of success",
      "POST"
    )
      .in("create")
      .in(JsonInputs.jsonMediaContentList)
      .out(SocialOutputs.mediaContentListOutput)

  val editMediaContentList: PublicEndpoint[(MediaContentListId, MediaContentList), UserError, MediaContentList, Any] =
    listBaseEndpoint(
      "Edit media content list endpoint", 
      "This endpoint allows to edit a media content list and returns it in case of success. Otherwise returns an error message",
      "PUT"
    )
      .in(PathInputs.pathListId)
      .in("edit")
      .in(JsonInputs.jsonMediaContentList)
      .out(SocialOutputs.mediaContentListOutput)

  val deleteMediaContentList: PublicEndpoint[MediaContentListId, UserError, Unit, Any] =
    listBaseEndpoint(
      "Delete media content list endpoint",
      "This endpoint deletes a media content list and returns it in case of success",
      "DELETE"
    )
      .in(PathInputs.pathListId)
      .in("delete")
}
