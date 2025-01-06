package endpoints.app.user.social

import sttp.tapir.*
import endpoints.app.user.UserEndpointsUtils.userBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.social.MediaContentList
import modelClasses.ids.Social.MediaContentListId
import modelClasses.ids.User.UserId

object UserListsEndpoints {

//  private val userListBaseEndpoint:
//    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
//      (name, description, method) => userBaseEndpoint(name, description, method)
//        .in(PathInputs.pathUserId)
//        .in("list")

  private val userListsBaseEndpoint:
    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
      (name, description, method) => userBaseEndpoint(name, description, method)
        .in(PathInputs.pathUserId)
        .in("lists")

  val getUserLists: PublicEndpoint[(UserId, Option[String]), UserError, List[MediaContentListId], Any] =
    userListsBaseEndpoint(
      "User's lists endpoint",
      "This endpoint returns all the lists for a user",
      "GET"
    )
      .in(QueryInputs.querySortBy)
      .out(SocialOutputs.listOfMediaContentListsIdsOutput)

//  val userSpecificListEndpoint: PublicEndpoint[(UserId, MediaContentListId), UserError, MediaContentList, Any] =
//    userListBaseEndpoint(
//      "User's specific list endpoint",
//      "This endpoint returns a specific list for a user by the ID of the list",
//      "GET"
//    )
//      .in(PathInputs.pathListId)
//      .out(SocialOutputs.mediaContentListSucess)

}
