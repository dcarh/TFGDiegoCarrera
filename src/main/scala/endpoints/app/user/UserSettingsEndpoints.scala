package endpoints.app.user

import sttp.tapir._

import endpoints.inputs.Common._
import endpoints.outputs.Common._
import modelClasses.app.user.UserSettings

object UserSettingsEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val usersBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "users")

  private val userBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "user")

  val userSettingsEndpoint: PublicEndpoint[String, Unit, UserSettings, Any] =
    userBaseEndpoint
      .name("User's settings endpoint")
      .description("This endpoint returns the settings [SPECIFY SETTINGS] of a user")
      .get
      .in(PathInputs.pathUsername)
      .in("settings")
      .out(UserOutputs.jsonSettingsOut)

  val userEditSettingsEndpoint: PublicEndpoint[(String, UserSettings), Unit, UserSettings, Any] =
    userBaseEndpoint
      .in(PathInputs.pathUsername)
      .in("settings" / "edit")
      .in(JsonInputs.jsonSettingsIn)
      .out(UserOutputs.jsonSettingsOut)

}
