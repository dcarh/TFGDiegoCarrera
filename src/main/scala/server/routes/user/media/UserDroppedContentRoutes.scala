package server.routes.user.media

import cats.effect.IO
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.user.media.UserDroppedContentEndpoints.*
import server.logics.user.media.UserDroppedContentLogics.*

object UserDroppedContentRoutes {

  val getDroppedRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(getDropped.serverLogic(getDroppedLogic))

  val addDroppedMovieRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(addDroppedMovie.serverLogic(addDroppedMovieLogic))

  val addDroppedTvShowRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(addDroppedTvShow.serverLogic(addDroppedTvShowLogic))

  val addDroppedSeasonRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(addDroppedSeason.serverLogic(addDroppedSeasonLogic))

  val addDroppedEpisodeRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(addDroppedEpisode.serverLogic(addDroppedEpisodeLogic))

  val addDroppedVideogameRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(addDroppedVideogame.serverLogic(addDroppedVideogameLogic))

  val addDroppedBookRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(addDroppedBook.serverLogic(addDroppedBookLogic))

}
