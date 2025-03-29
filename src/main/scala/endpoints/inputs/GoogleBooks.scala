package endpoints.inputs

import sttp.tapir.*

object GoogleBooks {

  val queryQ: EndpointInput[String] =
    query[String]("q")

  val queryLangRestrict: EndpointInput[String] =
    query[String]("langRestrict")

  val queryOrderBy: EndpointInput[String] =
    query[String]("orderBy")

  val queryProjection: EndpointInput[String] =
    query[String]("projection")

  val queryMaxResults: EndpointInput[Int] =
    query[Int]("maxResults")
}
