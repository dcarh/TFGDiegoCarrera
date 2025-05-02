package server.routes.user.network

import cats.effect.IO
import cats.implicits.toSemigroupKOps
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.user.network.UserNetworkEndpoints
import server.logics.user.network.UserNetworkLogics

object UserNetworkRoutes {
  
  private val getFollowers: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserNetworkEndpoints.getFollowers.serverLogic(UserNetworkLogics.getFollowers))

  private val getFollowing: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserNetworkEndpoints.getFollowing.serverLogic(UserNetworkLogics.getFollowing))

  private val getBlocked: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserNetworkEndpoints.getBlocked.serverLogic(UserNetworkLogics.getBlocked))

  private val followUser: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserNetworkEndpoints.followUser.serverLogic(UserNetworkLogics.followUser))

  private val unfollowUser: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserNetworkEndpoints.unfollowUser.serverLogic(UserNetworkLogics.unfollowUser))

  private val blockUser: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserNetworkEndpoints.blockUser.serverLogic(UserNetworkLogics.blockUser))
    
  private val unblockUser: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserNetworkEndpoints.unblockUser.serverLogic(UserNetworkLogics.unblockUser))

  val userNetworkRoutes: HttpRoutes[IO] =
    getFollowers   <+>
      getFollowing <+>
      getBlocked   <+>
      followUser   <+>
      unfollowUser <+>
      blockUser    <+>
      unblockUser
}
