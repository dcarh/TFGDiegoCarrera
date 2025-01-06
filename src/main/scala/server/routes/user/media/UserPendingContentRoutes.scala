package server.routes.user.media

import cats.effect.IO
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.user.media.UserPendingContentEndpoints.*
import server.logics.user.media.UserPendingContentLogics.*

object UserPendingContentRoutes {

  val getPendingRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(getPending.serverLogic(getPendingLogic))

  val addPendingMovieRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(addPendingMovie.serverLogic(addPendingMovieLogic))

  val addPendingTvShowRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(addPendingTvShow.serverLogic(addPendingTvShowLogic))

  val addPendingSeasonRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(addPendingSeason.serverLogic(addPendingSeasonLogic))

  val addPendingEpisodeRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(addPendingEpisode.serverLogic(addPendingEpisodeLogic))

  val addPendingVideogameRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(addPendingVideogame.serverLogic(addPendingVideogameLogic))

  val addPendingBookRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(addPendingBook.serverLogic(addPendingBookLogic))

}
