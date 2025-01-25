package server.routes.user.media

import cats.effect.IO
import cats.implicits.toSemigroupKOps
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.user.media.UserInProgressMediaEndpoints
import server.logics.user.media.UserInProgressMediaLogics

object UserInProgressMediaRoutes {

  private val getAllInProgressMedia: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserInProgressMediaEndpoints.getAllInProgressMedia.serverLogic(UserInProgressMediaLogics.getAllInProgressMedia))

  private val addInProgressTvShow: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserInProgressMediaEndpoints.addInProgressTvShow.serverLogic(UserInProgressMediaLogics.addInProgressTvShow))

  private val addInProgressSeason: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserInProgressMediaEndpoints.addInProgressSeason.serverLogic(UserInProgressMediaLogics.addInProgressSeason))

  private val addInProgressVideogame: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserInProgressMediaEndpoints.addInProgressVideogame.serverLogic(UserInProgressMediaLogics.addInProgressVideogame))

  private val addInProgressBook: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserInProgressMediaEndpoints.addInProgressBook.serverLogic(UserInProgressMediaLogics.addInProgressBook))

  private val deleteInProgressTvShow: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserInProgressMediaEndpoints.deleteInProgressTvShow.serverLogic(UserInProgressMediaLogics.deleteInProgressTvShow))

  private val deleteInProgressSeason: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserInProgressMediaEndpoints.deleteInProgressSeason.serverLogic(UserInProgressMediaLogics.deleteInProgressSeason))

  private val deleteInProgressVideogame: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserInProgressMediaEndpoints.deleteInProgressVideogame.serverLogic(UserInProgressMediaLogics.deleteInProgressVideogame))

  private val deleteInProgressBook: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserInProgressMediaEndpoints.deleteInProgressBook.serverLogic(UserInProgressMediaLogics.deleteInProgressBook))

  val userInProgressMediaRoutes: HttpRoutes[IO] =
    getAllInProgressMedia <+>
      addInProgressTvShow <+>
      addInProgressSeason <+>
      addInProgressVideogame <+>
      addInProgressBook <+>
      deleteInProgressTvShow <+>
      deleteInProgressSeason <+>
      deleteInProgressVideogame <+>
      deleteInProgressBook
}
