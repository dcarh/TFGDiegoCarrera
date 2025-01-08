package endpoints.app.social

import sttp.tapir.*
import endpoints.EndpointsUtils.httpMethodEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.social.MediaList
import modelClasses.ids.Social.MediaListId


object MediaListsEndpoints {
    
  private val listsBaseEndpoint:
    (String, String, String) => PublicEndpoint[Unit, UserError, Unit, Any] =
      (name, description, method) => httpMethodEndpoint(name, description, "lists", method)

  private val listBaseEndpoint:
    (String, String, String) => PublicEndpoint[Unit, UserError, Unit, Any] =
      (name, description, method) =>
        httpMethodEndpoint(name, description, "list", method)

  val getAllMediaLists: PublicEndpoint[Option[String], UserError, List[MediaList], Any] =
    listsBaseEndpoint(
      "Get all media content lists endpoint", 
      "This endpoint returns a list with all the media content lists in the app",
      "GET"
    )
      .in(QueryInputs.querySortBy)
      .out(SocialOutputs.listOfMediaListsOutput)

  val getMediaList: PublicEndpoint[MediaListId, UserError, MediaList, Any] =
    listsBaseEndpoint(
      "Get media content list endpoint", 
      "This endpoint returns a specific media content list by its ID",
      "GET"
    )
      .in(PathInputs.pathListId)
      .out(SocialOutputs.mediaContentListOutput)

  val createMediaList: PublicEndpoint[MediaList, UserError, MediaList, Any] =
    listBaseEndpoint(
      "Create media content list endpoint", 
      "This endpoint creates a media content list and returns it in case of success",
      "POST"
    )
      .in("create")
      .in(JsonInputs.jsonMediaList)
      .out(SocialOutputs.mediaContentListOutput)

  val editMediaList: PublicEndpoint[(MediaListId, MediaList), UserError, MediaList, Any] =
    listBaseEndpoint(
      "Edit media content list endpoint", 
      "This endpoint allows to edit a media content list and returns it in case of success. Otherwise returns an error message",
      "PUT"
    )
      .in(PathInputs.pathListId)
      .in("edit")
      .in(JsonInputs.jsonMediaList)
      .out(SocialOutputs.mediaContentListOutput)

  val deleteMediaList: PublicEndpoint[MediaListId, UserError, Unit, Any] =
    listBaseEndpoint(
      "Delete media content list endpoint",
      "This endpoint deletes a media content list and returns it in case of success",
      "DELETE"
    )
      .in(PathInputs.pathListId)
      .in("delete")
}
