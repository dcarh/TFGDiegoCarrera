package server.routes.user.social

import cats.effect.IO
import cats.implicits.toSemigroupKOps
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.user.social.UserListsEndpoints
import server.logics.user.social.UserListsLogics

object UserListsRoutes {

  private val getUserLists: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserListsEndpoints.getUserLists.serverLogic(UserListsLogics.getUserLists))

  val userListsRoutes: HttpRoutes[IO] =
    getUserLists

}
