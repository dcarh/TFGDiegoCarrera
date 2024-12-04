package endpoints.igdb

import sttp.tapir.*

import endpoints.EndpointsUtils.appBaseEndpoint
import endpoints.inputs.IGDB._

import modelClasses.ErrorInfo

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
    (String, String, String) => PublicEndpoint[(String, String, String, String), ErrorInfo, Unit, Any] =
    (name, description, path) =>
      appBaseEndpoint(name, description, path, "POST")
        .in(Headers.accept)
        .in(Headers.clientId)
        .in(Headers.authorization)
        .in(Body.stringBody)
}
