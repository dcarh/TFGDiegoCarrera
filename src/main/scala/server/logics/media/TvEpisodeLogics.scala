package server.logics.media

import cats.effect.IO
import cats.implicits.*
import clients.TMDBClient
import dummies.repositories.{EntryRepository, MediaListRepository}
import endpoints.tmdb.TvEpisodes
import modelClasses.app.media.*
import modelClasses.app.social.{Entry, MediaList}
import modelClasses.errors.UserError.*
import modelClasses.ids.Media.{TvEpisodeNumber, TvSeasonNumber, TvShowId}
import modelClasses.ids.Social.{EntryId, MediaListId}
import server.logics.media.MediaAuxFunctions.getMetricsForMedia

object TvEpisodeLogics {

  val getTvEpisode: ((TvShowId, TvSeasonNumber, TvEpisodeNumber)) => IO[Either[UserError, TvEpisode]] =
    (tvShowId, tvSeasonNumber, tvEpisodeNumber) =>
      TMDBClient.executeRequest(TvEpisodes.requestTvEpisodeEndpoint, (tvShowId, tvSeasonNumber, tvEpisodeNumber)).flatMap {
        case Left(error) => IO.pure(Left(error))
        case Right(requestedTvEpisode) =>
          val creditsTmdb = TMDBClient.executeRequest(TvEpisodes.requestedCreditsForTvEpisodeEndpoint, (tvShowId, tvSeasonNumber, tvEpisodeNumber))
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
            requestedTvEpisode = requestedTvEpisode,
            cast               = credits.map(_.cast.sortBy(_.order)),
            crew               = credits.map(_.crew),
            averageRating      = metrics.averageRating,
            entriesIds         = metrics.entriesIds,
            listsIds           = metrics.listsIds,
            numberOfCompleted  = metrics.statusCounts.completed,
            numberOfDropped    = metrics.statusCounts.dropped,
            numberOfPending    = metrics.statusCounts.pending,
            totalRatings       = metrics.totalRatings
          ))
      }.handleError {
        case ex: Exception =>
          Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }

  val getEntriesForTvEpisode: ((TvShowId, TvSeasonNumber, TvEpisodeNumber, List[EntryId])) => IO[Either[UserError, List[Entry]]] =
    (_, _, _, entriesIds) =>
      val entries = EntryRepository.getMany(entriesIds)

      if (entries.nonEmpty)
        IO.pure(Right(entries))
      else
        IO.pure(Left(BadRequest("No entries found for such IDs")))

  val getMediaListsForTvEpisode: ((TvShowId, TvSeasonNumber, TvEpisodeNumber, List[MediaListId])) => IO[Either[UserError, List[MediaList]]] =
    (_, _, _, mediaListsIds) =>
      val mediaLists = MediaListRepository.getMany(mediaListsIds)

      if (mediaLists.nonEmpty)
        IO.pure(Right(mediaLists))
      else
        IO.pure(Left(BadRequest("No mediaLists found for such IDs")))

}

