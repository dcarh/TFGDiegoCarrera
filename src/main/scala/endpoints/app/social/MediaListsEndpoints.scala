package endpoints.app.social

import sttp.tapir.*
import endpoints.EndpointsUtils.httpMethodEndpoint
import endpoints.io.inputs.Common.*
import endpoints.io.outputs.Common.*
import domain.errors.UserError.*
import domain.app.social.MediaList
import domain.ids.Social.MediaListId


object MediaListsEndpoints {
    
  private val mediaListsBaseEndpoint:
    (String, String, String) => PublicEndpoint[Unit, UserError, Unit, Any] =
      (name, description, method) => httpMethodEndpoint(name, description, "media_lists", method)

  private val mediaListBaseEndpoint:
    (String, String, String) => PublicEndpoint[Unit, UserError, Unit, Any] =
      (name, description, method) =>
        httpMethodEndpoint(name, description, "media_list", method)

  val getAllMediaLists: PublicEndpoint[Option[String], UserError, List[MediaList], Any] =
    mediaListsBaseEndpoint(
      "getAllMediaLists", 
      "This endpoint returns a list with all the media lists in the app",
      "GET"
    )
      .in(QueryInputs.querySortBy)
      .out(SocialOutputs.listOfMediaListsOutput)

  val getMediaList: PublicEndpoint[MediaListId, UserError, MediaList, Any] =
    mediaListBaseEndpoint(
      "getMediaList", 
      "This endpoint returns a media list by its ID",
      "GET"
    )
      .in(PathInputs.pathListId)
      .out(SocialOutputs.mediaContentListOutput)

  val createMediaList: PublicEndpoint[MediaList, UserError, MediaList, Any] =
    mediaListBaseEndpoint(
      "createMediaList", 
      "This endpoint creates a media list",
      "POST"
    )
      .in("create")
      .in(JsonInputs.jsonMediaList)
      .out(SocialOutputs.mediaContentListOutput)

  val editMediaList: PublicEndpoint[(MediaListId, MediaList), UserError, MediaList, Any] =
    mediaListBaseEndpoint(
      "editMediaList", 
      "This endpoint edits a media list",
      "PUT"
    )
      .in(PathInputs.pathListId)
      .in("edit")
      .in(JsonInputs.jsonMediaList)
      .out(SocialOutputs.mediaContentListOutput)

  val deleteMediaList: PublicEndpoint[MediaListId, UserError, Unit, Any] =
    mediaListBaseEndpoint(
      "deleteMediaList",
      "This endpoint deletes a media list",
      "DELETE"
    )
      .in(PathInputs.pathListId)
      .in("delete")
}
