package endpoints

import sttp.tapir.*
import sttp.model.StatusCode
import endpoints.outputs.Common.ErrorOutputsTraits
import modelClasses.errors.UserError.*

object EndpointsUtils {
  
//  val notFoundString:
//    String => String =
//      obj => obj + " not found"
//
//  val invalidRequestString: String = "Invalid request"

  private val baseEndpoint:
    (String, String, String) => PublicEndpoint[Unit, Unit, Unit, Any] =
    (name, description, path) =>
      endpoint
        .name(name)
        .description(description)
        .in(path)

  private val getBaseEndpoint:
    (String, String, String) => PublicEndpoint[Unit, UserError, Unit, Any] =
      (name, description, path) =>
        baseEndpoint(name, description, path)
          .get
          .errorOut(
            oneOf[UserError](
              oneOfVariant(StatusCode.NotFound, ErrorOutputsTraits.notFoundOutput),
              oneOfVariant(StatusCode.BadRequest, ErrorOutputsTraits.badRequestOutput),
              oneOfDefaultVariant(ErrorOutputsTraits.unknownOutput)
            )
          )
          .out(statusCode(StatusCode.Ok))

  private val postBaseEndpoint:
    (String, String, String) => PublicEndpoint[Unit, UserError, Unit, Any] =
      (name, description, path) =>
        baseEndpoint(name, description, path)
          .post
          .errorOut(
            oneOf[UserError](
              oneOfVariant(StatusCode.BadRequest, ErrorOutputsTraits.badRequestOutput),
              oneOfVariant(StatusCode.Conflict, ErrorOutputsTraits.conflictOutput),
              oneOfDefaultVariant(ErrorOutputsTraits.unknownOutput)
            )
          )
          .out(statusCode(StatusCode.Created))

  private val putBaseEndpoint:
    (String, String, String) => PublicEndpoint[Unit, UserError, Unit, Any] =
      (name, description, path) =>
        baseEndpoint(name, description, path)
          .put
          .errorOut(
            oneOf[UserError](
              oneOfVariant(StatusCode.BadRequest, ErrorOutputsTraits.badRequestOutput),
              oneOfVariant(StatusCode.NotFound, ErrorOutputsTraits.notFoundOutput),
              oneOfVariant(StatusCode.Conflict, ErrorOutputsTraits.conflictOutput),
              oneOfDefaultVariant(ErrorOutputsTraits.unknownOutput)
            )
          )
          .out(statusCode(StatusCode.Ok))

  private val deleteBaseEndpoint:
    (String, String, String) => PublicEndpoint[Unit, UserError, Unit, Any] =
      (name, description, path) =>
        baseEndpoint(name, description, path)
          .delete
          .errorOut(
            oneOf[UserError](
              oneOfVariant(StatusCode.NotFound, ErrorOutputsTraits.notFoundOutput),
              oneOfVariant(StatusCode.BadRequest, ErrorOutputsTraits.badRequestOutput),
              oneOfDefaultVariant(ErrorOutputsTraits.unknownOutput)
            )
          )
          .out(statusCode(StatusCode.NoContent))

  val httpMethodEndpoint:
    (String, String, String, String) => PublicEndpoint[Unit, UserError, Unit, Any] =
      {
        case (name, description, path, "GET") => getBaseEndpoint(name, description, path)
        case (name, description, path, "POST") => postBaseEndpoint(name, description, path)
        case (name, description, path, "PUT") => putBaseEndpoint(name, description, path)
        case (name, description, path, "DELETE") => deleteBaseEndpoint(name, description, path)
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
