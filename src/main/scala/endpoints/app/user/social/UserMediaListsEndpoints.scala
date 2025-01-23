package endpoints.app.user.social

import sttp.tapir.*
import endpoints.app.user.UserEndpointsUtils.specificUserBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.social.MediaList
import modelClasses.ids.Social.MediaListId
import modelClasses.ids.User.UserId

object UserMediaListsEndpoints {

  private val userMediaListsBaseEndpoint:
    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
      (name, description, method) => specificUserBaseEndpoint(name, description, method)
        .in("lists")

  val getUserMediaLists: PublicEndpoint[(UserId, Option[String]), UserError, List[MediaListId], Any] =
    userMediaListsBaseEndpoint(
      "User's lists endpoint",
      "This endpoint returns all the lists for a user",
      "GET"
    )
      .in(QueryInputs.querySortBy)
      .out(SocialOutputs.listOfMediaListsIdsOutput)
}
