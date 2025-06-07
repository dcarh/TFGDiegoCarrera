package endpoints.apis.igdb

import sttp.tapir.*
import endpoints.EndpointsUtils.httpMethodEndpoint
import endpoints.io.inputs.IGDB.*
import domain.errors.UserError.*

object Base {

  val igdbBaseEndpoint:
    (String, String, String) => PublicEndpoint[(String, String, String, String), UserError, Unit, Any] =
    (name, description, path) =>
      httpMethodEndpoint(name, description, path, "POST_IGDB")
        .in(Headers.accept)
        .in(Headers.clientId)
        .in(Headers.authorization)
        .in(Body.stringBody)
}
