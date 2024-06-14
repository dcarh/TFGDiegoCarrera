package endpoints.app.user

import sttp.tapir._

import endpoints.common.Inputs._
import endpoints.common.Outputs._
import modelClasses.app.social.Entry

object UserEntriesEndpoints {

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
      .in(PathInputs.pathUsername)
      .in("entries")
      .out(SocialOutputs.jsonEntryListOut)

  val userSpecificListEndpoint: PublicEndpoint[(String, Entry.Id), Unit, Entry, Any] =
    userBaseEndpoint
      .name("User's specific entry endpoint")
      .description("This endpoint returns a specific entry for a user by the ID of the entry")
      .get
      .in(PathInputs.pathUsername)
      .in("lists")
      .in(PathInputs.pathEntryId)
      .out(SocialOutputs.jsonEntryOut)

}
