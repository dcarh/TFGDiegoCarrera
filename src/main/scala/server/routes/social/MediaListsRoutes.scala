package server.routes.social

import cats.effect.IO
import cats.implicits.toSemigroupKOps
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.social.MediaListsEndpoints
import server.logics.social.MediaListsLogics

object MediaListsRoutes {

  private val getAllMediaLists: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(MediaListsEndpoints.getAllMediaLists.serverLogic(MediaListsLogics.getAllMediaLists))

  private val getMediaList: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(MediaListsEndpoints.getMediaList.serverLogic(MediaListsLogics.getMediaList))

  private val createMediaList: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(MediaListsEndpoints.createMediaList.serverLogic(MediaListsLogics.createMediaList))

  private val editMediaList: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(MediaListsEndpoints.editMediaList.serverLogic(MediaListsLogics.editMediaList))

  private val deleteMediaList: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(MediaListsEndpoints.deleteMediaList.serverLogic(MediaListsLogics.deleteMediaList))

  val mediaListsRoutes: HttpRoutes[IO] =
    getAllMediaLists  <+>
      getMediaList    <+>
      createMediaList <+>
      editMediaList   <+>
      deleteMediaList

}
