package server.logics.media

import cats.effect.IO
import clients.TMDBClient
import dummies.repositories.{EntryRepository, MediaListRepository}
import endpoints.tmdb.TvSeasons
import modelClasses.app.media.*
import modelClasses.app.social.{Entry, MediaList}
import modelClasses.errors.UserError.*
import modelClasses.ids.Media.{TvSeasonNumber, TvShowId}
import modelClasses.ids.Social.{EntryId, MediaListId}
import server.logics.media.MediaAuxFunctions.getMetricsForMedia

object TvSeasonLogics {
  
  val getTvSeason: ((TvShowId, TvSeasonNumber)) => IO[Either[UserError, TvSeason]] =
    (tvShowId, tvSeasonNumber) =>
      TMDBClient.executeRequest(TvSeasons.requestTvSeasonEndpoint, (tvShowId, tvSeasonNumber)).flatMap {
        case Left(error)              => IO.pure(Left(error))
        case Right(requestedTvSeason) =>
          val aggregateCreditsTmdb = TMDBClient.executeRequest(TvSeasons.requestedAggregateCreditsForTvSeasonEndpoint, (tvShowId, tvSeasonNumber))
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
            requestedTvSeason  = requestedTvSeason,
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

  val getEntriesForTvSeason: ((TvShowId, TvSeasonNumber)) => IO[Either[UserError, List[Entry]]] =
    (tvShowId, tvSeasonNumber) =>
      val entries = EntryRepository.getAll.filter(_.mediaId == (tvShowId, tvSeasonNumber))

      if (entries.nonEmpty)
        IO.pure(Right(entries))
      else
        IO.pure(Left(BadRequest("No entries found for season " + tvSeasonNumber.value + " from TV show with ID: " + tvShowId.value)))

  val getMediaListsForTvSeason: ((TvShowId, TvSeasonNumber)) => IO[Either[UserError, List[MediaList]]] =
    (tvShowId, tvSeasonNumber) =>
      val mediaLists = MediaListRepository.getAll.filter(_.mediaIds.contains((tvShowId, tvSeasonNumber)))  // TODO: ¿Decidir si quiero que haya seasons en MediaLists?

      if (mediaLists.nonEmpty)
        IO.pure(Right(mediaLists))
      else
        IO.pure(Left(BadRequest("No mediaLists found for season " + tvSeasonNumber.value + " from TV show with ID: " + tvShowId.value)))

}

