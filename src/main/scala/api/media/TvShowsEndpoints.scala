package api.media

import sttp.tapir._

import modelClasses.media.TVShow
import api.common.Inputs.inputs
import api.common.Outputs.outputs

import java.util.UUID

class TvShowsEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val tvShowsBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "tv_shows")

  private val tvShowBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "tv_show")
  
  val tvShowsEndpoint: PublicEndpoint[Option[String], Unit, List[TVShow], Any] =
    tvShowsBaseEndpoint
      .name("TV shows endpoint")
      .description("This endpoint returns a list with all the TV shows in the app")
      .get
      .in(inputs.querySortBy)
      .out(outputs.jsonTVShowListOut)

  val specificTVShowEndpoint: PublicEndpoint[TVShow.Id, Unit, TVShow, Any] =
    tvShowBaseEndpoint
      .name("Specific TV show endpoint")
      .description("This endpoint returns a specific TV show by its Id")
      .get
      .in(inputs.pathTVShowId)
      .out(outputs.jsonTVShowOut)

}
