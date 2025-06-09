package logics.media

import cats.effect.IO
import clients.GoogleBooksClient
import endpoints.apis.googleBooks.Books
import memory.repositories.{EntryRepository, MediaListRepository}
import domain.app.media.*
import domain.app.social.{Entry, MediaList}
import domain.errors.UserError.*
import domain.apis.googleBooks.BooksRequests.BookFromGoogleBooks
import domain.ids.Media.BookId
import domain.ids.Social.{EntryId, MediaListId}
import logics.functions.MediaAuxFunctions.getMetricsForMedia

object BookLogics {

  val getBook: BookId => IO[Either[UserError, Book]] =
    bookId =>
      GoogleBooksClient.executeRequest(Books.requestBook, bookId).flatMap {
        case Right(requestedBook: BookFromGoogleBooks) =>
          val metrics = getMetricsForMedia(bookId)
          
          IO.pure(Right(
            Book(
              bookFromGoogleBooks      = requestedBook,
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

        case Left(error: UserError) => IO.pure(Left(error))
      }.handleError {
        case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }

  val getEntriesForBook: BookId => IO[Either[UserError, List[Entry]]] =
    bookId =>
      val entries = EntryRepository.getAll.filter(_.mediaId == bookId)

      if (entries.nonEmpty)
        IO.pure(Right(entries))
      else
        IO.pure(Left(BadRequest("No entries found for book with ID: " + bookId.value)))

  val getMediaListsForBook: BookId => IO[Either[UserError, List[MediaList]]] =
    bookId =>
      val mediaLists = MediaListRepository.getAll.filter(_.mediaIds.contains(bookId))

      if (mediaLists.nonEmpty)
        IO.pure(Right(mediaLists))
      else
        IO.pure(Left(BadRequest("No mediaLists found for book with ID: " + bookId.value)))

}

