package endpoints.app.user

import sttp.tapir.*
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.app.user.UserSettings
import modelClasses.ids.User.UserId

object UserSettingsEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val usersBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "users")

  private val userBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "user")

  val userSettingsEndpoint: PublicEndpoint[UserId, Unit, UserSettings, Any] =
    userBaseEndpoint
      .name("User's settings endpoint")
      .description("This endpoint returns the settings [SPECIFY SETTINGS] of a user")
      .get
      .in(PathInputs.pathUserId)
      .in("settings")
      .out(UserOutputs.jsonSettingsOut)

  val userEditSettingsEndpoint: PublicEndpoint[(UserId, UserSettings), Unit, UserSettings, Any] =
    userBaseEndpoint
      .in(PathInputs.pathUserId)
      .in("settings" / "edit")
      .in(JsonInputs.jsonSettingsIn)
      .out(UserOutputs.jsonSettingsOut)

}
