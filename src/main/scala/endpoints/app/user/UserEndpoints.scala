package endpoints.app.user

import sttp.tapir.*
import endpoints.app.user.UserEndpointsUtils.{userBaseEndpoint, usersBaseEndpoint, specificUserBaseEndpoint}
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.user.User
import modelClasses.app.user.UserProfile
import modelClasses.ids.User.UserId

object UserEndpoints {

  private val userProfileBaseEndpoint:
    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
    (name, description, method) => specificUserBaseEndpoint(name, description, method)
      .in("profile")

  val getAllUsers: PublicEndpoint[Option[String], UserError, List[User], Any] =
    usersBaseEndpoint(
      "Users endpoint",
      "This endpoint returns a list of all the users in the app",
      "GET"
    )
      .in(QueryInputs.querySortBy)
      .out(UserOutputs.listOfUsersOutput)

  val getUser: PublicEndpoint[UserId, UserError, User, Any] =
    specificUserBaseEndpoint(
      "Get user endpoint",
      "This endpoint returns the user specified by its ID",
      "GET"
    )
      .out(UserOutputs.userOutput)

  val getProfile: PublicEndpoint[UserId, UserError, UserProfile, Any] =
    userProfileBaseEndpoint(
      "Get user's profile endpoint" ,
      "This endpoint returns the profile of a user",
      "GET"
    )
      .out(UserOutputs.userProfileOutput)

  val createUser: PublicEndpoint[(UserId, UserProfile), UserError, User, Any] =
    userBaseEndpoint(
      "Create user endpoint",
      "This endpoint creates a user and returns it in case of success",
      "POST"
    )
      .in(PathInputs.pathUserId)
      .in("create")
      .in(JsonInputs.jsonProfile)
      .out(UserOutputs.userOutput)

  val editUser: PublicEndpoint[(UserId, UserProfile), UserError, User, Any] =
    specificUserBaseEndpoint(
      "Edit user's profile endpoint" ,
      "This endpoint allows the editing of the profile of a user",
      "PUT"
    )
      .in("edit")
      .in(JsonInputs.jsonProfile)
      .out(UserOutputs.userOutput)

  val deleteUser: PublicEndpoint[UserId, UserError, Unit, Any] =
    specificUserBaseEndpoint(
      "Delete user endpoint",
      "This endpoint deletes a user and returns it in case of success",
      "DELETE"
    )
      .in("delete")

}
