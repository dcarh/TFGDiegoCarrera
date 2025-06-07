package endpoints.app.user.social

import sttp.tapir.*
import endpoints.app.user.UserEndpointsUtils.specificUserBaseEndpoint
import endpoints.outputs.Common.*
import domain.errors.UserError.*
import domain.ids.Social.EntryId
import domain.ids.User.UserId

object UserEntriesEndpoints {

  private val userEntriesBaseEndpoint:
    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
      (name, description, method) => specificUserBaseEndpoint(name, description, method)
        .in("entries")

  val getUserEntries: PublicEndpoint[UserId, UserError, List[EntryId], Any] =
    userEntriesBaseEndpoint(
      "getUserEntries",
      "This endpoint returns all the entries of a user",
      "GET"
    )
      .out(SocialOutputs.listOfEntriesIdsOutput)

}
