package modelClasses.app.media

import io.circe.generic.auto.*
import modelClasses.googleBooks.BooksRequests.RequestedBook
import modelClasses.ids.Social.{MediaListId, ReviewId}

case class Book(
                  requestedBook: RequestedBook,
//                 authors           : List[String],
//                 categories        : List[String],
//                 description       : String,
//                 id                : BookId,
//                 title             : String,
//
//                 averageRating     : Double,
//                 lists             : List[MediaListId],
                 numberOfCompleted : Long,
                 numberOfDropped   : Long,
                 numberOfInProgress: Long,
                 numberOfOnHold    : Long,
                 numberOfPending   : Long,
//                 ratings           : Long,
//                 reviews           : List[ReviewId]
               )