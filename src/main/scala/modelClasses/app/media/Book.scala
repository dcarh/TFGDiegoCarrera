package modelClasses.app.media

import io.circe.generic.auto.*
import modelClasses.googleBooks.BooksRequests.BookFromGoogleBooks
import modelClasses.ids.Social.{EntryId, MediaListId}

case class Book(
                 bookFromGoogleBooks: BookFromGoogleBooks,

                 averageRating      : Option[Double],
                 entriesIds         : Option[List[EntryId]],
                 mediaListsIds      : Option[List[MediaListId]],
                 completedCount     : Long,
                 droppedCount       : Long,
                 inProgressCount    : Long,
                 onHoldCount        : Long,
                 pendingCount       : Long,
                 totalRatings       : Long,
               )