package logics.media

import cats.effect.IO
import clients.TMDBClient
import endpoints.apis.tmdb.TvSeasons
import memory.repositories.{EntryRepository, MediaListRepository}
import domain.app.media.*
import domain.app.social.{Entry, MediaList}
import domain.errors.UserError.*
import domain.ids.Media.{TvSeasonNumber, TvShowId}
import domain.ids.Social.{EntryId, MediaListId}
import logics.functions.MediaAuxFunctions.getMetricsForMedia

object TvSeasonLogics {
  
  val getTvSeason: ((TvShowId, TvSeasonNumber)) => IO[Either[UserError, TvSeason]] =
    (tvShowId, tvSeasonNumber) =>
      TMDBClient.executeRequest(TvSeasons.requestTvSeason, (tvShowId, tvSeasonNumber)).flatMap {
        case Left(error)              => IO.pure(Left(error))
        case Right(requestedTvSeason) =>
          val aggregateCreditsTmdb = TMDBClient.executeRequest(TvSeasons.requestedAggregateCreditsForTvSeason, (tvShowId, tvSeasonNumber))
            .map(_.toOption)
            .handleError {
              case ex: Exception =>
                println(s"Some error occurred while requesting aggregate credits for the TV season: ${ex.getMessage}")
                None
            }

          val metrics = getMetricsForMedia((tvShowId, tvSeasonNumber))

          for {
            aggregateCredits <- aggregateCreditsTmdb
          } yield Right(TvSeason(
            tvSeasonFromTMDB = requestedTvSeason,
            cast             = aggregateCredits.map(_.cast.sortBy(_.order)),
            crew             = aggregateCredits.map(_.crew),
            averageRating    = metrics.averageRating,
            entriesIds       = metrics.entriesIds,
            mediaListsIds    = metrics.mediaListsIds,
            completedCount   = metrics.statusCounts.completed,
            droppedCount     = metrics.statusCounts.dropped,
            inProgressCount  = metrics.statusCounts.inProgress,
            onHoldCount      = metrics.statusCounts.onHold,
            pendingCount     = metrics.statusCounts.pending,
            totalRatings     = metrics.totalRatings
          ))
      }.handleError {
        case ex: Exception =>
          Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }

  val getEntriesForTvSeason: ((TvShowId, TvSeasonNumber)) => IO[Either[UserError, List[Entry]]] =
    (tvShowId, tvSeasonNumber) =>
      IO.pure {
        val entries = EntryRepository.getAll.filter(_.mediaId == (tvShowId, tvSeasonNumber))

        Right(entries)
      }.handleError {
        case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }

  val getMediaListsForTvSeason: ((TvShowId, TvSeasonNumber)) => IO[Either[UserError, List[MediaList]]] =
    (tvShowId, tvSeasonNumber) =>
      IO.pure {
        val mediaLists = MediaListRepository.getAll.filter(_.mediaIds.contains((tvShowId, tvSeasonNumber)))

        Right(mediaLists)

      }.handleError {
        case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }

}

