package server.routes.social

import cats.effect.IO
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.social.RepliesEndpoints.*
import server.logics.social.RepliesLogics.*

object RepliesRoutes {

  val getReplyRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(getReplyEndpoint.serverLogic(getReplyLogic))

  val createReplyRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(createReplyEndpoint.serverLogic(createReplyLogic))

  val editReplyRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(editReplyEndpoint.serverLogic(editReplyLogic))

  val deleteReplyRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(deleteReplyEndpoint.serverLogic(deleteReplyLogic))

}
