import cats.effect.*
import clients.TMDBClient
import endpoints.tmdb.{Movies, TVShows, Seasons, Episodes}
import modelClasses.app.media.{Movie, TVShow, Season, Episode}
import modelClasses.ids.Media.{MovieId, TVShowId}

object GetTMDBRequestExample extends IOApp {

  private val tmdbClient = TMDBClient()

  override def run(args: List[String]): IO[ExitCode] = {
    tmdbClient.executeRequest(Movies.requestedSimilarMoviesEndpoint, MovieId(550)).flatMap {
      case Right(resource) =>
        IO(println(s"Successfully retrieved resource: $resource")).as(ExitCode.Success)
      case Left(error) =>
        IO(println(s"Failed to retrieve resource: $error")).as(ExitCode.Error)
    }
  }
}