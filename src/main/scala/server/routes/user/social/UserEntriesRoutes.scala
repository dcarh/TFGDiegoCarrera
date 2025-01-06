package server.routes.user.social

import cats.effect.IO
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.user.social.UserEntriesEndpoints.*
import server.logics.user.social.UserEntriesLogics.*

object UserEntriesRoutes {

  val getUserEntriesRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(getUserEntries.serverLogic(getUserEntriesLogic))

}
