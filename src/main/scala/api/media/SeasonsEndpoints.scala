package api.media

import sttp.tapir._

import modelClasses.media.Season
import api.common.Inputs.inputs
import api.common.Outputs.outputs

class SeasonsEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val seasonsBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "seasons")

  private val seasonBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "season")
  
  val seasonsEndpoint: PublicEndpoint[Option[String], Unit, List[Season], Any] =
    seasonsBaseEndpoint
      .name("TV seasons endpoint")
      .description("This endpoint returns a list with all the TV seasons in the app")
      .get
      .in(inputs.querySortBy)
      .out(outputs.jsonSeasonListOut)

  val specificSeasonEndpoint: PublicEndpoint[Season.Id, Unit, Season, Any] =
    seasonBaseEndpoint
      .name("Specific TV season endpoint")
      .description("This endpoint returns a specific TV season by its Id")
      .get
      .in(inputs.pathSeasonId)
      .out(outputs.jsonSeasonOut)
  
}
