package server.routes.user.media

import cats.effect.IO
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.user.media.UserInProgressContentEndpoints.*
import server.logics.user.media.UserInProgressContentLogics.*

object UserInProgressContentRoutes {

  val getInProgressRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(getInProgress.serverLogic(getInProgressLogic))

  val addInProgressTvShowRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(addInProgressTvShow.serverLogic(addInProgressTvShowLogic))

  val addInProgressSeasonRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(addInProgressSeason.serverLogic(addInProgressSeasonLogic))

  val addInProgressVideogameRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(addInProgressVideogame.serverLogic(addInProgressVideogameLogic))

  val addInProgressBookRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(addInProgressBook.serverLogic(addInProgressBookLogic))

}
