package endpoints.app.media

import sttp.tapir._

import endpoints.inputs.Common._
import endpoints.outputs.Common._
import modelClasses.app.media.Season
import modelClasses.ids.Media.SeasonNumber

object SeasonsEndpoints {

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
      .in(QueryInputs.querySortBy)
      .out(MediaOutputs.jsonSeasonListOut)

  val specificSeasonEndpoint: PublicEndpoint[SeasonNumber, Unit, Season, Any] =
    seasonBaseEndpoint
      .name("Specific TV season endpoint")
      .description("This endpoint returns a specific TV season by its Id")
      .get
      .in(PathInputs.pathSeasonNumber)
      .out(MediaOutputs.jsonSeasonOut)
  
}
