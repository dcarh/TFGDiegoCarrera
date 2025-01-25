package server.routes.user.media

import cats.effect.IO
import cats.implicits.toSemigroupKOps
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.user.media.UserPendingMediaEndpoints
import server.logics.user.media.UserPendingMediaLogics

object UserPendingMediaRoutes {

  private val getAllPendingMedia: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserPendingMediaEndpoints.getAllPendingMedia.serverLogic(UserPendingMediaLogics.getAllPendingMedia))

  private val addPendingMovie: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserPendingMediaEndpoints.addPendingMovie.serverLogic(UserPendingMediaLogics.addPendingMovie))

  private val addPendingTvShow: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserPendingMediaEndpoints.addPendingTvShow.serverLogic(UserPendingMediaLogics.addPendingTvShow))

  private val addPendingSeason: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserPendingMediaEndpoints.addPendingSeason.serverLogic(UserPendingMediaLogics.addPendingSeason))

  private val addPendingEpisode: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserPendingMediaEndpoints.addPendingEpisode.serverLogic(UserPendingMediaLogics.addPendingEpisode))

  private val addPendingVideogame: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserPendingMediaEndpoints.addPendingVideogame.serverLogic(UserPendingMediaLogics.addPendingVideogame))

  private val addPendingBook: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserPendingMediaEndpoints.addPendingBook.serverLogic(UserPendingMediaLogics.addPendingBook))

  private val deletePendingMovie: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserPendingMediaEndpoints.deletePendingMovie.serverLogic(UserPendingMediaLogics.deletePendingMovie))

  private val deletePendingTvShow: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserPendingMediaEndpoints.deletePendingTvShow.serverLogic(UserPendingMediaLogics.deletePendingTvShow))

  private val deletePendingSeason: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserPendingMediaEndpoints.deletePendingSeason.serverLogic(UserPendingMediaLogics.deletePendingSeason))

  private val deletePendingEpisode: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserPendingMediaEndpoints.deletePendingEpisode.serverLogic(UserPendingMediaLogics.deletePendingEpisode))

  private val deletePendingVideogame: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserPendingMediaEndpoints.deletePendingVideogame.serverLogic(UserPendingMediaLogics.deletePendingVideogame))

  private val deletePendingBook: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserPendingMediaEndpoints.deletePendingBook.serverLogic(UserPendingMediaLogics.deletePendingBook))

  val userPendingMediaRoutes: HttpRoutes[IO] =
    getAllPendingMedia <+>
      addPendingMovie <+>
      addPendingTvShow <+>
      addPendingSeason <+>
      addPendingEpisode <+>
      addPendingVideogame <+>
      addPendingBook <+>
      deletePendingMovie <+>
      deletePendingTvShow <+>
      deletePendingSeason <+>
      deletePendingEpisode <+>
      deletePendingVideogame <+>
      deletePendingBook
}
