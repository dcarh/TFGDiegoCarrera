import cats.effect.*
import cats.implicits.*
import clients.TMDBClient

import concurrent.duration.DurationInt
import endpoints.tmdb.{Episodes, Movies, Seasons, TVShows}
import modelClasses.app.media.{Episode, Movie, Season, TvShow}
import modelClasses.ids.Media.{EpisodeNumber, MovieId, SeasonNumber, TvShowId}

object GetTMDBRequestExampleFOR extends IOApp {

  private val tmdbClient = TMDBClient()

  override def run(args: List[String]): IO[ExitCode] = {

    val requests = (1 to 300).toList // Limito a 300 para evitar un problema de rendimiento
      .map(i =>
        IO.sleep(1.second) *>
        tmdbClient.executeRequest(Episodes.requestEpisodeEndpoint, (TvShowId(i), SeasonNumber(1), EpisodeNumber(1)))
        .flatMap {
          case Right(resource) => IO(println(s"$i Successfully retrieved resource: $resource"))
          case Left(error) => IO(println(s"$i Failed to retrieve resource: $error"))
        }
      )

    // Ejecuta todas las peticiones de manera secuencial
    requests.traverse(identity).flatMap { _ =>
      // Ejecutar la petición final después de completar todas las demás
      tmdbClient.executeRequest(Movies.requestMovieEndpoint, MovieId(113112)).flatMap {
        case Right(resource) =>
          IO(println(s"Successfully retrieved resource: $resource")).as(ExitCode.Success)
        case Left(error) =>
          IO(println(s"Failed to retrieve resource: $error")).as(ExitCode.Error)
      }
    }

//    requests.parTraverse(identity).flatMap { _ =>
//      // Ejecutar la petición final después de completar todas las demás
//      tmdbClient.executeRequest(Movies.requestMovieEndpoint, MovieId(113112)).flatMap {
//        case Right(resource) =>
//          IO(println(s"Successfully retrieved resource: $resource")).as(ExitCode.Success)
//        case Left(error) =>
//          IO(println(s"Failed to retrieve resource: $error")).as(ExitCode.Error)
//      }
//    }
  }
}