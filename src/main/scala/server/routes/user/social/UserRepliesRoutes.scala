package server.routes.user.social

import cats.effect.IO
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.user.social.UserRepliesEndpoints
import server.logics.user.social.UserRepliesLogics

object UserRepliesRoutes {

  private val getUserReplies: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserRepliesEndpoints.getUserReplies.serverLogic(UserRepliesLogics.getUserReplies))

  val userRepliesRoutes: HttpRoutes[IO] =
    getUserReplies

}
