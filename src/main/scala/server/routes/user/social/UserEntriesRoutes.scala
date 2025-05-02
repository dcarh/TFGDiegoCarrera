package server.routes.user.social

import cats.effect.IO
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.user.social.UserEntriesEndpoints
import server.logics.user.social.UserEntriesLogics

object UserEntriesRoutes {

  private val getUserEntries: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserEntriesEndpoints.getUserEntries.serverLogic(UserEntriesLogics.getUserEntries))
    
  val userEntriesRoutes: HttpRoutes[IO] =
    getUserEntries

}
