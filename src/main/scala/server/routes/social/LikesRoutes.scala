package server.routes.social

import cats.effect.IO
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.social.LikesEndpoints.*
import server.logics.social.LikesLogics.*

object LikesRoutes {

  val getLikeRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(getLikeEndpoint.serverLogic(getLikeLogic))

  val createLikeRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(createLikeEndpoint.serverLogic(createLikeLogic))

  val deleteLikeRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(deleteLikeEndpoint.serverLogic(deleteLikeLogic))

}
