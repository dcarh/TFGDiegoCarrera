package server.routes.chatting

import cats.effect.IO
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.chatting.MessagesEndpoints.*
import server.logics.chatting.MessagesLogics.*

object MessagesRoutes {

  val getChatRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(getMessageEndpoint.serverLogic(getMessageLogic))

  val createChatRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(createMessageEndpoint.serverLogic(createMessageLogic))

  val editChatRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(editMessageEndpoint.serverLogic(editMessageLogic))

  val deleteChatRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(deleteMessageEndpoint.serverLogic(deleteMessageLogic))

}
