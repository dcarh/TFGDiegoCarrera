package server.routes.user.chatting

import cats.effect.IO
import cats.implicits.toSemigroupKOps
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.user.chatting.UserChatsEndpoints
import server.logics.user.chatting.UserChatsLogics

object UserChatsRoutes {

  private val getUserChats: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserChatsEndpoints.getUserChats.serverLogic(UserChatsLogics.getUserChats))

  val userChatsRoutes: HttpRoutes[IO] =
    getUserChats
}
