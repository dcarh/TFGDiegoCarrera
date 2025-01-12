package endpoints.app.user.network

import sttp.tapir.*
import endpoints.app.user.UserEndpointsUtils.specificUserBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.ids.User.UserId

object UserNetworkEndpoints {

//  private val userNetworkBaseEndpoint:
//    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
//      (name, description, method) => userBaseEndpoint(name, description, method)

  val getFollowers: PublicEndpoint[UserId, UserError, List[UserId], Any] =
    specificUserBaseEndpoint(
      "User's followers endpoint",
      "This endpoint returns the followers of the user",
      "GET"
    )
      .in("followers")
      .out(UserOutputs.listOfUserIdsOutput)

  val getFollowing: PublicEndpoint[UserId, UserError, List[UserId], Any] =
    specificUserBaseEndpoint(
      "User's following endpoint",
      "This endpoint returns the people followed by the user",
      "GET"
    )
      .in("following")
      .out(UserOutputs.listOfUserIdsOutput)

  val getBlocked: PublicEndpoint[UserId, UserError, List[UserId], Any] =
    specificUserBaseEndpoint(
      "User's blocked endpoint",
      "This endpoint returns the people blocked by the user",
      "GET"
    )
      .in("blocked")
      .out(UserOutputs.listOfUserIdsOutput)

  val followUser: PublicEndpoint[(UserId, UserId), UserError, (List[UserId], List[UserId]), Any] =
    specificUserBaseEndpoint(
      "Follow user endpoint",
      "This endpoint allows a user to follow another user and returns the list of people followed by the user",
      "POST"
    )
      .in("follow")
      .in(PathInputs.pathUserId)
      .out(UserOutputs.tupleOfListsOfUserIdsOutput)

  val unfollowUser: PublicEndpoint[(UserId, UserId), UserError, Unit, Any] =
    specificUserBaseEndpoint(
      "Unfollow user endpoint",
      "This endpoint allows a user to unfollow another user",
      "DELETE"
    )
      .in("unfollow")
      .in(PathInputs.pathUserId)

  val blockUser: PublicEndpoint[(UserId, UserId), UserError, List[UserId], Any] =
    specificUserBaseEndpoint(
      "Block user endpoint",
      "This endpoint allows a user to block another user and returns the list of people blocked by the user",
      "POST"
    )
      .in("block")
      .in(PathInputs.pathUserId)
      .out(UserOutputs.listOfUserIdsOutput)

  val unblockUser: PublicEndpoint[(UserId, UserId), UserError, Unit, Any] =
    specificUserBaseEndpoint(
      "Unblock user endpoint",
      "This endpoint allows a user to unblock another user",
      "DELETE"
    )
      .in("unblock")
      .in(PathInputs.pathUserId)

}
