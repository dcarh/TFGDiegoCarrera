package server.routes.media

import cats.effect.IO
import cats.implicits.toSemigroupKOps
import endpoints.app.media.MovieEndpoints
import org.http4s.HttpRoutes
import server.logics.media.MovieLogics
import sttp.tapir.server.http4s.Http4sServerInterpreter

object MovieRoutes {

  private val getMovie: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().
      toRoutes(MovieEndpoints.getMovie.serverLogic(MovieLogics.getMovie))

  private val getEntriesForMovie: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().
      toRoutes(MovieEndpoints.getEntriesForMovie.serverLogic(MovieLogics.getEntriesForMovie))

  private val getMediaListsForMovie: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().
      toRoutes(MovieEndpoints.getMediaListsForMovie.serverLogic(MovieLogics.getMediaListsForMovie))

  val movieRoutes: HttpRoutes[IO] =
    getMovie <+>
      getEntriesForMovie <+>
      getMediaListsForMovie

}
