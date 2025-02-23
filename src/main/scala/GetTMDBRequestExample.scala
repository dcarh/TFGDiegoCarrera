import cats.effect.*
import clients.TMDBClient
import endpoints.tmdb.{TvEpisodes, Movies, TvSeasons, TvShows}
import modelClasses.app.media.{TvEpisode, Movie, TvSeason, TvShow}
import modelClasses.ids.Media.{TvEpisodeNumber, MovieId, TvSeasonNumber, TvShowId}

object GetTMDBRequestExample extends IOApp {

  private val tmdbClient = TMDBClient()

  override def run(args: List[String]): IO[ExitCode] = {
    tmdbClient.executeRequest(TvShows.requestTvShowEndpoint, TvShowId(244623)).flatMap {
      case Right(resource) =>
        IO(println(s"Successfully retrieved resource: $resource")).as(ExitCode.Success)
      case Left(error) =>
        IO(println(s"Failed to retrieve resource: $error")).as(ExitCode.Error)
    }
  }
}