package server.routes.media

import cats.effect.IO
import cats.implicits.toSemigroupKOps
import endpoints.app.media.TvEpisodeEndpoints
import org.http4s.HttpRoutes
import server.logics.media.TvEpisodeLogics
import sttp.tapir.server.http4s.Http4sServerInterpreter

object TvEpisodeRoutes {

  private val getTvEpisode: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().
      toRoutes(TvEpisodeEndpoints.getTvEpisode.serverLogic(TvEpisodeLogics.getTvEpisode))

  private val getEntriesForTvEpisode: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().
      toRoutes(TvEpisodeEndpoints.getEntriesForTvEpisode.serverLogic(TvEpisodeLogics.getEntriesForTvEpisode))

  private val getMediaListsForTvEpisode: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().
      toRoutes(TvEpisodeEndpoints.getMediaListsForTvEpisode.serverLogic(TvEpisodeLogics.getMediaListsForTvEpisode))

  val tvEpisodeRoutes: HttpRoutes[IO] =
    getTvEpisode                <+>
      getEntriesForTvEpisode    <+>
      getMediaListsForTvEpisode

}
