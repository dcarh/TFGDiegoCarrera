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
        case Left(error) => IO.pure(Left(error))
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
            listsIds           = metrics.listsIds,
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

  val getEntriesForTvSeason: ((TvShowId, TvSeasonNumber, List[EntryId])) => IO[Either[UserError, List[Entry]]] =
    (_, _, entriesIds) =>
      val entries = EntryRepository.getMany(entriesIds)

      if (entries.nonEmpty)
        IO.pure(Right(entries))
      else
        IO.pure(Left(BadRequest("No entries found for such IDs")))

  val getMediaListsForTvSeason: ((TvShowId, TvSeasonNumber, List[MediaListId])) => IO[Either[UserError, List[MediaList]]] =
    (_, _, mediaListsIds) =>
      val mediaLists = MediaListRepository.getMany(mediaListsIds)

      if (mediaLists.nonEmpty)
        IO.pure(Right(mediaLists))
      else
        IO.pure(Left(BadRequest("No mediaLists found for such IDs")))

}

