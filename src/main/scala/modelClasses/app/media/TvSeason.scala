package modelClasses.app.media

import io.circe.generic.auto.*
import modelClasses.ids.Social.{EntryId, MediaListId}
import modelClasses.tmdb.Common.Member
import modelClasses.tmdb.TvSeasonRequests.TvSeasonFromTMDB

case class TvSeason(
                     tvSeasonFromTMDB: TvSeasonFromTMDB,
                     cast            : Option[List[Member]],
                     crew            : Option[List[Member]],

                     averageRating   : Option[Double],
                     entriesIds      : Option[List[EntryId]],
                     mediaListsIds   : Option[List[MediaListId]],
                     completedCount  : Long,
                     droppedCount    : Long,
                     inProgressCount : Long,
                     onHoldCount     : Long,
                     pendingCount    : Long,
                     totalRatings    : Long
                 )