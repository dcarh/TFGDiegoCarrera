package endpoints.app.user.social

import sttp.tapir.*
import endpoints.app.user.UserEndpointsUtils.userBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.social.MediaList
import modelClasses.ids.Social.MediaListId
import modelClasses.ids.User.UserId

object UserMediaListsEndpoints {

//  private val userListBaseEndpoint:
//    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
//      (name, description, method) => userBaseEndpoint(name, description, method)
//        .in(PathInputs.pathUserId)
//        .in("list")

  private val userMediaListsBaseEndpoint:
    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
      (name, description, method) => userBaseEndpoint(name, description, method)
        .in(PathInputs.pathUserId)
        .in("lists")

  val getUserMediaLists: PublicEndpoint[(UserId, Option[String]), UserError, List[MediaListId], Any] =
    userMediaListsBaseEndpoint(
      "User's lists endpoint",
      "This endpoint returns all the lists for a user",
      "GET"
    )
      .in(QueryInputs.querySortBy)
      .out(SocialOutputs.listOfMediaListsIdsOutput)

//  val userSpecificListEndpoint: PublicEndpoint[(UserId, MediaContentListId), UserError, MediaContentList, Any] =
//    userListBaseEndpoint(
//      "User's specific list endpoint",
//      "This endpoint returns a specific list for a user by the ID of the list",
//      "GET"
//    )
//      .in(PathInputs.pathListId)
//      .out(SocialOutputs.mediaContentListSucess)

}
