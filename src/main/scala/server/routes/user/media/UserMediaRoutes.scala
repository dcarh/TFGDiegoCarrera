package server.routes.user.media

import cats.effect.IO
import cats.implicits.toSemigroupKOps
import endpoints.app.user.media.UserMediaEndpoints
import org.http4s.HttpRoutes
import server.logics.user.media.UserMediaLogics
import sttp.tapir.server.http4s.Http4sServerInterpreter

object UserMediaRoutes {

  private val getAllMedia: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserMediaEndpoints.getAllMedia.serverLogic(UserMediaLogics.getAllMedia))

  private val addMovie: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserMediaEndpoints.addMovie.serverLogic(UserMediaLogics.addMovie))

  private val addTvShow: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserMediaEndpoints.addTvShow.serverLogic(UserMediaLogics.addTvShow))

  private val addSeason: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserMediaEndpoints.addSeason.serverLogic(UserMediaLogics.addSeason))

  private val addEpisode: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserMediaEndpoints.addEpisode.serverLogic(UserMediaLogics.addEpisode))

  private val addVideogame: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserMediaEndpoints.addVideogame.serverLogic(UserMediaLogics.addVideogame))

  private val addBook: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserMediaEndpoints.addBook.serverLogic(UserMediaLogics.addBook))

  private val deleteMovie: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserMediaEndpoints.deleteMovie.serverLogic(UserMediaLogics.deleteMovie))

  private val deleteTvShow: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserMediaEndpoints.deleteTvShow.serverLogic(UserMediaLogics.deleteTvShow))

  private val deleteSeason: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserMediaEndpoints.deleteSeason.serverLogic(UserMediaLogics.deleteSeason))

  private val deleteEpisode: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserMediaEndpoints.deleteEpisode.serverLogic(UserMediaLogics.deleteEpisode))

  private val deleteVideogame: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserMediaEndpoints.deleteVideogame.serverLogic(UserMediaLogics.deleteVideogame))

  private val deleteBook: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserMediaEndpoints.deleteBook.serverLogic(UserMediaLogics.deleteBook))
    
  val userMediaRoutes: HttpRoutes[IO] =
    getAllMedia       <+>
      addMovie        <+>
      addTvShow       <+>
      addSeason       <+>
      addEpisode      <+>
      addVideogame    <+>
      addBook         <+>
      deleteMovie     <+>
      deleteTvShow    <+>
      deleteSeason    <+>
      deleteEpisode   <+>
      deleteVideogame <+>
      deleteBook
}
