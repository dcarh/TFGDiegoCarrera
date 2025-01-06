package endpoints.app.user

import sttp.tapir.*
import endpoints.app.user.UserEndpointsUtils.userBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.user.UserSettings
import modelClasses.ids.User.UserId

object UserSettingsEndpoints {

  private val userSettingsBaseEndpoint:
    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
      (name, description, method) => userBaseEndpoint(name, description, method)
        .in(PathInputs.pathUserId)
        .in("settings")

  val userSettingsEndpoint: PublicEndpoint[UserId, UserError, UserSettings, Any] =
    userSettingsBaseEndpoint(
      "User's settings endpoint" ,
      "This endpoint returns the settings [SPECIFY SETTINGS] of a user",
      "GET"
    )
      .out(UserOutputs.userSettingsOutput)

  val userEditSettingsEndpoint: PublicEndpoint[(UserId, UserSettings), UserError, UserSettings, Any] =
    userSettingsBaseEndpoint(
      "User's edit settings endpoint" ,
      "This endpoint allows the editing of the settings [SPECIFY SETTINGS] of a user",
      "PUT"
    )
      .in("edit")
      .in(JsonInputs.jsonSettings)
      .out(UserOutputs.userSettingsOutput)

}
