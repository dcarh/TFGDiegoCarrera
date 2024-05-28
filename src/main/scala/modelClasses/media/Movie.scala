package modelClasses.media

import sttp.tapir.generic.auto._
import io.circe.generic.auto._

import modelClasses.social.{Like, MediaContentList, Review}

case class Movie(
                  id                 : Movie.Id,
                  title              : String,
                  director           : String,
                  year               : String,
                  runtime            : Int,
                  overview           : String,
                  cast               : List[(String, String)],
                  genres             : List[(Int, String)],
                  productionCompanies: List[(Int, String)],
                  productionCountries: List[(String, String)],
                  budget             : Long,
                  revenue            : Long,
                  recommendations    : List[Movie.Id],
                  similar            : List[Movie.Id],
                  status             : String,
                  likes              : List[Like.Id],
                  reviews            : List[Review.Id],
                  averageRating      : Double,
                  ratings            : Long,
                  lists              : List[MediaContentList.Id],
                  numberOfCompleted  : Long,
                  numberOfInProgress : Option[Long],
                  numberOfPaused     : Option[Long],
                  numberOfPending    : Long,
                  numberOfAbandoned  : Long
                )

object Movie {
  type Id = Long
}


