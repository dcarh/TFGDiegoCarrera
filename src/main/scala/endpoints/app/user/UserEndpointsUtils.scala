package endpoints.app.user

import sttp.tapir.*
import modelClasses.ErrorInfo

import endpoints.EndpointsUtils.appBaseEndpoint

object UserEndpointsUtils {
  
  val userBaseEndpoint:
    (String, String, String) => PublicEndpoint[Unit, ErrorInfo, Unit, Any] =
      (name, description, method) => appBaseEndpoint(name, description, "user", method)

  val usersBaseEndpoint:
    (String, String, String) => PublicEndpoint[Unit, ErrorInfo, Unit, Any] =
      (name, description, method) => appBaseEndpoint(name, description, "users", method)
      
}
