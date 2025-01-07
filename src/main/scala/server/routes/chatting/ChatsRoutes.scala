package server.routes.chatting

import cats.effect.IO
import cats.implicits.toSemigroupKOps
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.chatting.ChatsEndpoints
import server.logics.chatting.ChatsLogics

object ChatsRoutes {

  private val getChat: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(ChatsEndpoints.getChat.serverLogic(ChatsLogics.getChat))

  private val createChat: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(ChatsEndpoints.createChat.serverLogic(ChatsLogics.createChat))

  private val editChat: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(ChatsEndpoints.editChat.serverLogic(ChatsLogics.editChat))

  private val deleteChat: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(ChatsEndpoints.deleteChat.serverLogic(ChatsLogics.deleteChat))
    
  val chatsRoutes: HttpRoutes[IO] =
    getChat <+>
      createChat <+>
      editChat <+>
      deleteChat

}
