package routes.chatting

import cats.effect.IO
import cats.implicits.toSemigroupKOps
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.chatting.ChattingEndpoints
import logics.chatting.ChattingLogics

object ChattingRoutes {
  
  private val getChats: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(ChattingEndpoints.getChats.serverLogic(ChattingLogics.getChats))
    
  private val getChat: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(ChattingEndpoints.getChat.serverLogic(ChattingLogics.getChat))

  private val archiveChat: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(ChattingEndpoints.archiveChat.serverLogic(ChattingLogics.archiveChat))

  private val deleteChat: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(ChattingEndpoints.deleteChat.serverLogic(ChattingLogics.deleteChat))

  private val getChatMessages: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(ChattingEndpoints.getChatMessages.serverLogic(ChattingLogics.getChatMessages))

  private val getMessage: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(ChattingEndpoints.getMessage.serverLogic(ChattingLogics.getMessage))

  private val sendMessage: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(ChattingEndpoints.sendMessage.serverLogic(ChattingLogics.sendMessage))

  private val deleteMessage: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(ChattingEndpoints.deleteMessage.serverLogic(ChattingLogics.deleteMessage))

  val chattingRoutes: HttpRoutes[IO] =
    getChats          <+>
      getChat         <+>
      archiveChat     <+>
      deleteChat      <+>
      getChatMessages <+>
      getMessage      <+>
      sendMessage     <+>
      deleteMessage

}
