package routes.user

import cats.effect.IO
import cats.implicits.toSemigroupKOps
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.user.UserEndpoints
import logics.user.UserLogics

object UserRoutes {

  private val getAllUsers: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserEndpoints.getAllUsers.serverLogic(UserLogics.getAllUsers))

  private val getUser: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserEndpoints.getUser.serverLogic(UserLogics.getUser))
    
  private val getProfile: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserEndpoints.getProfile.serverLogic(UserLogics.getProfile))
  
  private val createUser: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserEndpoints.createUser.serverLogic(UserLogics.createUser))
  
  private val editUser: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserEndpoints.editUser.serverLogic(UserLogics.editUser))
  
  private val deleteUser: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserEndpoints.deleteUser.serverLogic(UserLogics.deleteUser))
  
  val userRoutes: HttpRoutes[IO] =
    getAllUsers  <+>
      getUser    <+>
      getProfile <+>
      createUser <+>
      editUser   <+>
      deleteUser

}
