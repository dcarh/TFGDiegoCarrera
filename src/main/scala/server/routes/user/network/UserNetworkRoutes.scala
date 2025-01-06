package server.routes.user.network

import cats.effect.IO
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.user.network.UserNetworkEndpoints.*
import server.logics.user.network.UserNetworkLogics.*

object UserNetworkRoutes {
  
  val getFollowersRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(getFollowers.serverLogic(getFollowersLogic))

  val getFollowingRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(getFollowing.serverLogic(getFollowingLogic))

  val getBlockedRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(getBlocked.serverLogic(getBlockedLogic))

  val followUserRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(followUser.serverLogic(followUserLogic))

  val unfollowUserRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(unfollowUser.serverLogic(unfollowUserLogic))

  val blockUserRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(blockUser.serverLogic(blockUserLogic))
    
  val unblockUserRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(unblockUser.serverLogic(unblockUserLogic))

}
