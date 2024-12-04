package endpoints

import sttp.tapir.*
import modelClasses.ErrorInfo
import endpoints.outputs.Common._

object EndpointsUtils {
  
  val notFoundString: 
    String => String = 
      obj => obj + " not found"
  
  val invalidRequestString: String = "Invalid request"

  private val getBaseEndpoint:
    (String, String, String) => PublicEndpoint[Unit, ErrorInfo, Unit, Any] =
      (name, description, path) =>
        endpoint
          .name(name)
          .description(description)
          .get
          .in(path)
          .errorOut(ErrorOutputs.jsonErrorInfoOut)

  private val postBaseEndpoint:
    (String, String, String) => PublicEndpoint[Unit, ErrorInfo, Unit, Any] =
      (name, description, path) =>
        endpoint
          .name(name)
          .description(description)
          .post
          .in(path)
          .errorOut(ErrorOutputs.jsonErrorInfoOut)

  val appBaseEndpoint:
    (String, String, String, String) => PublicEndpoint[Unit, ErrorInfo, Unit, Any] =
      {
        case (name, description, path, "GET") => getBaseEndpoint(name, description, path)
        case (name, description, path, "POST") => postBaseEndpoint(name, description, path)
      }



//  private val appBaseEndpoint:
//    (String, String, String, String) => PublicEndpoint[Unit, ErrorInfo, Unit, Any] =
//      (name, description, path, method) =>
//        method.toUpperCase() match
//          case "GET" => getBaseEndpoint(name, description, path)
//          case "POST" => postBaseEndpoint(name, description, path)

//  private val appBaseEndpoint:
//    (String, String, String) => PublicEndpoint[Unit, ErrorInfo, Unit, Any] =
//      (name, description, path) =>
//        endpoint
//          .name(name)
//          .description(description)
//          .in(path)
//          .errorOut(ErrorOutputs.jsonErrorInfoOut)

//  val getBaseEndpoint:
//    (String, String, String) => PublicEndpoint[Unit, ErrorInfo, Unit, Any] =
//      (name, description, path) =>
//        appBaseEndpoint(name, description, path)
//          .get
//
//  val postBaseEndpoint:
//    (String, String, String) => PublicEndpoint[Unit, ErrorInfo, Unit, Any] =
//      (name, description, path) =>
//        appBaseEndpoint(name, description, path)
//          .post

}
