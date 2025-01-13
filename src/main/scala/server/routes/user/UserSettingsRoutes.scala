package server.routes.user

import cats.effect.IO
import cats.implicits.toSemigroupKOps
import endpoints.app.user.UserSettingsEndpoints
import org.http4s.HttpRoutes
import server.logics.user.UserSettingsLogics
import sttp.tapir.server.http4s.Http4sServerInterpreter

object UserSettingsRoutes {

  private val getUserSettings: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserSettingsEndpoints.getUserSettings.serverLogic(UserSettingsLogics.getUserSettings))

  private val editUserSettings: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserSettingsEndpoints.editUserSettings.serverLogic(UserSettingsLogics.editUserSettings))

  val userSettingsRoutes: HttpRoutes[IO] =
    getUserSettings <+>
      editUserSettings

}
