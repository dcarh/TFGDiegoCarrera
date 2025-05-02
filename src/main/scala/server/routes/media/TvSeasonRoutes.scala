package server.routes.media

import cats.effect.IO
import cats.implicits.toSemigroupKOps
import endpoints.app.media.TvSeasonEndpoints
import org.http4s.HttpRoutes
import server.logics.media.TvSeasonLogics
import sttp.tapir.server.http4s.Http4sServerInterpreter

object TvSeasonRoutes {
    
  private val getTvSeason: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().
      toRoutes(TvSeasonEndpoints.getTvSeason.serverLogic(TvSeasonLogics.getTvSeason))

  private val getEntriesForTvSeason: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().
      toRoutes(TvSeasonEndpoints.getEntriesForTvSeason.serverLogic(TvSeasonLogics.getEntriesForTvSeason))

  private val getMediaListsForTvSeason: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().
      toRoutes(TvSeasonEndpoints.getMediaListsForTvSeason.serverLogic(TvSeasonLogics.getMediaListsForTvSeason))

  val tvSeasonRoutes: HttpRoutes[IO] =
    getTvSeason                <+>
      getEntriesForTvSeason    <+>
      getMediaListsForTvSeason

}
