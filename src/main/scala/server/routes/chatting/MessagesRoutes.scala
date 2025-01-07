package server.routes.chatting

import cats.effect.IO
import cats.implicits.toSemigroupKOps
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.chatting.MessagesEndpoints
import server.logics.chatting.MessagesLogics

object MessagesRoutes {

  private val getMessage: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(MessagesEndpoints.getMessage.serverLogic(MessagesLogics.getMessage))

  private val createMessage: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(MessagesEndpoints.createMessage.serverLogic(MessagesLogics.createMessage))

  private val editMessage: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(MessagesEndpoints.editMessage.serverLogic(MessagesLogics.editMessage))

  private val deleteMessage: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(MessagesEndpoints.deleteMessage.serverLogic(MessagesLogics.deleteMessage))
    
  val messagesRoutes: HttpRoutes[IO] =
    getMessage <+>
      createMessage <+>
      editMessage <+>
      deleteMessage

}
