package server.routes.user.media

import cats.effect.IO
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.user.media.UserOnHoldContentEndpoints.*
import server.logics.user.media.UserOnHoldContentLogics.*

object UserOnHoldContentRoutes {

  val getOnHoldRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(getOnHold.serverLogic(getOnHoldLogic))

  val addOnHoldTvShowRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(addOnHoldTvShow.serverLogic(addOnHoldTvShowLogic))

  val addOnHoldSeasonRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(addOnHoldSeason.serverLogic(addOnHoldSeasonLogic))

  val addOnHoldVideogameRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(addOnHoldVideogame.serverLogic(addOnHoldVideogameLogic))

  val addOnHoldBookRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(addOnHoldBook.serverLogic(addOnHoldBookLogic))

}
