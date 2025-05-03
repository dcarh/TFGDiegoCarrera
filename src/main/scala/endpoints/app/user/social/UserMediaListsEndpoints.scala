package endpoints.app.user.social

import sttp.tapir.*
import endpoints.app.user.UserEndpointsUtils.specificUserBaseEndpoint
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.ids.Social.MediaListId
import modelClasses.ids.User.UserId

object UserMediaListsEndpoints {

  private val userMediaListsBaseEndpoint:
    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
      (name, description, method) => specificUserBaseEndpoint(name, description, method)
        .in("media_lists")

  val getUserMediaLists: PublicEndpoint[UserId, UserError, List[MediaListId], Any] =
    userMediaListsBaseEndpoint(
      "getUserMediaLists",
      "This endpoint returns all the media lists for a user",
      "GET"
    )
      .out(SocialOutputs.listOfMediaListsIdsOutput)
}
