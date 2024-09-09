package modelClasses.app.media

import io.circe.generic.auto.*
import modelClasses.ids.Media.TVShowId
import modelClasses.ids.Social.{LikeId, MediaContentListId, ReviewId}
import modelClasses.app.social.{Like, MediaContentList, Review}

case class TVShow(
                   cast               : List[(String, String)],
                   creator            : String,
                   episodeRuntime     : Int,
                   firstAirDate       : String,
                   genres             : List[(Int, String)],
                   id                 : TVShowId,
                   lastAirDate        : String,
                   numberOfEpisodes   : Int,
                   numberOfSeasons    : Int,
                   overview           : String,
                   productionCompanies: List[(Int, String)],
                   productionCountries: List[(String, String)],
                   recommendations    : List[TVShowId],
                   similar            : List[TVShowId],
                   status             : String,
                   title              : String,

                   averageRating      : Double,
                   // episodesIds        : List[Episode.Id],
                   // episodesNumbers    : List[Episode.Number],
                   // seasonsIds         : List[Season.Id],
                   // seasonsNumbers     : List[Season.Number],
                   likes              : List[LikeId],
                   lists              : List[MediaContentListId],
                   numberOfAbandoned  : Long,
                   numberOfCompleted  : Long,
                   numberOfInProgress : Option[Long],
                   numberOfPaused     : Option[Long],
                   numberOfPending    : Long,
                   ratings            : Long,
                   reviews            : List[ReviewId],
                   totalRuntime       : Int
                 )

// object TVShow {
//   type Id = Long
// }
