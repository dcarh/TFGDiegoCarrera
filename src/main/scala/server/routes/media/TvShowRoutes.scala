package server.routes.media

import cats.effect.IO
import cats.implicits.toSemigroupKOps
import endpoints.app.media.TvShowEndpoints
import org.http4s.HttpRoutes
import server.logics.media.TvShowLogics
import sttp.tapir.server.http4s.Http4sServerInterpreter

object TvShowRoutes {

  private val getTvShow: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().
      toRoutes(TvShowEndpoints.getTvShow.serverLogic(TvShowLogics.getTvShow))

  private val getEntriesForTvShow: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().
      toRoutes(TvShowEndpoints.getEntriesForTvShow.serverLogic(TvShowLogics.getEntriesForTvShow))

  private val getMediaListsForTvShow: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().
      toRoutes(TvShowEndpoints.getMediaListsForTvShow.serverLogic(TvShowLogics.getMediaListsForTvShow))

  val tvShowRoutes: HttpRoutes[IO] =
    getTvShow <+>
      getEntriesForTvShow <+>
      getMediaListsForTvShow

}
