package api.user

import sttp.tapir._

import modelClasses.user.User
import api.common.Inputs.inputs
import api.common.Outputs.outputs

class UsersEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val usersBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "users")

  private val userBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "user")

  // TODO: ¿Input?
  val userSignUpEndpoint: PublicEndpoint[Unit, Unit, User, Any] =
    userBaseEndpoint
      .name("Sign up endpoint")
      .description("With this endpoint, a person can sign up in the app. The endpoint returns the created user in case of success")
      .post
      .in("sign-up")
      .out(outputs.jsonUserOut)

  // TODO: ¿Input?
  val userSignInEndpoint: PublicEndpoint[Unit, Unit, User, Any] =
    userBaseEndpoint
      .name("Sign in endpoint")
      .description("With this endpoint, a person can sign in in the app. The endpoint returns the user in case of success")
      .post
      .in("sign-in")
      .out(outputs.jsonUserOut)

  // Endpoint that returns a list with all the users in the app
  val usersEndpoint: PublicEndpoint[String, Unit, List[User], Any] =
    usersBaseEndpoint
      .name("Users endpoint")
      .description("This endpoint returns a list of all the users in the app")
      .get
      .in(inputs.queryOrderBy)
      .out(outputs.jsonUserListOut)
}
