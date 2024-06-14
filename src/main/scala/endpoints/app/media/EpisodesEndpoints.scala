package endpoints.app.media

import sttp.tapir._

import endpoints.common.Inputs._
import endpoints.common.Outputs._
import modelClasses.app.media.Episode

object EpisodesEndpoints {

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
      .in(QueryInputs.querySortBy)
      .out(MediaOutputs.jsonEpisodeListOut)

  val specificEpisodeEndpoint: PublicEndpoint[Episode.Number, Unit, Episode, Any] =
    episodeBaseEndpoint
      .name("Specific TV episode endpoint")
      .description("This endpoint returns a specific TV episode by its Id")
      .get
      .in(PathInputs.pathEpisodeNumber)
      .out(MediaOutputs.jsonEpisodeOut)

}
