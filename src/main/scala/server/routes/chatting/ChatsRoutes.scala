package server.routes.chatting

import cats.effect.IO
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.chatting.ChatsEndpoints.*
import server.logics.chatting.ChatsLogics.*

object ChatsRoutes {

  val getChatRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(getChatEndpoint.serverLogic(getChatLogic))

  val createChatRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(createChatEndpoint.serverLogic(createChatLogic))

  val editChatRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(editChatEndpoint.serverLogic(editChatLogic))

  val deleteChatRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(deleteChatEndpoint.serverLogic(deleteChatLogic))

}
