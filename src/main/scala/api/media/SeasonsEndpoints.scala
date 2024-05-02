package api.media

import io.circe.generic.auto._
import modelClasses.media.Season
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._

class SeasonsEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val pathSeasonId: EndpointInput[Season.Id] =
    path[Season.Id]("season_id")

  private val queryOrderBy: EndpointInput[String] =
    query[String]("order_by").description("Ordenar por")

  private val jsonSeasonListOut: EndpointOutput[List[Season]] =
    jsonBody[List[Season]]

  private val jsonSeasonOut: EndpointOutput[Season] =
    jsonBody[Season]


  private val seasonsBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "seasons")

  private val seasonBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "season")


  val seasonsEndpoint: PublicEndpoint[String, Unit, List[Season], Any] =
    seasonsBaseEndpoint
      .name("TV seasons endpoint")
      .description("This endpoint returns a list with all the TV seasons in the app")
      .get
      .in(queryOrderBy)
      .out(jsonSeasonListOut)

  val specificSeasonEndpoint: PublicEndpoint[Season.Id, Unit, Season, Any] =
    seasonBaseEndpoint
      .name("Specific TV season endpoint")
      .description("This endpoint returns a specific TV season by its Id")
      .get
      .in(pathSeasonId)
      .out(jsonSeasonOut)
  
}
