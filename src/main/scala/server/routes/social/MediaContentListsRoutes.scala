package server.routes.social

import cats.effect.IO
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.social.MediaContentListsEndpoints.*
import server.logics.social.MediaContentListsLogics.*

object MediaContentListsRoutes {

  val getAllMediaContentListsRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(getAllMediaContentListsEndpoint.serverLogic(getAllMediaContentListsLogic))

  val getMediaContentListRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(getMediaContentListEndpoint.serverLogic(getMediaContentListLogic))

  val createMediaContentListRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(createMediaContentListEndpoint.serverLogic(createMediaContentListLogic))

  val editMediaContentListRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(editMediaContentListEndpoint.serverLogic(editMediaContentListLogic))

  val deleteMediaContentListRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(deleteMediaContentListEndpoint.serverLogic(deleteMediaContentListLogic))

}
