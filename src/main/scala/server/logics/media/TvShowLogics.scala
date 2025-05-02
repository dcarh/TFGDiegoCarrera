package server.logics.media

import cats.effect.IO
import clients.TMDBClient
import dummies.repositories.{EntryRepository, MediaListRepository}
import endpoints.tmdb.TvShows
import modelClasses.app.media.*
import modelClasses.app.social.{Entry, MediaList}
import modelClasses.errors.UserError.*
import modelClasses.ids.Media.TvShowId
import modelClasses.ids.Social.{EntryId, MediaListId}
import server.logics.media.MediaAuxFunctions.getMetricsForMedia

object TvShowLogics {
  
  val getTvShow: TvShowId => IO[Either[UserError, TvShow]] =
    tvShowId =>
      TMDBClient.executeRequest(TvShows.requestTvShowEndpoint, tvShowId).flatMap {
        case Left(error)           => IO.pure(Left(error))
        case Right(requestedTvShow) =>
          val similarTvShowsTmdb = TMDBClient.executeRequest(TvShows.requestedSimilarTvShowsEndpoint, tvShowId)
            .map(_.toOption.map(_.results))
            .handleError {
              case ex: Exception =>
                println(s"Some error occurred while requesting similar TV shows: ${ex.getMessage}")
                None
            }

          val recommendedTvShowsTmdb = TMDBClient.executeRequest(TvShows.requestedRecommendedTvShowsEndpoint, tvShowId)
            .map(_.toOption.map(_.results))
            .handleError {
              case ex: Exception =>
                println(s"Some error occurred while requesting recommended TV shows: ${ex.getMessage}")
                None
            }

          val aggregateCreditsTmdb = TMDBClient.executeRequest(TvShows.requestedAggregateCreditsForTvShowEndpoint, tvShowId)
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
            requestedTvShow    = requestedTvShow,
            similarTvShows     = similarTvShows,
            recommendedTvShows = recommendedTvShows,
            cast               = aggregateCredits.map(_.cast.sortBy(_.order)),
            crew               = aggregateCredits.map(_.crew),
            averageRating      = metrics.averageRating,
            entriesIds         = metrics.entriesIds,
            mediaListsIds      = metrics.mediaListsIds,
            numberOfCompleted  = metrics.statusCounts.completed,
            numberOfDropped    = metrics.statusCounts.dropped,
            numberOfInProgress = metrics.statusCounts.inProgress,
            numberOfOnHold     = metrics.statusCounts.onHold,
            numberOfPending    = metrics.statusCounts.pending,
            totalRatings       = metrics.totalRatings
          ))
      }.handleError {
        case ex: Exception =>
          Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }

  val getEntriesForTvShow: ((TvShowId, List[EntryId])) => IO[Either[UserError, List[Entry]]] =
    (_, entriesIds) =>
      val entries = EntryRepository.getMany(entriesIds)

      if (entries.nonEmpty)
        IO.pure(Right(entries))
      else
        IO.pure(Left(BadRequest("No entries found for such IDs")))

  val getMediaListsForTvShow: ((TvShowId, List[MediaListId])) => IO[Either[UserError, List[MediaList]]] =
    (_, mediaListsIds) =>
      val mediaLists = MediaListRepository.getMany(mediaListsIds)

      if (mediaLists.nonEmpty)
        IO.pure(Right(mediaLists))
      else
        IO.pure(Left(BadRequest("No mediaLists found for such IDs")))

}

