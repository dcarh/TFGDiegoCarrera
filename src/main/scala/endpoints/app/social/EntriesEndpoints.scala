package endpoints.app.social

import sttp.tapir.*
import endpoints.EndpointsUtils.httpMethodEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.social.Entry
import modelClasses.ids.Social.EntryId

object EntriesEndpoints {
  
  private val entriesBaseEndpoint:
    (String, String, String) => PublicEndpoint[Unit, UserError, Unit, Any] =
      (name, description, method) => httpMethodEndpoint(name, description, "entries", method)

  private val entryBaseEndpoint:
    (String, String, String) => PublicEndpoint[Unit, UserError, Unit, Any] =
      (name, description, method) =>
        httpMethodEndpoint(name, description, "entry", method)
  
  val getAllEntries: PublicEndpoint[(Option[String], Option[List[String]]), UserError, List[Entry], Any] =
    entriesBaseEndpoint(
      "getAllEntries", 
      "This endpoint returns a list with all the entries in the app",
      "GET"
    )
      .in(QueryInputs.querySortBy)
      .in(QueryInputs.queryCategories)
      .out(SocialOutputs.listOfEntriesOutput)

  val getEntry: PublicEndpoint[EntryId, UserError, Entry, Any] =
    entryBaseEndpoint(
      "getEntry", 
      "This endpoint returns a specific entry by its Id",
      "GET"
    )
      .in(PathInputs.pathEntryId)
      .out(SocialOutputs.entryOutput)
  
  val createEntry: PublicEndpoint[Entry, UserError, Entry, Any] =
    entryBaseEndpoint(
      "createEntry",
      "This endpoint creates a entry of elements and returns it in case of success",
      "POST"
    )
      .in("create")
      .in(JsonInputs.jsonEntry)
      .out(SocialOutputs.entryOutput)

  val editEntry: PublicEndpoint[(EntryId, Entry), UserError, Entry, Any] =
    entryBaseEndpoint(
      "editEntry",
      "This endpoint allows to edit a entry and returns it in case of success. Otherwise returns an error message",
      "PUT"
    )
      .in(PathInputs.pathEntryId)
      .in("edit")
      .in(JsonInputs.jsonEntry)
      .out(SocialOutputs.entryOutput)

  val deleteEntry: PublicEndpoint[EntryId, UserError, Unit, Any] =
    entryBaseEndpoint(
      "deleteEntry",
      "This endpoint deletes a entry and returns it in case of success",
      "DELETE"
    )
      .in(PathInputs.pathEntryId)
      .in("delete")

}
