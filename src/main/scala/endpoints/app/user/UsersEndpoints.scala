package endpoints.app.user

import sttp.tapir._

import endpoints.inputs.Common._
import endpoints.outputs.Common._
import modelClasses.app.user.User

object UsersEndpoints {

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
      .out(UserOutputs.jsonUserOut)

  // TODO: ¿Input?
  val userSignInEndpoint: PublicEndpoint[Unit, Unit, User, Any] =
    userBaseEndpoint
      .name("Sign in endpoint")
      .description("With this endpoint, a person can sign in in the app. The endpoint returns the user in case of success")
      .post
      .in("sign-in")
      .out(UserOutputs.jsonUserOut)

  val usersEndpoint: PublicEndpoint[Option[String], Unit, List[User], Any] =
    usersBaseEndpoint
      .name("Users endpoint")
      .description("This endpoint returns a list of all the users in the app")
      .get
      .in(QueryInputs.querySortBy)
      .out(UserOutputs.jsonUserListOut)
}
