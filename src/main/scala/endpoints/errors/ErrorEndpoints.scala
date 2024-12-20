//package endpoints.errors
//
//import modelClasses.ErrorInfoTraits.ErrorInfoTraits
//import sttp.tapir.*
//import sttp.model.StatusCode
//import endpoints.outputs.Common.*
//
//object ErrorEndpoints {
//
//  val deleteErrors: PublicEndpoint[Any, ErrorInfoTraits, Unit, Any] =
//    endpoint
//      .errorOut(
//        oneOf[ErrorInfoTraits](
//          oneOfVariant(StatusCode.NotFound, ErrorOutputsTraits.notFound),
//          oneOfVariant(StatusCode.BadRequest, ErrorOutputsTraits.badRequest),
//          oneOfDefaultVariant(ErrorOutputsTraits.unknown)
//        )
//      )
//      .out(statusCode(StatusCode.NoContent))
//
//}
