package api.user

import sttp.tapir._

import modelClasses.user.UserSettings
import api.common.Inputs.inputs
import api.common.Outputs.outputs

class UserSettingsEndpoints {

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
      .in(inputs.pathUsername)
      .in("settings")
      .out(outputs.jsonSettingsOut)

  val userEditSettingsEndpoint: PublicEndpoint[(String, UserSettings), Unit, UserSettings, Any] =
    userBaseEndpoint
      .in(inputs.pathUsername)
      .in("settings" / "edit")
      .in(inputs.jsonSettingsIn)
      .out(outputs.jsonSettingsOut)

}
