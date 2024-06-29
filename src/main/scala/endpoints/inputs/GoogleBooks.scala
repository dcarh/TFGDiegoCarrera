package endpoints.inputs

import sttp.tapir.*
import sttp.tapir.generic.auto.*
import sttp.tapir.json.circe.*
import io.circe.generic.auto.*

object GoogleBooks {

  val queryQ: EndpointInput[String] =
    query[String]("q")

  val queryLangRestrict: EndpointInput[String] =
    query[String]("langRestrict")

  val queryOrderBy: EndpointInput[String] =
    query[String]("orderBy")

  val queryProjection: EndpointInput[String] =
    query[String]("projection")
}
