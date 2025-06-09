package routes.media

import cats.effect.IO
import cats.implicits.toSemigroupKOps
import endpoints.app.media.VideogameEndpoints
import logics.media.VideogameLogics
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter

object VideogameRoutes {

  private val getVideogame: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().
      toRoutes(VideogameEndpoints.getVideogame.serverLogic(VideogameLogics.getVideogame))

  private val getEntriesForVideogame: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().
      toRoutes(VideogameEndpoints.getEntriesForVideogame.serverLogic(VideogameLogics.getEntriesForVideogame))

  private val getMediaListsForVideogame: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().
      toRoutes(VideogameEndpoints.getMediaListsForVideogame.serverLogic(VideogameLogics.getMediaListsForVideogame))

  val videogameRoutes: HttpRoutes[IO] =
    getVideogame                <+>
      getEntriesForVideogame    <+>
      getMediaListsForVideogame

}
