package endpoints.app.media

import sttp.tapir.*
import endpoints.EndpointsUtils.httpMethodEndpoint
import domain.errors.UserError.*

object MediaBaseEndpoint {

  val mediaBaseEndpoint:
    (String, String) => PublicEndpoint[Unit, UserError, Unit, Any] =
      (name, description) => httpMethodEndpoint(name, description, "display", "GET")
}
