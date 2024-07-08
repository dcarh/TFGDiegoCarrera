package endpoints.app.media

import sttp.tapir._

import endpoints.inputs.Common._
import endpoints.outputs.Common._
import modelClasses.app.media.TVShow

import java.util.UUID

object TvShowsEndpoints {

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
      .in(QueryInputs.querySortBy)
      .out(MediaOutputs.jsonTVShowListOut)

  val specificTVShowEndpoint: PublicEndpoint[TVShow.Id, Unit, TVShow, Any] =
    tvShowBaseEndpoint
      .name("Specific TV show endpoint")
      .description("This endpoint returns a specific TV show by its Id")
      .get
      .in(PathInputs.pathTVShowId)
      .out(MediaOutputs.jsonTVShowOut)

}
