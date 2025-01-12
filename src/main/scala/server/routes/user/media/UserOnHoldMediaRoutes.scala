package server.routes.user.media

import cats.effect.IO
import cats.implicits.toSemigroupKOps
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.user.media.UserOnHoldMediaEndpoints
import server.logics.user.media.UserOnHoldMediaLogics

object UserOnHoldMediaRoutes {

  private val getAllOnHoldMedia: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserOnHoldMediaEndpoints.getAllOnHoldMedia.serverLogic(UserOnHoldMediaLogics.getAllOnHoldMedia))

//  private val addOnHoldTvShow: HttpRoutes[IO] =
//    Http4sServerInterpreter[IO]()
//      .toRoutes(UserOnHoldMediaEndpoints.addOnHoldTvShow.serverLogic(UserOnHoldMediaLogics.addOnHoldTvShow))
//
//  private val addOnHoldSeason: HttpRoutes[IO] =
//    Http4sServerInterpreter[IO]()
//      .toRoutes(UserOnHoldMediaEndpoints.addOnHoldSeason.serverLogic(UserOnHoldMediaLogics.addOnHoldSeason))
//
//  private val addOnHoldVideogame: HttpRoutes[IO] =
//    Http4sServerInterpreter[IO]()
//      .toRoutes(UserOnHoldMediaEndpoints.addOnHoldVideogame.serverLogic(UserOnHoldMediaLogics.addOnHoldVideogame))
//
//  private val addOnHoldBook: HttpRoutes[IO] =
//    Http4sServerInterpreter[IO]()
//      .toRoutes(UserOnHoldMediaEndpoints.addOnHoldBook.serverLogic(UserOnHoldMediaLogics.addOnHoldBook))
//
//  private val deleteOnHoldTvShow: HttpRoutes[IO] =
//    Http4sServerInterpreter[IO]()
//      .toRoutes(UserOnHoldMediaEndpoints.deleteOnHoldTvShow.serverLogic(UserOnHoldMediaLogics.deleteOnHoldTvShow))
//
//  private val deleteOnHoldSeason: HttpRoutes[IO] =
//    Http4sServerInterpreter[IO]()
//      .toRoutes(UserOnHoldMediaEndpoints.deleteOnHoldSeason.serverLogic(UserOnHoldMediaLogics.deleteOnHoldSeason))
//
//  private val deleteOnHoldVideogame: HttpRoutes[IO] =
//    Http4sServerInterpreter[IO]()
//      .toRoutes(UserOnHoldMediaEndpoints.deleteOnHoldVideogame.serverLogic(UserOnHoldMediaLogics.deleteOnHoldVideogame))
//
//  private val deleteOnHoldBook: HttpRoutes[IO] =
//    Http4sServerInterpreter[IO]()
//      .toRoutes(UserOnHoldMediaEndpoints.deleteOnHoldBook.serverLogic(UserOnHoldMediaLogics.deleteOnHoldBook))

  val userOnHoldMediaRoutes: HttpRoutes[IO] =
    getAllOnHoldMedia // <+>
//      addOnHoldTvShow <+>
//      addOnHoldSeason <+>
//      addOnHoldVideogame <+>
//      addOnHoldBook <+>
//      deleteOnHoldTvShow <+>
//      deleteOnHoldSeason <+>
//      deleteOnHoldVideogame <+>
//      deleteOnHoldBook
}
