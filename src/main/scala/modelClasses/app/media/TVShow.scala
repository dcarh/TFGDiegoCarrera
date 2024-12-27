package modelClasses.app.media

import io.circe.generic.auto.*
import modelClasses.ids.Media.TVShowId
import modelClasses.ids.Social.{LikeId, MediaContentListId, ReviewId}
import modelClasses.app.social.{Like, MediaContentList, Review}

case class TVShow(
//                   cast               : List[(String, String)], Temporal (23/12/2024)
//                   creator            : String, Temporal
//                   episodeRuntime     : Int, Temporal
                   firstAirDate       : String,
//                   genres             : List[(Int, String)], Temporal (23/12/2024)
                   id                 : TVShowId,
                   lastAirDate        : String,
                   numberOfEpisodes   : Long,
                   numberOfSeasons    : Long,
                   overview           : String,
//                   productionCompanies: List[(Int, String)], Temporal (23/12/2024)
//                   productionCountries: List[(String, String)], Temporal (23/12/2024)
//                   recommendations    : List[TVShowId], Temporal (23/12/2024)
//                   similar            : List[TVShowId], Temporal (23/12/2024)
                   status             : String,
                   title              : String,
                   year               : String

//                   averageRating      : Double, Temporal (23/12/2024)
                   // episodesIds        : List[Episode.Id],
                   // episodesNumbers    : List[Episode.Number],
                   // seasonsIds         : List[Season.Id],
                   // seasonsNumbers     : List[Season.Number],
//                   likes              : List[LikeId], Temporal (23/12/2024)
//                   lists              : List[MediaContentListId], Temporal (23/12/2024)
//                   numberOfAbandoned  : Long, Temporal (23/12/2024)
//                   numberOfCompleted  : Long, Temporal (23/12/2024)
//                   numberOfInProgress : Option[Long], Temporal (23/12/2024)
//                   numberOfPaused     : Option[Long], Temporal (23/12/2024)
//                   numberOfPending    : Long, Temporal (23/12/2024)
//                   ratings            : Long, Temporal (23/12/2024)
//                   reviews            : List[ReviewId], Temporal (23/12/2024)
//                   totalRuntime       : Int Temporal (23/12/2024)
                 )

// object TVShow {
//   type Id = Long
// }
