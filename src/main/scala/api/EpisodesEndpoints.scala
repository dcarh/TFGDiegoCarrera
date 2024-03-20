package api

import io.circe.generic.auto._
import modelClasses.Episode
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._

class EpisodesEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val pathEpisodeId: EndpointInput[Int] =
    path[Int]("episode_id")

  private val queryOrderBy: EndpointInput[String] =
    query[String]("order_by").description("Ordenar por")

  private val jsonEpisodeListOut: EndpointOutput[Seq[Episode]] =
    jsonBody[Seq[Episode]]

  private val jsonEpisodeOut: EndpointOutput[Episode] =
    jsonBody[Episode]


  private val episodesBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "episodes")

  private val episodeBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "episode")


  val episodesEnpoint: PublicEndpoint[String, Unit, Seq[Episode], Any] =
    episodesBaseEndpoint
      .in(queryOrderBy)
      .out(jsonEpisodeListOut)

  val specificEpisodeEnpoint: PublicEndpoint[Int, Unit, Episode, Any] =
    episodeBaseEndpoint
      .in(pathEpisodeId)
      .out(jsonEpisodeOut)

}
