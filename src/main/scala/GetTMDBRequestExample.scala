import cats.effect.*
import clients.TMDBClient
import endpoints.tmdb.{Episodes, Movies, Seasons, TVShows}
import modelClasses.app.media.{Episode, Movie, Season, TvShow}
import modelClasses.ids.Media.{EpisodeNumber, MovieId, SeasonNumber, TvShowId}

object GetTMDBRequestExample extends IOApp {

  private val tmdbClient = TMDBClient()

  override def run(args: List[String]): IO[ExitCode] = {
    tmdbClient.executeRequest(Episodes.requestEpisodeEndpoint, (TvShowId(244623), SeasonNumber(1), EpisodeNumber(1))).flatMap {
      case Right(resource) =>
        IO(println(s"Successfully retrieved resource: $resource")).as(ExitCode.Success)
      case Left(error) =>
        IO(println(s"Failed to retrieve resource: $error")).as(ExitCode.Error)
    }
  }
}