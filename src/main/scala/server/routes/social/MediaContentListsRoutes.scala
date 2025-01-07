package server.routes.social

import cats.effect.IO
import cats.implicits.toSemigroupKOps
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.social.MediaContentListsEndpoints
import server.logics.social.MediaContentListsLogics

object MediaContentListsRoutes {

  private val getAllMediaContentLists: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(MediaContentListsEndpoints.getAllMediaContentLists.serverLogic(MediaContentListsLogics.getAllMediaContentLists))

  private val getMediaContentList: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(MediaContentListsEndpoints.getMediaContentList.serverLogic(MediaContentListsLogics.getMediaContentList))

  private val createMediaContentList: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(MediaContentListsEndpoints.createMediaContentList.serverLogic(MediaContentListsLogics.createMediaContentList))

  private val editMediaContentList: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(MediaContentListsEndpoints.editMediaContentList.serverLogic(MediaContentListsLogics.editMediaContentList))

  private val deleteMediaContentList: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(MediaContentListsEndpoints.deleteMediaContentList.serverLogic(MediaContentListsLogics.deleteMediaContentList))

  val mediaContentListsRoutes: HttpRoutes[IO] =
    getAllMediaContentLists <+>
      getMediaContentList <+>
      createMediaContentList <+>
      editMediaContentList <+>
      deleteMediaContentList

}
