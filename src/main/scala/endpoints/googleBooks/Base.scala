package endpoints.googleBooks

import sttp.tapir.*
import endpoints.EndpointsUtils.httpMethodEndpoint
import modelClasses.errors.UserError.*
//import endpoints.outputs.Common._


object Base {

//  private val googleBooksBaseEndpoint:
//    (String, String) => PublicEndpoint[Unit, ErrorInfo, Unit, Any] =
//    (name, description) =>
//      endpoint
//        .name(name)
//        .description(description)
//        .get
//        .errorOut(ErrorOutputs.jsonErrorInfoOut)

//  val bookBaseEndpoint:
//    (String, String) => PublicEndpoint[Unit, ErrorInfo, Unit, Any] =
//    (name, description) =>
//      googleBooksBaseEndpoint(name, description)
//        .in("volumes")

  val bookBaseEndpoint:
    (String, String) => PublicEndpoint[Unit, UserError, Unit, Any] =
      (name, description) => httpMethodEndpoint(name, description, "volumes", "GET")

}
