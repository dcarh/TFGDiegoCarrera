package endpoints.app.user

import sttp.tapir.*
import endpoints.app.user.UserEndpointsUtils.userBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.social.MediaContentList
import modelClasses.ids.Social.MediaContentListId
import modelClasses.ids.User.UserId

object UserListsEndpoints {

  val userListsEndpoint: PublicEndpoint[(UserId, Option[String]), UserError, List[MediaContentList], Any] =
    userBaseEndpoint(
      "User's lists endpoint",
      "This endpoint returns all the lists for a user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("lists")
      .in(QueryInputs.querySortBy)
      .out(SocialOutputs.listOfMediaContentListSuccess)

  val userSpecificListEndpoint: PublicEndpoint[(UserId, MediaContentListId), UserError, MediaContentList, Any] =
    userBaseEndpoint(
      "User's specific list endpoint",
      "This endpoint returns a specific list for a user by the ID of the list",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("lists")
      .in(PathInputs.pathListId)
      .out(SocialOutputs.mediaContentListSucess)

}
