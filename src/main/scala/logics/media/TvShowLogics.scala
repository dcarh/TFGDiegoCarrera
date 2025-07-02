package logics.media

import cats.effect.IO
import clients.TMDBClient
import endpoints.apis.tmdb.TvShows
import memory.repositories.{EntryRepository, MediaListRepository}
import domain.app.media.*
import domain.app.social.{Entry, MediaList}
import domain.errors.UserError.*
import domain.ids.Media.TvShowId
import domain.ids.Social.{EntryId, MediaListId}
import logics.functions.MediaAuxFunctions.getMetricsForMedia

object TvShowLogics {
  
  val getTvShow: TvShowId => IO[Either[UserError, TvShow]] =
    tvShowId =>
      TMDBClient.executeRequest(TvShows.requestTvShow, tvShowId).flatMap {
        case Left(error)           => IO.pure(Left(error))
        case Right(requestedTvShow) =>
          val similarTvShowsTmdb = TMDBClient.executeRequest(TvShows.requestedSimilarTvShows, tvShowId)
            .map(_.toOption.map(_.results))
            .handleError {
              case ex: Exception =>
                println(s"Some error occurred while requesting similar TV shows: ${ex.getMessage}")
                None
            }

          val recommendedTvShowsTmdb = TMDBClient.executeRequest(TvShows.requestedRecommendedTvShows, tvShowId)
            .map(_.toOption.map(_.results))
            .handleError {
              case ex: Exception =>
                println(s"Some error occurred while requesting recommended TV shows: ${ex.getMessage}")
                None
            }

          val aggregateCreditsTmdb = TMDBClient.executeRequest(TvShows.requestedAggregateCreditsForTvShow, tvShowId)
            .map(_.toOption)
            .handleError {
              case ex: Exception =>
                println(s"Some error occurred while requesting aggregate credits for the TV show: ${ex.getMessage}")
                None
            }

          val metrics = getMetricsForMedia(tvShowId)

          for {
            similarTvShows     <- similarTvShowsTmdb
            recommendedTvShows <- recommendedTvShowsTmdb
            aggregateCredits   <- aggregateCreditsTmdb
          } yield Right(TvShow(
            tvShowFromTMDB     = requestedTvShow,
            similarTvShows     = similarTvShows,
            recommendedTvShows = recommendedTvShows,
            cast               = aggregateCredits.map(_.cast.sortBy(_.order)),
            crew               = aggregateCredits.map(_.crew),
            averageRating      = metrics.averageRating,
            entriesIds         = metrics.entriesIds,
            mediaListsIds      = metrics.mediaListsIds,
            completedCount     = metrics.statusCounts.completed,
            droppedCount       = metrics.statusCounts.dropped,
            inProgressCount    = metrics.statusCounts.inProgress,
            onHoldCount        = metrics.statusCounts.onHold,
            pendingCount       = metrics.statusCounts.pending,
            totalRatings       = metrics.totalRatings
          ))
      }.handleError {
        case ex: Exception =>
          Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }

  val getEntriesForTvShow: TvShowId => IO[Either[UserError, List[Entry]]] =
    tvShowId =>
      IO.pure {
        val entries = EntryRepository.getAll.filter(_.mediaId == tvShowId)

        Right(entries)
      }.handleError {
        case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }

  val getMediaListsForTvShow: TvShowId => IO[Either[UserError, List[MediaList]]] =
    tvShowId =>
      IO.pure {
        val mediaLists = MediaListRepository.getAll.filter(_.mediaIds.contains(tvShowId))

        Right(mediaLists)

      }.handleError {
        case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }

}

