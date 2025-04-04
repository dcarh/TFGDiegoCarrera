package modelClasses.app.media

import io.circe.generic.auto.*
import modelClasses.ids.Social.{MediaListId, ReviewId}
import modelClasses.tmdb.Common.{Member, Result}
import modelClasses.tmdb.TvShowRequests.RequestedTvShow

case class TvShow(
                   requestedTvShow: RequestedTvShow,
                   similarTvShows: Option[List[Result]],
                   recommendedTvShows: Option[List[Result]],
                   cast: Option[List[Member]],                 // Viene de los Aggregate Credits y habría que ordenarlos según el campo order.
                   crew: Option[List[Member]],                 // Viene de los Aggregate Credits también, aunque no se me ocurre la forma de ordenarlos (Pooularidad?)
//                   cast               : List[(String, String)], Temporal (23/12/2024)
//                   creator            : String, Temporal
//                   episodeRuntime     : Int, Temporal
//                   firstAirDate       : Option[String],
//                   genres             : List[(Int, String)], Temporal (23/12/2024)
//                   id                 : TvShowId,
//                   lastAirDate        : Option[String],
//                   numberOfEpisodes   : Long,
//                   numberOfSeasons    : Long,
//                   overview           : String,
//                   productionCompanies: List[(Int, String)], Temporal (23/12/2024)
//                   productionCountries: List[(String, String)], Temporal (23/12/2024)
//                   recommendations    : List[TvShowId], Temporal (23/12/2024)
//                   similar            : List[TvShowId], Temporal (23/12/2024)
//                   status             : String,
//                   title              : String,
//                   year               : String

//                   averageRating      : Double, Temporal (23/12/2024)
//                   lists              : List[MediaListId], Temporal (23/12/2024)
                   numberOfCompleted  : Long,                                         // Temporal (23/12/2024)
                   numberOfDropped    : Long,                                         // Temporal (23/12/2024)
                   numberOfInProgress : Long,                                 // Temporal (23/12/2024)
                   numberOfOnHold     : Long,                                 // Temporal (23/12/2024)
                   numberOfPending    : Long,                                         // Temporal (23/12/2024)
//                   ratings            : Long, Temporal (23/12/2024)
//                   reviews            : List[ReviewId], Temporal (23/12/2024)
//                   totalRuntime       : Int Temporal (23/12/2024)
                 )