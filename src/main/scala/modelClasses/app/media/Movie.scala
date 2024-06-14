package modelClasses.app.media

import io.circe.generic.auto.*
import modelClasses.app.social.{Like, MediaContentList, Review}

case class Movie(
                  budget             : Long,
                  cast               : List[(String, String)],
                  director           : String,
                  genres             : List[(Int, String)],
                  id                 : Movie.Id,
                  overview           : String,
                  productionCompanies: List[(Int, String)],
                  productionCountries: List[(String, String)],
                  recommendations    : List[Movie.Id],
                  revenue            : Long,
                  runtime            : Int,
                  similar            : List[Movie.Id],
                  status             : String,
                  title              : String,
                  year               : String,
                
                  averageRating      : Double,
                  likes              : List[Like.Id],
                  lists              : List[MediaContentList.Id],
                  numberOfAbandoned  : Long,
                  numberOfCompleted  : Long,
                  numberOfInProgress : Option[Long],
                  numberOfPaused     : Option[Long],
                  numberOfPending    : Long,
                  ratings            : Long,
                  reviews            : List[Review.Id]
                )

object Movie {
  type Id = Long
}
