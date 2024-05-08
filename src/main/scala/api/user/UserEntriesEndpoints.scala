package api.user

import sttp.tapir._

import modelClasses.social.Entry
import api.common.Inputs.inputs
import api.common.Outputs.outputs

class UserEntriesEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val usersBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "users")

  private val userBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "user")

  val userEntriesEndpoint: PublicEndpoint[String, Unit, List[Entry], Any] =
    userBaseEndpoint
      .name("User's entries endpoint")
      .description("This endpoint returns all the entries for a user")
      .get
      .in(inputs.pathUsername)
      .in("entries")
      .out(outputs.jsonEntryListOut)

  val userSpecificListEndpoint: PublicEndpoint[(String, Entry.Id), Unit, Entry, Any] =
    userBaseEndpoint
      .name("User's specific entry endpoint")
      .description("This endpoint returns a specific entry for a user by the ID of the entry")
      .get
      .in(inputs.pathUsername)
      .in("lists")
      .in(inputs.pathEntryId)
      .out(outputs.jsonEntryOut)

}
