package endpoints.app.user.social

import sttp.tapir.*
import endpoints.app.user.UserEndpointsUtils.specificUserBaseEndpoint
import endpoints.io.outputs.Common.*
import domain.errors.UserError.*
import domain.ids.Social.MediaListId
import domain.ids.User.UserId

object UserMediaListsEndpoints {

  private val userMediaListsBaseEndpoint:
    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
      (name, description, method) => specificUserBaseEndpoint(name, description, method)
        .in("media_lists")

  val getUserMediaLists: PublicEndpoint[UserId, UserError, List[MediaListId], Any] =
    userMediaListsBaseEndpoint(
      "getUserMediaLists",
      "This endpoint returns all the media lists of a user",
      "GET"
    )
      .out(SocialOutputs.listOfMediaListsIdsOutput)
}
