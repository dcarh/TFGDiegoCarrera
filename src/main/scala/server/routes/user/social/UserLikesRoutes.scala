package server.routes.user.social

import cats.effect.IO
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.user.social.UserLikesEndpoints.*
import server.logics.user.social.UserLikesLogics.*

object UserLikesRoutes {

  val getUserLikesRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(getUserLikes.serverLogic(getUserLikesLogic))

}
