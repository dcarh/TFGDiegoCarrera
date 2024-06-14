package modelClasses.app.media

import io.circe.generic.auto.*
import modelClasses.app.social.{Like, MediaContentList, Review}

case class Book(
                 authors           : List[String],
                 categories        : List[String],
                 description       : String,
                 id                : Book.Id,
                 title             : String,

                 averageRating     : Double,
                 likes             : List[Like.Id],
                 lists             : List[MediaContentList.Id],
                 numberOfAbandoned : Long,
                 numberOfCompleted : Long,
                 numberOfInProgress: Option[Long],
                 numberOfPaused    : Option[Long],
                 numberOfPending   : Long,
                 ratings           : Long,
                 reviews           : List[Review.Id]
               )

object Book {
  type Id = Long
}
