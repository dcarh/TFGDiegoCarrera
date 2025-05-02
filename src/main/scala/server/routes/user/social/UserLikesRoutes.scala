package server.routes.user.social

import cats.effect.IO
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.user.social.UserLikesEndpoints
import server.logics.user.social.UserLikesLogics

object UserLikesRoutes {

  private val getUserLikes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserLikesEndpoints.getUserLikes.serverLogic(UserLikesLogics.getUserLikes))

  val userLikesRoutes: HttpRoutes[IO] =
    getUserLikes

}
