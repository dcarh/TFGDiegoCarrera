package server.routes.media

import cats.effect.IO
import cats.implicits.toSemigroupKOps
import endpoints.app.media.MediaEndpoints
import org.http4s.HttpRoutes
import server.logics.media.MediaLogics
import sttp.tapir.server.http4s.Http4sServerInterpreter

object MediaRoutes {

  private val getMovie: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().
      toRoutes(MediaEndpoints.getMovie.serverLogic(MediaLogics.getMovie))

  private val getTvShow: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().
      toRoutes(MediaEndpoints.getTvShow.serverLogic(MediaLogics.getTvShow))
    
  private val getTvSeason: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().
      toRoutes(MediaEndpoints.getTvSeason.serverLogic(MediaLogics.getTvSeason))

  private val getTvEpisode: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().
      toRoutes(MediaEndpoints.getTvEpisode.serverLogic(MediaLogics.getTvEpisode))

  private val getVideogame: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().
      toRoutes(MediaEndpoints.getVideogame.serverLogic(MediaLogics.getVideogame))
  
  private val getBook: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().
      toRoutes(MediaEndpoints.getBook.serverLogic(MediaLogics.getBook))

  val mediaRoutes: HttpRoutes[IO] =
    getMovie <+>
      getTvShow <+>
      getTvSeason <+>
      getTvEpisode <+>
      getVideogame <+>
      getBook

}
