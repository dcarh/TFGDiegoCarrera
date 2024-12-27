package endpoints.app.user.network

import sttp.tapir.*
import endpoints.app.user.UserEndpointsUtils.userBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.user.User
import modelClasses.ids.User.UserId

object UserNetworkEndpoints {

  private val userNetworkBaseEndpoint:
    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
      (name, description, method) => userBaseEndpoint(name, description, method)
        .in(PathInputs.pathUserId)

  val userFollowerList: PublicEndpoint[UserId, UserError, List[User], Any] =
    userNetworkBaseEndpoint(
      "User's followers endpoint",
      "This endpoint returns the followers of the user",
      "GET"
    )
      .in("followers")
      .out(UserOutputs.listOfUsersSuccess)

  val userFollowingList: PublicEndpoint[UserId, UserError, List[User], Any] =
    userNetworkBaseEndpoint(
      "User's following endpoint",
      "This endpoint returns the people followed by the user",
      "GET"
    )
      .in("following")
      .out(UserOutputs.listOfUsersSuccess)

  val userBlockedList: PublicEndpoint[UserId, UserError, List[User], Any] =
    userNetworkBaseEndpoint(
      "User's blocked endpoint",
      "This endpoint returns the people blocked by the user",
      "GET"
    )
      .in("blocked")
      .out(UserOutputs.listOfUsersSuccess)

}
