package server.routes.social

import cats.effect.IO
import cats.implicits.toSemigroupKOps
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.social.EntriesEndpoints
import server.logics.social.EntriesLogics

object EntriesRoutes {

  private val getAllEntries: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(EntriesEndpoints.getAllEntries.serverLogic(EntriesLogics.getAllEntries))
    
  private val getEntry: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(EntriesEndpoints.getEntry.serverLogic(EntriesLogics.getEntry))

  private val createEntry: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(EntriesEndpoints.createEntry.serverLogic(EntriesLogics.createEntry))

  private val editEntry: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(EntriesEndpoints.editEntry.serverLogic(EntriesLogics.editEntry))

  private val deleteEntry: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(EntriesEndpoints.deleteEntry.serverLogic(EntriesLogics.deleteEntry))

  val entriesRoutes: HttpRoutes[IO] =
    getAllEntries <+>
      getEntry    <+>
      createEntry <+>
      editEntry   <+>
      deleteEntry

}
