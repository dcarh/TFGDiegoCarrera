package endpoints.app.user

import sttp.tapir.*
import endpoints.app.user.UserEndpointsUtils.userBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.social.Entry
import modelClasses.ids.Social.EntryId
import modelClasses.ids.User.UserId

object UserEntriesEndpoints {

//  private val userEntryBaseEndpoint:
//    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
//      (name, description, method) => userBaseEndpoint(name, description, method)
//        .in(PathInputs.pathUserId)
//        .in("entry")

  private val userEntriesBaseEndpoint:
    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
      (name, description, method) => userBaseEndpoint(name, description, method)
        .in(PathInputs.pathUserId)
        .in("entries")

  val userEntriesEndpoint: PublicEndpoint[UserId, UserError, List[Entry], Any] =
    userEntriesBaseEndpoint(
      "User's entries endpoint",
      "This endpoint returns all the entries for a user",
      "GET"
    )
      .out(SocialOutputs.listOfEntriesSuccess)

//  val userEntryEndpoint: PublicEndpoint[(UserId, EntryId), UserError, Entry, Any] =
//    userEntryBaseEndpoint(
//      "User's specific entry endpoint",
//      "This endpoint returns a specific entry for a user by the ID of the entry",
//      "GET"
//    )
//      .in(PathInputs.pathEntryId)
//      .out(SocialOutputs.entrySuccess)

}
