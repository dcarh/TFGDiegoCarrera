package endpoints.app.user

import sttp.tapir.*
import endpoints.EndpointsUtils.httpMethodEndpoint
import endpoints.inputs.Common.PathInputs
import modelClasses.errors.UserError.*
import modelClasses.ids.User.UserId

object UserEndpointsUtils {
  
  val userBaseEndpoint:
    (String, String, String) => PublicEndpoint[Unit, UserError, Unit, Any] =
      (name, description, method) => httpMethodEndpoint(name, description, "user", method)

  val specificUserBaseEndpoint:
    (String, String, String) => PublicEndpoint[UserId, UserError, Unit, Any] =
    (name, description, method) => userBaseEndpoint(name, description, method)
        .in(PathInputs.pathUserId)

  val usersBaseEndpoint:
    (String, String, String) => PublicEndpoint[Unit, UserError, Unit, Any] =
      (name, description, method) => httpMethodEndpoint(name, description, "users", method)
      
}
