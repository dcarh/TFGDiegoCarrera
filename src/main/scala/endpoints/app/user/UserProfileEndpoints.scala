package endpoints.app.user

import endpoints.app.user.UserEndpointsUtils.specificUserBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.app.user.UserProfile
import modelClasses.errors.UserError.*
import modelClasses.ids.User.UserId
import sttp.tapir.*

object UserProfileEndpoints {

  private val userProfileBaseEndpoint:
    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
      (name, description, method) => specificUserBaseEndpoint(name, description, method)
        .in("profile")

  val getUserProfile: PublicEndpoint[UserId, UserError, UserProfile, Any] =
    userProfileBaseEndpoint(
      "Get user's profile endpoint" ,
      "This endpoint returns the profile of a user",
      "GET"
    )
      .out(UserOutputs.userProfileOutput)

  val editUserProfile: PublicEndpoint[(UserId, UserProfile), UserError, UserProfile, Any] =
    userProfileBaseEndpoint(
      "Edit user's profile endpoint" ,
      "This endpoint allows the editing of the profile of a user",
      "PUT"
    )
      .in("edit")
      .in(JsonInputs.jsonProfile)
      .out(UserOutputs.userProfileOutput)

}
