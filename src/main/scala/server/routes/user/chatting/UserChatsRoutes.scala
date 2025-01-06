package server.routes.user.chatting

import cats.effect.IO
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.user.chatting.UserChatsEndpoints.*
import server.logics.user.chatting.UserChatsLogics.*

object UserChatsRoutes {

  val getChatRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(getUserChats.serverLogic(getUserChatsLogic))
  
}
