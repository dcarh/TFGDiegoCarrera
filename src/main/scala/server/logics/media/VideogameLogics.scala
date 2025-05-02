package server.logics.media

import cats.effect.IO
import clients.IGDBClient
import dummies.repositories.{EntryRepository, MediaListRepository}
import endpoints.igdb.Videogames
import modelClasses.app.media.*
import modelClasses.app.social.{Entry, MediaList}
import modelClasses.errors.UserError.*
import modelClasses.ids.Media.VideogameId
import modelClasses.ids.Social.{EntryId, MediaListId}
import modelClasses.igdb.VideogameRequests.VideogameAllFields
import server.logics.media.MediaAuxFunctions.getMetricsForMedia

object VideogameLogics {

  val getVideogame: VideogameId => IO[Either[UserError, Videogame]] =
    videogameId =>
      IGDBClient.executeRequest(Videogames.requestVideogameAllFieldsEndpoint, videogameId).flatMap {
        case Right(requestedListOfVideogames: List[VideogameAllFields]) =>
          requestedListOfVideogames match
            case head :: tail =>
              val metrics = getMetricsForMedia(videogameId)
              
              IO.pure(Right(
                Videogame(
                  requestedVideogame = head,
                  averageRating      = metrics.averageRating,
                  entriesIds         = metrics.entriesIds,
                  mediaListsIds      = metrics.mediaListsIds,
                  numberOfCompleted  = metrics.statusCounts.completed,
                  numberOfDropped    = metrics.statusCounts.dropped,
                  numberOfInProgress = metrics.statusCounts.inProgress,
                  numberOfOnHold     = metrics.statusCounts.onHold,
                  numberOfPending    = metrics.statusCounts.pending,
                  totalRatings       = metrics.totalRatings
                )
              ))
            case Nil => IO.pure(Left(NotFound("Not found videogame with ID introduced")))

        case Left(error: UserError) => IO(Left(error))
          
      }.handleError {
        case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }

  val getEntriesForVideogame: ((VideogameId, List[EntryId])) => IO[Either[UserError, List[Entry]]] =
    (_, entriesIds) =>
      val entries = EntryRepository.getMany(entriesIds)

      if (entries.nonEmpty)
        IO.pure(Right(entries))
      else
        IO.pure(Left(BadRequest("No entries found for such IDs")))

  val getMediaListsForVideogame: ((VideogameId, List[MediaListId])) => IO[Either[UserError, List[MediaList]]] =
    (_, mediaListsIds) =>
      val mediaLists = MediaListRepository.getMany(mediaListsIds)

      if (mediaLists.nonEmpty)
        IO.pure(Right(mediaLists))
      else
        IO.pure(Left(BadRequest("No mediaLists found for such IDs")))

}

