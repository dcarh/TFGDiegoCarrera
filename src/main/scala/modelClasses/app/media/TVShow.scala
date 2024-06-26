package modelClasses.app.media

import io.circe.generic.auto.*
import modelClasses.app.social.{Like, MediaContentList, Review}

case class TVShow(
                   cast               : List[(String, String)],
                   creator            : String,
                   episodeRuntime     : Int,
                   firstAirDate       : String,
                   genres             : List[(Int, String)],
                   id                 : TVShow.Id,
                   lastAirDate        : String,
                   numberOfEpisodes   : Int,
                   numberOfSeasons    : Int,
                   overview           : String,
                   productionCompanies: List[(Int, String)],
                   productionCountries: List[(String, String)],
                   recommendations    : List[TVShow.Id],
                   similar            : List[TVShow.Id],
                   status             : String,
                   title              : String,

                   averageRating      : Double,
                   episodesIds        : List[Episode.Id],
                   episodesNumbers    : List[Episode.Number],
                   seasonsIds         : List[Season.Id],
                   seasonsNumbers     : List[Season.Number],
                   likes              : List[Like.Id],
                   lists              : List[MediaContentList.Id],
                   numberOfAbandoned  : Long,
                   numberOfCompleted  : Long,
                   numberOfInProgress : Option[Long],
                   numberOfPaused     : Option[Long],
                   numberOfPending    : Long,
                   ratings            : Long,
                   reviews            : List[Review.Id],
                   totalRuntime       : Int
                 )

object TVShow {
  type Id = Long
}
