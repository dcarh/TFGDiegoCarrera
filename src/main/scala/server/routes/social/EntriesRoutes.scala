package server.routes.social

import cats.effect.IO
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.social.EntriesEndpoints.*
import server.logics.social.EntriesLogics.*

object EntriesRoutes {

  val getAllEntriesRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(getAllEntriesEndpoint.serverLogic(getAllEntriesLogic))
    
  val getEntryRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(getEntryEndpoint.serverLogic(getEntryLogic))

  val createEntryRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(createEntryEndpoint.serverLogic(createEntryLogic))

  val editEntryRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(editEntryEndpoint.serverLogic(editEntryLogic))

  val deleteEntryRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(deleteEntryEndpoint.serverLogic(deleteEntryLogic))

}
