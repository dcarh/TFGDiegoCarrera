package routes.user.social

import cats.effect.IO
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.user.social.UserRatingsEndpoints
import logics.user.social.UserRatingsLogics

object UserRatingsRoutes {

  private val getUserRatings: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserRatingsEndpoints.getUserRatings.serverLogic(UserRatingsLogics.getUserRatings))

  val userRatingsRoutes: HttpRoutes[IO] =
    getUserRatings

}
