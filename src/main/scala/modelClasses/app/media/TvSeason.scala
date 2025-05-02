package modelClasses.app.media

import io.circe.generic.auto.*
import modelClasses.ids.Social.{EntryId, MediaListId}
import modelClasses.tmdb.Common.Member
import modelClasses.tmdb.TvSeasonRequests.RequestedTvSeason

case class TvSeason(
                     requestedTvSeason : RequestedTvSeason,
                     cast              : Option[List[Member]],
                     crew              : Option[List[Member]],

                     averageRating     : Option[Double],
                     entriesIds        : Option[List[EntryId]],
                     mediaListsIds          : Option[List[MediaListId]],
                     numberOfCompleted : Long,
                     numberOfDropped   : Long,
                     numberOfInProgress: Long,
                     numberOfOnHold    : Long,
                     numberOfPending   : Long,
                     totalRatings      : Long
                 )