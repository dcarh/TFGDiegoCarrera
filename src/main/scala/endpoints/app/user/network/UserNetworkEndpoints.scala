package endpoints.app.user.network

import sttp.tapir.*
import endpoints.EndpointsUtils.appBaseEndpoint
import endpoints.app.user.UserEndpointsUtils.userBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.ErrorInfo
import modelClasses.app.user.User
import modelClasses.ids.User.UserId

object UserNetworkEndpoints {

  val userFollowerList: PublicEndpoint[UserId, ErrorInfo, List[User], Any] =
    userBaseEndpoint(
      "User's followers endpoint",
      "This endpoint returns the followers of the user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("followers")
      .out(UserOutputs.listOfUsersSuccess)

  val userFollowingList: PublicEndpoint[UserId, ErrorInfo, List[User], Any] =
    userBaseEndpoint(
      "User's following endpoint",
      "This endpoint returns the people followed by the user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("following")
      .out(UserOutputs.listOfUsersSuccess)

  val userBlockedList: PublicEndpoint[UserId, ErrorInfo, List[User], Any] =
    userBaseEndpoint(
      "User's blocked endpoint",
      "This endpoint returns the people blocked by the user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("blocked")
      .out(UserOutputs.listOfUsersSuccess)

}
