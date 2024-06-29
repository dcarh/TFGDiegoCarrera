package endpoints.googleBooks

import sttp.tapir.*

import endpoints.inputs.Common._
import endpoints.outputs.Common._
import modelClasses.app.media.{Episode, Movie, Season, TVShow}
import modelClasses.app.media.IDs.BookId
import modelClasses.ErrorInfo

object Base {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val googleBooksBaseEndpoint:
    (String, String) => PublicEndpoint[Unit, ErrorInfo, Unit, Any] =
    (name, description) =>
      endpoint
        .name(name)
        .description(description)
        .get
        .errorOut(ApiOutputs.jsonErrorInfoOut)

  val bookBaseEndpoint:
    (String, String) => PublicEndpoint[Unit, ErrorInfo, Unit, Any] =
    (name, description) =>
      googleBooksBaseEndpoint(name, description)
        .in("volumes")

}
