package api

import io.circe.generic.auto._
import modelClasses.Season
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._

class SeasonsEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val pathSeasonId: EndpointInput[Int] =
    path[Int]("season_id")

  private val queryOrderBy: EndpointInput[String] =
    query[String]("order_by").description("Ordenar por")

  private val jsonSeasonListOut: EndpointOutput[Seq[Season]] =
    jsonBody[Seq[Season]]

  private val jsonSeasonOut: EndpointOutput[Season] =
    jsonBody[Season]


  private val seasonsBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "seasons")

  private val seasonBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "season")


  val seasonsEnpoint: PublicEndpoint[String, Unit, Seq[Season], Any] =
    seasonsBaseEndpoint
      .in(queryOrderBy)
      .out(jsonSeasonListOut)

  val specificSeasonEnpoint: PublicEndpoint[Int, Unit, Season, Any] =
    seasonBaseEndpoint
      .in(pathSeasonId)
      .out(jsonSeasonOut)
  
}
