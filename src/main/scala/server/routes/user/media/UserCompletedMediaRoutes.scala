package server.routes.user.media

import cats.effect.IO
import cats.implicits.toSemigroupKOps
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

import endpoints.app.user.media.UserCompletedMediaEndpoints
import server.logics.user.media.UserCompletedMediaLogics

object UserCompletedMediaRoutes {

  private val getAllCompletedMedia: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserCompletedMediaEndpoints.getAllCompletedMedia.serverLogic(UserCompletedMediaLogics.getAllCompletedMedia))

//  private val addCompletedMovie: HttpRoutes[IO] =
//    Http4sServerInterpreter[IO]()
//      .toRoutes(UserCompletedMediaEndpoints.addCompletedMovie.serverLogic(UserCompletedMediaLogics.addCompletedMovie))
//
//  private val addCompletedTvShow: HttpRoutes[IO] =
//    Http4sServerInterpreter[IO]()
//      .toRoutes(UserCompletedMediaEndpoints.addCompletedTvShow.serverLogic(UserCompletedMediaLogics.addCompletedTvShow))
//
//  private val addCompletedSeason: HttpRoutes[IO] =
//    Http4sServerInterpreter[IO]()
//      .toRoutes(UserCompletedMediaEndpoints.addCompletedSeason.serverLogic(UserCompletedMediaLogics.addCompletedSeason))
//
//  private val addCompletedEpisode: HttpRoutes[IO] =
//    Http4sServerInterpreter[IO]()
//      .toRoutes(UserCompletedMediaEndpoints.addCompletedEpisode.serverLogic(UserCompletedMediaLogics.addCompletedEpisode))
//
//  private val addCompletedVideogame: HttpRoutes[IO] =
//    Http4sServerInterpreter[IO]()
//      .toRoutes(UserCompletedMediaEndpoints.addCompletedVideogame.serverLogic(UserCompletedMediaLogics.addCompletedVideogame))
//
//  private val addCompletedBook: HttpRoutes[IO] =
//    Http4sServerInterpreter[IO]()
//      .toRoutes(UserCompletedMediaEndpoints.addCompletedBook.serverLogic(UserCompletedMediaLogics.addCompletedBook))
//
//  private val deleteCompletedMovie: HttpRoutes[IO] =
//    Http4sServerInterpreter[IO]()
//      .toRoutes(UserCompletedMediaEndpoints.deleteCompletedMovie.serverLogic(UserCompletedMediaLogics.deleteCompletedMovie))
//
//  private val deleteCompletedTvShow: HttpRoutes[IO] =
//    Http4sServerInterpreter[IO]()
//      .toRoutes(UserCompletedMediaEndpoints.deleteCompletedTvShow.serverLogic(UserCompletedMediaLogics.deleteCompletedTvShow))
//
//  private val deleteCompletedSeason: HttpRoutes[IO] =
//    Http4sServerInterpreter[IO]()
//      .toRoutes(UserCompletedMediaEndpoints.deleteCompletedSeason.serverLogic(UserCompletedMediaLogics.deleteCompletedSeason))
//
//  private val deleteCompletedEpisode: HttpRoutes[IO] =
//    Http4sServerInterpreter[IO]()
//      .toRoutes(UserCompletedMediaEndpoints.deleteCompletedEpisode.serverLogic(UserCompletedMediaLogics.deleteCompletedEpisode))
//
//  private val deleteCompletedVideogame: HttpRoutes[IO] =
//    Http4sServerInterpreter[IO]()
//      .toRoutes(UserCompletedMediaEndpoints.deleteCompletedVideogame.serverLogic(UserCompletedMediaLogics.deleteCompletedVideogame))
//
//  private val deleteCompletedBook: HttpRoutes[IO] =
//    Http4sServerInterpreter[IO]()
//      .toRoutes(UserCompletedMediaEndpoints.deleteCompletedBook.serverLogic(UserCompletedMediaLogics.deleteCompletedBook))
    
  val userCompletedMediaRoutes: HttpRoutes[IO] =
    getAllCompletedMedia // <+>
//      addCompletedMovie <+>
//      addCompletedTvShow <+>
//      addCompletedSeason <+>
//      addCompletedEpisode <+>
//      addCompletedVideogame <+>
//      addCompletedBook <+>
//      deleteCompletedMovie <+>
//      deleteCompletedTvShow <+>
//      deleteCompletedSeason <+>
//      deleteCompletedEpisode <+>
//      deleteCompletedVideogame <+>
//      deleteCompletedBook
}
