package server.logics.media

import cats.effect.IO
import clients.GoogleBooksClient
import dummies.repositories.{EntryRepository, MediaListRepository}
import endpoints.googleBooks.Books
import modelClasses.app.media.*
import modelClasses.app.social.{Entry, MediaList}
import modelClasses.errors.UserError.*
import modelClasses.googleBooks.BooksRequests.RequestedBook
import modelClasses.ids.Media.BookId
import modelClasses.ids.Social.{EntryId, MediaListId}
import server.logics.media.MediaAuxFunctions.getMetricsForMedia

object BookLogics {

  val getBook: BookId => IO[Either[UserError, Book]] =
    bookId =>
      GoogleBooksClient.executeRequest(Books.requestBookEndpoint, bookId).flatMap {
        case Right(requestedBook: RequestedBook) =>
          val metrics = getMetricsForMedia(bookId)
          
          IO.pure(Right(
            Book(
              requestedBook      = requestedBook,
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

        case Left(error: UserError) => IO.pure(Left(error))
      }.handleError {
        case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }

  val getEntriesForBook: ((BookId, List[EntryId])) => IO[Either[UserError, List[Entry]]] =
    (_, entriesIds) =>
      val entries = EntryRepository.getMany(entriesIds)

      if (entries.nonEmpty)
        IO.pure(Right(entries))
      else
        IO.pure(Left(BadRequest("No entries found for such IDs")))

  val getMediaListsForBook: ((BookId, List[MediaListId])) => IO[Either[UserError, List[MediaList]]] =
    (_, mediaListsIds) =>
      val mediaLists = MediaListRepository.getMany(mediaListsIds)

      if (mediaLists.nonEmpty)
        IO.pure(Right(mediaLists))
      else
        IO.pure(Left(BadRequest("No mediaLists found for such IDs")))

}

