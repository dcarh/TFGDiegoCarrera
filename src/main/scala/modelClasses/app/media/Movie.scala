package modelClasses.app.media

import io.circe.generic.auto.*
import modelClasses.ids.Social.{MediaListId, ReviewId}
import modelClasses.tmdb.Common.{Credits, Member, Result}
import modelClasses.tmdb.MovieRequests.RequestedMovie

case class Movie(
                  requestedMovie: RequestedMovie,
                  similarMovies: Option[List[Result]],
                  recommendedMovies: Option[List[Result]],
                  cast: Option[List[Member]],
                  crew: Option[List[Member]],
//                  budget             : Long,
                  //                  cast               : List[(String, String)],
                  //                  director           : String,
                  //                  genres             : List[(Int, String)],
//                  id                 : MovieId,
//                  overview           : String,
                  //                  productionCompanies: List[(Int, String)],
                  //                  productionCountries: List[(String, String)],
                  //                  recommendations    : List[MovieId],
//                  release_date       : Option[String],
//                  revenue            : Long,
//                  runtime            : Option[Long],
                  //                  similar            : List[MovieId],
//                  status             : String,
//                  title              : String,
//                  year               : String,

//                  averageRating      : Double, 
//                  lists              : List[MediaListId],
                  numberOfCompleted  : Long,
                  numberOfDropped    : Long,
                  numberOfPending    : Long,
//                  ratings            : Long,
//                  reviews            : List[ReviewId]
                )
