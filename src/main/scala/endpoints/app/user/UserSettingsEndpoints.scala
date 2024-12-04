package endpoints.app.user

import sttp.tapir.*
import endpoints.app.user.UserEndpointsUtils.userBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.ErrorInfo
import modelClasses.app.user.UserSettings
import modelClasses.ids.User.UserId

object UserSettingsEndpoints {

  val userSettingsEndpoint: PublicEndpoint[UserId, ErrorInfo, UserSettings, Any] =
    userBaseEndpoint(
      "User's settings endpoint" ,
      "This endpoint returns the settings [SPECIFY SETTINGS] of a user",
      "GET"
    )
      .in(PathInputs.pathUserId)
      .in("settings")
      .out(UserOutputs.userSettingsSuccess)

  val userEditSettingsEndpoint: PublicEndpoint[(UserId, UserSettings), ErrorInfo, UserSettings, Any] =
    userBaseEndpoint(
      "User's edit settings endpoint" ,
      "This endpoint allows the editing of the settings [SPECIFY SETTINGS] of a user",
      "PUT"
    )
      .in(PathInputs.pathUserId)
      .in("settings" / "edit")
      .in(JsonInputs.jsonSettingsIn)
      .out(UserOutputs.userSettingsSuccess)

}
