package endpoints.app.social

import sttp.tapir.*
import endpoints.EndpointsUtils.appBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.ErrorInfo
import modelClasses.app.social.Entry
import modelClasses.ids.Social.EntryId

object EntriesEndpoints {
  
  private val entriesBaseEndpoint:
    (String, String, String) => PublicEndpoint[Unit, ErrorInfo, Unit, Any] =
      (name, description, method) => appBaseEndpoint(name, description, "entries", method)

  private val entryBaseEndpoint:
    (String, String, String) => PublicEndpoint[Unit, ErrorInfo, Unit, Any] =
      (name, description, method) =>
        appBaseEndpoint(name, description, "entry", method)
  
  val entriesEndpoint: PublicEndpoint[Option[String], ErrorInfo, List[Entry], Any] =
    entriesBaseEndpoint(
      "Entries endpoint", 
      "This endpoint returns a list with all the entries in the app",
      "GET"
    )
      .in(QueryInputs.querySortBy)
      .out(SocialOutputs.listOfEntriesSuccess)

  val specificEntryEndpoint: PublicEndpoint[EntryId, ErrorInfo, Entry, Any] =
    entryBaseEndpoint(
      "Specific entry endpoint", 
      "This endpoint returns a specific entry by its Id",
      "GET"
    )
      .in(PathInputs.pathEntryId)
      .out(SocialOutputs.entrySuccess)

}
