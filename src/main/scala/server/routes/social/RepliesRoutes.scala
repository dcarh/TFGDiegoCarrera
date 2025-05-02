package server.routes.social

import cats.effect.IO
import cats.implicits.toSemigroupKOps
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.social.RepliesEndpoints
import server.logics.social.RepliesLogics

object RepliesRoutes {

  private val getReply: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(RepliesEndpoints.getReply.serverLogic(RepliesLogics.getReply))

  private val createReply: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(RepliesEndpoints.createReply.serverLogic(RepliesLogics.createReply))

  private val editReply: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(RepliesEndpoints.editReply.serverLogic(RepliesLogics.editReply))

  private val deleteReply: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(RepliesEndpoints.deleteReply.serverLogic(RepliesLogics.deleteReply))

  val repliesRoutes: HttpRoutes[IO] =
    getReply      <+>
      createReply <+>
      editReply   <+>
      deleteReply

}
