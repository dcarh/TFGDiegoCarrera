package server.routes.user.media

import cats.effect.IO
import cats.implicits.toSemigroupKOps
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.user.media.UserDroppedMediaEndpoints
import server.logics.user.media.UserDroppedMediaLogics

object UserDroppedMediaRoutes {

  private val getAllDroppedMedia: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserDroppedMediaEndpoints.getAllDroppedMedia.serverLogic(UserDroppedMediaLogics.getAllDroppedMedia))

  private val addDroppedMovie: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserDroppedMediaEndpoints.addDroppedMovie.serverLogic(UserDroppedMediaLogics.addDroppedMovie))

  private val addDroppedTvShow: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserDroppedMediaEndpoints.addDroppedTvShow.serverLogic(UserDroppedMediaLogics.addDroppedTvShow))

  private val addDroppedSeason: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserDroppedMediaEndpoints.addDroppedSeason.serverLogic(UserDroppedMediaLogics.addDroppedSeason))

  private val addDroppedEpisode: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserDroppedMediaEndpoints.addDroppedEpisode.serverLogic(UserDroppedMediaLogics.addDroppedEpisode))

  private val addDroppedVideogame: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserDroppedMediaEndpoints.addDroppedVideogame.serverLogic(UserDroppedMediaLogics.addDroppedVideogame))

  private val addDroppedBook: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserDroppedMediaEndpoints.addDroppedBook.serverLogic(UserDroppedMediaLogics.addDroppedBook))

  private val deleteDroppedMovie: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserDroppedMediaEndpoints.deleteDroppedMovie.serverLogic(UserDroppedMediaLogics.deleteDroppedMovie))

  private val deleteDroppedTvShow: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserDroppedMediaEndpoints.deleteDroppedTvShow.serverLogic(UserDroppedMediaLogics.deleteDroppedTvShow))

  private val deleteDroppedSeason: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserDroppedMediaEndpoints.deleteDroppedSeason.serverLogic(UserDroppedMediaLogics.deleteDroppedSeason))

  private val deleteDroppedEpisode: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserDroppedMediaEndpoints.deleteDroppedEpisode.serverLogic(UserDroppedMediaLogics.deleteDroppedEpisode))

  private val deleteDroppedVideogame: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserDroppedMediaEndpoints.deleteDroppedVideogame.serverLogic(UserDroppedMediaLogics.deleteDroppedVideogame))

  private val deleteDroppedBook: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserDroppedMediaEndpoints.deleteDroppedBook.serverLogic(UserDroppedMediaLogics.deleteDroppedBook))

  val userDroppedMediaRoutes: HttpRoutes[IO] =
    getAllDroppedMedia <+>
      addDroppedMovie <+>
      addDroppedTvShow <+>
      addDroppedSeason <+>
      addDroppedEpisode <+>
      addDroppedVideogame <+>
      addDroppedBook <+>
      deleteDroppedMovie <+>
      deleteDroppedTvShow <+>
      deleteDroppedSeason <+>
      deleteDroppedEpisode <+>
      deleteDroppedVideogame <+>
      deleteDroppedBook
}
