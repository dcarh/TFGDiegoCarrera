package server.routes.user.social

import cats.effect.IO
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.user.social.UserListsEndpoints.*
import server.logics.user.social.UserListsLogics.*

object UserListsRoutes {

  val getUserListsRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(getUserLists.serverLogic(getUserListsLogic))

}
