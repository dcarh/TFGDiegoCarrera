package api

import cats.effect.*
import io.circe.Printer
import io.circe.generic.auto._
import io.circe.syntax.*
import model.{User,Element, Movie, TVShow, Season, Episode, Videogame, Book, ElementList, Log, Comment, Article,
  Settings, Review, ErrorInfo, Chat}
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import sttp.tapir.model.UsernamePassword
import sttp.model.StatusCode

import java.util.UUID

class TvShowsEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val pathTVShowId: EndpointInput[Int] =
    path[Int]("tv_show_id")

  private val queryOrderBy: EndpointInput[String] =
    query[String]("order_by").description("Ordenar por")

  private val jsonTVShowListOut: EndpointOutput[List[TVShow]] =
    jsonBody[List[TVShow]]

  private val jsonTVShowOut: EndpointOutput[TVShow] =
    jsonBody[TVShow]


  private val tvShowsBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "tv_shows")

  private val tvShowBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "tv_show")


  val tvShowsEnpoint: PublicEndpoint[String, Unit, List[TVShow], Any] =
    tvShowsBaseEndpoint
      .in(queryOrderBy)
      .out(jsonTVShowListOut)

  val specificTVShowEnpoint: PublicEndpoint[Int, Unit, TVShow, Any] =
    tvShowBaseEndpoint
      .in(pathTVShowId)
      .out(jsonTVShowOut)

}
