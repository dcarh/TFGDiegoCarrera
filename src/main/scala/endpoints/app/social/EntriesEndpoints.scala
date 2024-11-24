package endpoints.app.social

import sttp.tapir.*

import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.app.social.Entry
import modelClasses.ids.Social.EntryId

object EntriesEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]
  
  private val entriesBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "entries")

  private val entryBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "entry")
  
  val entriesEndpoint: PublicEndpoint[Option[String], Unit, List[Entry], Any] =
    entriesBaseEndpoint
      .name("Entries endpoint")
      .description("This endpoint returns a list with all the entries in the app")
      .get
      .in(QueryInputs.querySortBy)
      .out(SocialOutputs.jsonEntryListOut)

  val specificEntryEndpoint: PublicEndpoint[EntryId, Unit, Entry, Any] =
    entryBaseEndpoint
      .name("Specific entry endpoint")
      .description("This endpoint returns a specific entry by its Id")
      .get
      .in(PathInputs.pathEntryId)
      .out(SocialOutputs.jsonEntryOut)

}
