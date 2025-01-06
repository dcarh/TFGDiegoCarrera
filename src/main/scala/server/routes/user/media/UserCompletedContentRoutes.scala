package server.routes.user.media

import cats.effect.IO
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.user.media.UserCompletedContentEndpoints.*
import server.logics.user.media.UserCompletedContentLogics.*

object UserCompletedContentRoutes {

  val getCompletedRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(getCompleted.serverLogic(getCompletedLogic))

  val addCompletedMovieRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(addCompletedMovie.serverLogic(addCompletedMovieLogic))

  val addCompletedTvShowRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(addCompletedTvShow.serverLogic(addCompletedTvShowLogic))

  val addCompletedSeasonRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(addCompletedSeason.serverLogic(addCompletedSeasonLogic))

  val addCompletedEpisodeRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(addCompletedEpisode.serverLogic(addCompletedEpisodeLogic))

  val addCompletedVideogameRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(addCompletedVideogame.serverLogic(addCompletedVideogameLogic))

  val addCompletedBookRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(addCompletedBook.serverLogic(addCompletedBookLogic))

}
