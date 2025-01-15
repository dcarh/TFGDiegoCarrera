package modelClasses.app.media

import io.circe.generic.auto.*
import modelClasses.ids.Media.MovieId
import modelClasses.ids.Social.{LikeId, MediaListId, ReviewId}
import modelClasses.app.social.{Like, MediaList, Review}

case class Movie(
                  budget             : Long,
//                  cast               : List[(String, String)],
//                  director           : String,
//                  genres             : List[(Int, String)],
                  id                 : MovieId,
                  overview           : String,
//                  productionCompanies: List[(Int, String)],
//                  productionCountries: List[(String, String)],
//                  recommendations    : List[MovieId],
                  release_date       : String,
                  revenue            : Long,
                  runtime            : Long,
//                  similar            : List[MovieId],
                  status             : String,
                  title              : String,
                  year               : String,
                
//                  averageRating      : Double, 
//                  lists              : List[MediaListId],
//                  numberOfAbandoned  : Long,
//                  numberOfCompleted  : Long,
//                  numberOfInProgress : Option[Long],
//                  numberOfPaused     : Option[Long],
//                  numberOfPending    : Long,
//                  ratings            : Long,
//                  reviews            : List[ReviewId]
                )
