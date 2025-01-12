package server.routes.user

import cats.effect.IO
import cats.implicits.toSemigroupKOps
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.user.UserEndpoints
import server.logics.user.UserLogics

object UserRoutes {

  private val getUser: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserEndpoints.getUser.serverLogic(UserLogics.getUser))
  
  private val createUser: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserEndpoints.createUser.serverLogic(UserLogics.createUser))
  
  private val deleteUser: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserEndpoints.deleteUser.serverLogic(UserLogics.deleteUser))
  
  val userRoutes: HttpRoutes[IO] =
    getUser <+>
      createUser <+>
      deleteUser

}
