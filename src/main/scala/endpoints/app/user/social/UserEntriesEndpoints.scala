package endpoints.app.user.social

import domain.app.social.Entry
import domain.errors.UserError.*
import domain.ids.User.UserId
import endpoints.app.user.UserEndpointsUtils.specificUserBaseEndpoint
import endpoints.io.outputs.Common.*
import sttp.tapir.*

object UserEntriesEndpoints {

  private val userEntriesBaseEndpoint:
    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
      (name, description, method) => specificUserBaseEndpoint(name, description, method)
        .in("entries")

  val getUserEntries: PublicEndpoint[UserId, UserError, List[Entry], Any] =
    userEntriesBaseEndpoint(
      "getUserEntries",
      "This endpoint returns all the entries of a user",
      "GET"
    )
      .out(SocialOutputs.listOfEntriesOutput)

}
