package logics.media

import cats.effect.IO
import cats.implicits.*
import clients.TMDBClient
import endpoints.apis.tmdb.TvEpisodes
import memory.repositories.{EntryRepository, MediaListRepository}
import domain.app.media.*
import domain.app.social.{Entry, MediaList}
import domain.errors.UserError.*
import domain.ids.Media.{TvEpisodeNumber, TvSeasonNumber, TvShowId}
import domain.ids.Social.{EntryId, MediaListId}
import logics.functions.MediaAuxFunctions.getMetricsForMedia

object TvEpisodeLogics {

  val getTvEpisode: ((TvShowId, TvSeasonNumber, TvEpisodeNumber)) => IO[Either[UserError, TvEpisode]] =
    (tvShowId, tvSeasonNumber, tvEpisodeNumber) =>
      TMDBClient.executeRequest(TvEpisodes.requestTvEpisode, (tvShowId, tvSeasonNumber, tvEpisodeNumber)).flatMap {
        case Left(error)               => IO.pure(Left(error))
        case Right(requestedTvEpisode) =>
          val creditsTmdb = TMDBClient.executeRequest(TvEpisodes.requestedCreditsForTvEpisode, (tvShowId, tvSeasonNumber, tvEpisodeNumber))
            .map(_.toOption)
            .handleError {
              case ex: Exception =>
                println(s"Some error occurred while requesting aggregate credits for the TV episode: ${ex.getMessage}")
                None
            }

          val metrics = getMetricsForMedia((tvShowId, tvSeasonNumber, tvEpisodeNumber))

          for {
            credits <- creditsTmdb
          } yield Right(TvEpisode(
            tvEpisodeFromTMDB = requestedTvEpisode,
            cast              = credits.map(_.cast.sortBy(_.order)),
            crew              = credits.map(_.crew),
            averageRating     = metrics.averageRating,
            entriesIds        = metrics.entriesIds,
            mediaListsIds     = metrics.mediaListsIds,
            completedCount    = metrics.statusCounts.completed,
            droppedCount      = metrics.statusCounts.dropped,
            pendingCount      = metrics.statusCounts.pending,
            totalRatings      = metrics.totalRatings
          ))
      }.handleError {
        case ex: Exception =>
          Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }

  val getEntriesForTvEpisode: ((TvShowId, TvSeasonNumber, TvEpisodeNumber)) => IO[Either[UserError, List[Entry]]] =
    (tvShowId, tvSeasonNumber, tvEpisodeNumber) =>
      IO.pure {
        val entries = EntryRepository.getAll.filter(_.mediaId == (tvShowId, tvSeasonNumber, tvEpisodeNumber))

        Right(entries)
      }.handleError {
        case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }

  val getMediaListsForTvEpisode: ((TvShowId, TvSeasonNumber, TvEpisodeNumber)) => IO[Either[UserError, List[MediaList]]] =
    (tvShowId, tvSeasonNumber, tvEpisodeNumber) =>
      IO.pure {
        val mediaLists = MediaListRepository.getAll.filter(_.mediaIds.contains((tvShowId, tvSeasonNumber, tvEpisodeNumber)))

        Right(mediaLists)

      }.handleError {
        case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }

}

