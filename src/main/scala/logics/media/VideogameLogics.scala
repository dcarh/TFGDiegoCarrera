package logics.media

import cats.effect.IO
import clients.IGDBClient
import endpoints.apis.igdb.Videogames
import memory.repositories.{EntryRepository, MediaListRepository}
import domain.app.media.*
import domain.app.social.{Entry, MediaList}
import domain.errors.UserError.*
import domain.ids.Media.VideogameId
import domain.ids.Social.{EntryId, MediaListId}
import domain.apis.igdb.VideogameRequests.VideogameFromIGDB
import logics.functions.MediaAuxFunctions.getMetricsForMedia

object VideogameLogics {

  val getVideogame: VideogameId => IO[Either[UserError, Videogame]] =
    videogameId =>
      IGDBClient.executeRequest(Videogames.requestVideogameAllFields, videogameId).flatMap {
        case Right(requestedListOfVideogames: List[VideogameFromIGDB]) =>
          requestedListOfVideogames match
            case head :: tail =>
              val metrics = getMetricsForMedia(videogameId)
              
              IO.pure(Right(
                Videogame(
                  videogameFromIGDB = head,
                  averageRating      = metrics.averageRating,
                  entriesIds         = metrics.entriesIds,
                  mediaListsIds      = metrics.mediaListsIds,
                  completedCount  = metrics.statusCounts.completed,
                  droppedCount    = metrics.statusCounts.dropped,
                  inProgressCount = metrics.statusCounts.inProgress,
                  onHoldCount     = metrics.statusCounts.onHold,
                  pendingCount    = metrics.statusCounts.pending,
                  totalRatings       = metrics.totalRatings
                )
              ))
            case Nil => IO.pure(Left(NotFound("Not found videogame with ID introduced")))

        case Left(error: UserError) => IO(Left(error))
          
      }.handleError {
        case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }

  val getEntriesForVideogame: VideogameId => IO[Either[UserError, List[Entry]]] =
    videogameId =>
      IO.pure {
        val entries = EntryRepository.getAll.filter(_.mediaId == videogameId)

        Right(entries)
      }.handleError {
        case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }

  val getMediaListsForVideogame: VideogameId => IO[Either[UserError, List[MediaList]]] =
    videogameId =>
      IO.pure {
        val mediaLists = MediaListRepository.getAll.filter(_.mediaIds.contains(videogameId))

        Right(mediaLists)

      }.handleError {
        case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }

}

