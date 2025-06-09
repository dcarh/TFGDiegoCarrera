package routes.user.media

import cats.effect.IO
import cats.implicits.toSemigroupKOps
import endpoints.app.user.media.UserMediaEndpoints
import logics.user.media.UserMediaLogics
import org.http4s.HttpRoutes
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

  private val addTvSeason: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserMediaEndpoints.addTvSeason.serverLogic(UserMediaLogics.addTvSeason))

  private val addTvEpisode: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserMediaEndpoints.addTvEpisode.serverLogic(UserMediaLogics.addTvEpisode))

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

  private val deleteTvSeason: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserMediaEndpoints.deleteTvSeason.serverLogic(UserMediaLogics.deleteTvSeason))

  private val deleteTvEpisode: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]()
      .toRoutes(UserMediaEndpoints.deleteTvEpisode.serverLogic(UserMediaLogics.deleteTvEpisode))

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
      addTvSeason       <+>
      addTvEpisode      <+>
      addVideogame    <+>
      addBook         <+>
      deleteMovie     <+>
      deleteTvShow    <+>
      deleteTvSeason    <+>
      deleteTvEpisode   <+>
      deleteVideogame <+>
      deleteBook
}
