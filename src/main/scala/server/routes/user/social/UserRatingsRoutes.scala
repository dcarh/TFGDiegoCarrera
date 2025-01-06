package server.routes.user.social

import cats.effect.IO
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.user.social.UserRatingsEndpoints.*
import server.logics.user.social.UserRatingsLogics.*

object UserRatingsRoutes {

  val getUserRatingsRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(getUserRatings.serverLogic(getUserRatingsLogic))

}
