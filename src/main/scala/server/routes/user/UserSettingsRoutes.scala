package server.routes.user

import cats.effect.IO
import cats.implicits.toSemigroupKOps
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

object UserSettingsRoutes {
  
  val userSettingsRoutes: HttpRoutes[IO] = ???
  
}
