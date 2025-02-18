package endpoints.igdb

import sttp.tapir.*
import endpoints.EndpointsUtils.httpMethodEndpoint
import endpoints.inputs.IGDB.*
import modelClasses.errors.UserError.*

object Base {

//  val igdbBaseEndpoint:
//    (String, String) => PublicEndpoint[(String, String, String, String), ErrorInfo, Unit, Any] =
//    (name, description) =>
//      endpoint
//        .name(name)
//        .description(description)
//        .post
//        .in(Headers.accept)
//        .in(Headers.clientId)
//        .in(Headers.authorization)
//        .in(Body.stringBody)
//        .errorOut(ErrorOutputs.jsonErrorInfoOut)

  val igdbBaseEndpoint:
    (String, String, String) => PublicEndpoint[(String, String, String, String), UserError, Unit, Any] =
    (name, description, path) =>
      httpMethodEndpoint(name, description, path, "POST_IGDB")
        .in(Headers.accept)
        .in(Headers.clientId)
        .in(Headers.authorization)
        .in(Body.stringBody)
}
