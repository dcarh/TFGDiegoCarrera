package endpoints.app.user.network

import sttp.tapir.*
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.app.user.User
import modelClasses.ids.User.UserId

object UserNetworkEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val usersBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "users")

  private val userBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "user")

  val userFollowerList: PublicEndpoint[UserId, Unit, List[User], Any] =
    userBaseEndpoint
      .name("User's followers endpoint")
      .description("This endpoint returns the followers of the user")
      .get
      .in(PathInputs.pathUserId)
      .in("followers")
      .out(UserOutputs.jsonUserListOut)

  val userFollowingList: PublicEndpoint[UserId, Unit, List[User], Any] =
    userBaseEndpoint
      .name("User's following endpoint")
      .description("This endpoint returns the people followed by the user")
      .get
      .in(PathInputs.pathUserId)
      .in("following")
      .out(UserOutputs.jsonUserListOut)

  val userBlockedList: PublicEndpoint[UserId, Unit, List[User], Any] =
    userBaseEndpoint
      .name("User's blocked endpoint")
      .description("This endpoint returns the people blocked by the user")
      .get
      .in(PathInputs.pathUserId)
      .in("blocked")
      .out(UserOutputs.jsonUserListOut)

}
