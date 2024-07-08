package endpoints.igdb

import sttp.tapir.*

import endpoints.outputs.Common._
import endpoints.inputs.IGDB._
import modelClasses.ErrorInfo

object Base {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  val igdbBaseEndpoint:
    (String, String) => PublicEndpoint[(String, String, String, String), ErrorInfo, Unit, Any] =
    (name, description) =>
      endpoint
        .name(name)
        .description(description)
        .post
        .in(Headers.accept)
        .in(Headers.clientId)
        .in(Headers.authorization)
        .in(Body.stringBody)
        .errorOut(ApiOutputs.jsonErrorInfoOut)
}
