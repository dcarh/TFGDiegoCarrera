package endpoints.app.user

import sttp.tapir.*
import endpoints.app.user.UserEndpointsUtils.{userBaseEndpoint, usersBaseEndpoint, specificUserBaseEndpoint}
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.user.User
import modelClasses.ids.User.UserId

object UserEndpoints {

  // TODO: ¿Input?
//  val userSignUpEndpoint: PublicEndpoint[Unit, UserError, User, Any] =
//    userBaseEndpoint(
//      "Sign up endpoint",
//      "With this endpoint, a person can sign up in the app. The endpoint returns the created user in case of success",
//      "POST"
//    )
//      .in("sign-up")
//      .out(UserOutputs.userOutput)

  // TODO: ¿Input?
//  val userSignInEndpoint: PublicEndpoint[Unit, UserError, User, Any] =
//    userBaseEndpoint(
//      "Sign in endpoint",
//      "With this endpoint, a person can sign in in the app. The endpoint returns the user in case of success",
//      "POST"
//    )
//      .in("sign-in")
//      .out(UserOutputs.userOutput)

  val getAllUsers: PublicEndpoint[Option[String], UserError, List[User], Any] =
    usersBaseEndpoint(
      "getAllUsers",
      "This endpoint returns a list of all the users in the app",
      "GET"
    )
      .in(QueryInputs.querySortBy)
      .out(UserOutputs.listOfUsersOutput)

  val getUser: PublicEndpoint[UserId, UserError, User, Any] =
    specificUserBaseEndpoint(
      "getUser",
      "This endpoint returns the user specified by its ID",
      "GET"
    )
      .out(UserOutputs.userOutput)

  val createUser: PublicEndpoint[User, UserError, User, Any] =
    userBaseEndpoint(
      "createUser",
      "This endpoint creates a user and returns it in case of success",
      "POST"
    )
      .in("create")
      .in(JsonInputs.jsonUser)
      .out(UserOutputs.userOutput)

  val deleteUser: PublicEndpoint[UserId, UserError, Unit, Any] =
    specificUserBaseEndpoint(
      "deleteUser",
      "This endpoint deletes a user and returns it in case of success",
      "DELETE"
    )
      .in("delete")

}
