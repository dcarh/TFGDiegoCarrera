package endpoints.app.user.social

import sttp.tapir.*
import endpoints.app.user.UserEndpointsUtils.userBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.ids.Social.EntryId
import modelClasses.ids.User.UserId

object UserEntriesEndpoints {

  private val userEntriesBaseEndpoint:
    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
      (name, description, method) => userBaseEndpoint(name, description, method)
        .in(PathInputs.pathUserId)
        .in("entries")

  val getUserEntries: PublicEndpoint[UserId, UserError, List[EntryId], Any] =
    userEntriesBaseEndpoint(
      "User's entries endpoint",
      "This endpoint returns all the entries for a user",
      "GET"
    )
      .out(SocialOutputs.listOfEntriesIdsOutput)

}
