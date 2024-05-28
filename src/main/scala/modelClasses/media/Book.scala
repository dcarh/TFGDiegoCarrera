package modelClasses.media

import sttp.tapir.generic.auto._
import io.circe.generic.auto._

import modelClasses.social.{Like, MediaContentList, Review}

case class Book(
                 id                : Book.Id,
                 title             : String,
                 authors           : List[String],
                 description       : String,
                 categories        : List[String],

                 likes             : List[Like.Id],
                 reviews           : List[Review.Id],
                 averageRating     : Double,
                 ratings           : Long,
                 lists             : List[MediaContentList.Id],
                 numberOfCompleted : Long,
                 numberOfInProgress: Option[Long],
                 numberOfPaused    : Option[Long],
                 numberOfPending   : Long,
                 numberOfAbandoned : Long
               )

object Book {
  type Id = Long
}
