import cats.effect.*
import clients.TMDBClient
import endpoints.tmdb.{Movies, TVShows, Seasons, Episodes}
import modelClasses.app.media.{Movie, TVShow, Season, Episode}

object GetRequestExample extends IOApp {

  private val tmdbClient = TMDBClient()

  override def run(args: List[String]): IO[ExitCode] = {
    tmdbClient.generalRequestNotGeneralized(Episodes.requestedCreditsForEpisodeEndpoint, (1396, 2, 5)).flatMap {
      case Right(resource) =>
        IO(println(s"Successfully retrieved resource: $resource")).as(ExitCode.Success)
      case Left(error) =>
        IO(println(s"Failed to retrieve resource: $error")).as(ExitCode.Error)
    }
  }
}
