package server.routes.user.social

import cats.effect.IO
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.user.social.UserMediaListsEndpoints
import server.logics.user.social.UserMediaListsLogics

object UserMediaListsRoutes {

  private val getUserMediaLists: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserMediaListsEndpoints.getUserMediaLists.serverLogic(UserMediaListsLogics.getUserMediaLists))

  val userMediaListsRoutes: HttpRoutes[IO] =
    getUserMediaLists

}
