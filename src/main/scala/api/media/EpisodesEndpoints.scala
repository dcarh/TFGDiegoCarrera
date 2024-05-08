package api.media

import sttp.tapir._

import modelClasses.media.Episode
import api.common.Inputs.inputs
import api.common.Outputs.outputs

class EpisodesEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val episodesBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "episodes")

  private val episodeBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "episode")
  
  val episodesEndpoint: PublicEndpoint[Option[String], Unit, List[Episode], Any] =
    episodesBaseEndpoint
      .name("TV episodes endpoint")
      .description("This endpoint returns a list with all the TV episodes in the app")
      .get
      .in(inputs.querySortBy)
      .out(outputs.jsonEpisodeListOut)

  val specificEpisodeEndpoint: PublicEndpoint[Episode.Id, Unit, Episode, Any] =
    episodeBaseEndpoint
      .name("Specific TV episode endpoint")
      .description("This endpoint returns a specific TV episode by its Id")
      .get
      .in(inputs.pathEpisodeId)
      .out(outputs.jsonEpisodeOut)

}
