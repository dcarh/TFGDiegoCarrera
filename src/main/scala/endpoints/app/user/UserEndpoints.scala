package endpoints.app.user

import sttp.tapir.*
import endpoints.app.user.UserEndpointsUtils.{userBaseEndpoint, usersBaseEndpoint, specificUserBaseEndpoint}
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import domain.errors.UserError.*
import domain.app.user.User
import domain.app.user.UserProfile
import domain.ids.User.UserId

object UserEndpoints {

  private val userProfileBaseEndpoint:
    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
    (name, description, method) => specificUserBaseEndpoint(name, description, method)
      .in("profile")

  val getAllUsers: PublicEndpoint[Option[String], UserError, List[User], Any] =
    usersBaseEndpoint(
      "getAllUsers",
      "This endpoint returns all the users in the app",
      "GET"
    )
      .in(QueryInputs.querySortBy)
      .out(UserOutputs.listOfUsersOutput)

  val getUser: PublicEndpoint[UserId, UserError, User, Any] =
    specificUserBaseEndpoint(
      "getUser",
      "This endpoint returns a user by its ID",
      "GET"
    )
      .out(UserOutputs.userOutput)

  val getProfile: PublicEndpoint[UserId, UserError, UserProfile, Any] =
    userProfileBaseEndpoint(
      "getProfile" ,
      "This endpoint returns the profile of a user",
      "GET"
    )
      .out(UserOutputs.userProfileOutput)

  val createUser: PublicEndpoint[(UserId, UserProfile), UserError, User, Any] =
    userBaseEndpoint(
      "createUser",
      "This endpoint creates a user",
      "POST"
    )
      .in(PathInputs.pathUserId)
      .in("create")
      .in(JsonInputs.jsonProfile)
      .out(UserOutputs.userOutput)

  val editUser: PublicEndpoint[(UserId, UserProfile), UserError, User, Any] =
    specificUserBaseEndpoint(
      "editUser" ,
      "This endpoint allows editing the profile of a user",
      "PUT"
    )
      .in("edit")
      .in(JsonInputs.jsonProfile)
      .out(UserOutputs.userOutput)

  val deleteUser: PublicEndpoint[UserId, UserError, Unit, Any] =
    specificUserBaseEndpoint(
      "deleteUser",
      "This endpoint deletes a user",
      "DELETE"
    )
      .in("delete")

}
