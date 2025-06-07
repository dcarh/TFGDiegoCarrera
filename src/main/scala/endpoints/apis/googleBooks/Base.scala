package endpoints.apis.googleBooks

import sttp.tapir.*
import endpoints.EndpointsUtils.httpMethodEndpoint
import domain.errors.UserError.*
//import endpoints.outputs.Common._


object Base {

  val bookBaseEndpoint:
    (String, String) => PublicEndpoint[Unit, UserError, Unit, Any] =
      (name, description) => httpMethodEndpoint(name, description, "volumes", "GET")

}
