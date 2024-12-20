package endpoints.app.user

import sttp.tapir.*
import endpoints.EndpointsUtils.httpMethodEndpoint
import modelClasses.errors.UserError.*

object UserEndpointsUtils {
  
  val userBaseEndpoint:
    (String, String, String) => PublicEndpoint[Unit, UserError, Unit, Any] =
      (name, description, method) => httpMethodEndpoint(name, description, "user", method)

  val usersBaseEndpoint:
    (String, String, String) => PublicEndpoint[Unit, UserError, Unit, Any] =
      (name, description, method) => httpMethodEndpoint(name, description, "users", method)
      
}
