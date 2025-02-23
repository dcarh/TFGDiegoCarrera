package modelClasses.app.media

import io.circe.generic.auto.*
import modelClasses.googleBooks.BooksRequests.RequestedBook
import modelClasses.ids.Social.{MediaListId, ReviewId}

case class Book(
                  requestedBook: RequestedBook
//                 authors           : List[String],
//                 categories        : List[String],
//                 description       : String,
//                 id                : BookId,
//                 title             : String,
//
//                 averageRating     : Double,
//                 lists             : List[MediaListId],
//                 numberOfAbandoned : Long,
//                 numberOfCompleted : Long,
//                 numberOfInProgress: Option[Long],
//                 numberOfPaused    : Option[Long],
//                 numberOfPending   : Long,
//                 ratings           : Long,
//                 reviews           : List[ReviewId]
               )