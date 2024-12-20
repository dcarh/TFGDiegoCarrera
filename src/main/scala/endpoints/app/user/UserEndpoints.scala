package endpoints.app.user

import sttp.tapir.*
import endpoints.app.user.UserEndpointsUtils.{userBaseEndpoint, usersBaseEndpoint}
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.user.User

object UserEndpoints {

  // TODO: ¿Input?
  val userSignUpEndpoint: PublicEndpoint[Unit, UserError, User, Any] =
    userBaseEndpoint(
      "Sign up endpoint",
      "With this endpoint, a person can sign up in the app. The endpoint returns the created user in case of success",
      "POST"
    )
      .in("sign-up")
      .out(UserOutputs.userSuccess)

  // TODO: ¿Input?
  val userSignInEndpoint: PublicEndpoint[Unit, UserError, User, Any] =
    userBaseEndpoint(
      "Sign in endpoint",
      "With this endpoint, a person can sign in in the app. The endpoint returns the user in case of success",
      "POST"
    )
      .in("sign-in")
      .out(UserOutputs.userSuccess)

  val usersEndpoint: PublicEndpoint[Option[String], UserError, List[User], Any] =
    usersBaseEndpoint(
      "Users endpoint",
      "This endpoint returns a list of all the users in the app",
      "GET"
    )
      .in(QueryInputs.querySortBy)
      .out(UserOutputs.listOfUsersSuccess)
}
