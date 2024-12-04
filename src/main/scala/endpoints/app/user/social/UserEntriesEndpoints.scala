package endpoints.app.user

import sttp.tapir.*
import endpoints.EndpointsUtils.appBaseEndpoint
import endpoints.app.user.UserEndpointsUtils.userBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.ErrorInfo
import modelClasses.app.social.Entry
import modelClasses.ids.Social.EntryId
import modelClasses.ids.User.UserId

object UserEntriesEndpoints {

  val userEntriesEndpoint: PublicEndpoint[UserId, ErrorInfo, List[Entry], Any] =
    userBaseEndpoint(
      "User's entries endpoint",
      "This endpoint returns all the entries for a user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("entries")
      .out(SocialOutputs.listOfEntriesSuccess)

  val userSpecificListEndpoint: PublicEndpoint[(UserId, EntryId), ErrorInfo, Entry, Any] =
    userBaseEndpoint(
      "User's specific entry endpoint",
      "This endpoint returns a specific entry for a user by the ID of the entry",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("lists")
      .in(PathInputs.pathEntryId)
      .out(SocialOutputs.entrySuccess)

}
