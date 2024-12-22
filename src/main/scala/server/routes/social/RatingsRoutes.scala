package server.routes.social

import cats.effect.IO
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.social.RatingsEndpoints.*
import server.logics.social.RatingsLogics.*

object RatingsRoutes {

  val getRatingRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(getRatingEndpoint.serverLogic(getRatingLogic))

  val createRatingRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(createRatingEndpoint.serverLogic(createRatingLogic))

  val editRatingRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(editRatingEndpoint.serverLogic(editRatingLogic))

  val deleteRatingRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(deleteRatingEndpoint.serverLogic(deleteRatingLogic))

}
