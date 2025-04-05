package modelClasses.app.media

import io.circe.generic.auto.*
import modelClasses.googleBooks.BooksRequests.RequestedBook
import modelClasses.ids.Social.{EntryId, MediaListId}

case class Book(
                 requestedBook     : RequestedBook,

                 averageRating     : Option[Double],
                 entriesIds        : Option[List[EntryId]],
                 listsIds          : Option[List[MediaListId]],
                 numberOfCompleted : Long,
                 numberOfDropped   : Long,
                 numberOfInProgress: Long,
                 numberOfOnHold    : Long,
                 numberOfPending   : Long, 
                 totalRatings      : Long,
               )