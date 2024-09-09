package modelClasses.app.media

import io.circe.generic.auto.*

import modelClasses.ids.Media.BookId
import modelClasses.ids.Social.{LikeId, MediaContentListId, ReviewId}

case class Book(
                 authors           : List[String],
                 categories        : List[String],
                 description       : String,
                 id                : BookId,
                 title             : String,

                 averageRating     : Double,
                 likes             : List[LikeId],
                 lists             : List[MediaContentListId],
                 numberOfAbandoned : Long,
                 numberOfCompleted : Long,
                 numberOfInProgress: Option[Long],
                 numberOfPaused    : Option[Long],
                 numberOfPending   : Long,
                 ratings           : Long,
                 reviews           : List[ReviewId]
               )

// object Book {
//   type Id = Long
// }
