package endpoints.app.user

import sttp.tapir.*
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.app.social.Entry
import modelClasses.ids.Social.EntryId
import modelClasses.ids.User.UserId

object UserEntriesEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val usersBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "users")

  private val userBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "user")

  val userEntriesEndpoint: PublicEndpoint[UserId, Unit, List[Entry], Any] =
    userBaseEndpoint
      .name("User's entries endpoint")
      .description("This endpoint returns all the entries for a user")
      .get
      .in(PathInputs.pathUserId)
      .in("entries")
      .out(SocialOutputs.jsonEntryListOut)

  val userSpecificListEndpoint: PublicEndpoint[(UserId, EntryId), Unit, Entry, Any] =
    userBaseEndpoint
      .name("User's specific entry endpoint")
      .description("This endpoint returns a specific entry for a user by the ID of the entry")
      .get
      .in(PathInputs.pathUserId)
      .in("lists")
      .in(PathInputs.pathEntryId)
      .out(SocialOutputs.jsonEntryOut)

}
