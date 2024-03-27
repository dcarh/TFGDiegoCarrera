package api

import io.circe.generic.auto._
import modelClasses.TVShow
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._

import java.util.UUID

class TvShowsEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val pathTVShowId: EndpointInput[Int] =
    path[Int]("tv_show_id")

  private val queryOrderBy: EndpointInput[String] =
    query[String]("order_by").description("Ordenar por")

  private val jsonTVShowListOut: EndpointOutput[Seq[TVShow]] =
    jsonBody[Seq[TVShow]]

  private val jsonTVShowOut: EndpointOutput[TVShow] =
    jsonBody[TVShow]


  private val tvShowsBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "tv_shows")

  private val tvShowBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "tv_show")


  val tvShowsEndpoint: PublicEndpoint[String, Unit, Seq[TVShow], Any] =
    tvShowsBaseEndpoint
      .name("TV shows endpoint")
      .description("This endpoint returns a list with all the TV shows in the app")
      .get
      .in(queryOrderBy)
      .out(jsonTVShowListOut)

  val specificTVShowEndpoint: PublicEndpoint[Int, Unit, TVShow, Any] =
    tvShowBaseEndpoint
      .name("Specific TV show endpoint")
      .description("This endpoint returns a specific TV show by its Id")
      .get
      .in(pathTVShowId)
      .out(jsonTVShowOut)

}
