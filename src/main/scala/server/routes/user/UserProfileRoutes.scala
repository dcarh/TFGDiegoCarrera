package server.routes.user

import cats.effect.IO
import cats.implicits.toSemigroupKOps
import endpoints.app.user.UserProfileEndpoints
import org.http4s.HttpRoutes
import server.logics.user.UserProfileLogics
import sttp.tapir.server.http4s.Http4sServerInterpreter

object UserProfileRoutes {

  private val getUserProfile: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserProfileEndpoints.getUserProfile.serverLogic(UserProfileLogics.getUserProfile))
  
  private val editUserProfile: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserProfileEndpoints.editUserProfile.serverLogic(UserProfileLogics.editUserProfile))
  
  val userProfileRoutes: HttpRoutes[IO] =
    getUserProfile <+>
      editUserProfile

}
