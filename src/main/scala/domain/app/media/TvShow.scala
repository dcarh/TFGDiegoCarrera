package domain.app.media

import io.circe.generic.auto.*
import domain.ids.Social.{EntryId, MediaListId}
import domain.apis.tmdb.Common.{Member, Result}
import domain.apis.tmdb.TvShowRequests.TvShowFromTMDB

case class TvShow(
                   tvShowFromTMDB    : TvShowFromTMDB,
                   similarTvShows    : Option[List[Result]],
                   recommendedTvShows: Option[List[Result]],
                   cast              : Option[List[Member]],
                   crew              : Option[List[Member]],

                   averageRating     : Option[Double],
                   entriesIds        : Option[List[EntryId]],
                   mediaListsIds     : Option[List[MediaListId]],
                   completedCount    : Long,
                   droppedCount      : Long,
                   inProgressCount   : Long,
                   onHoldCount       : Long,
                   pendingCount      : Long,
                   totalRatings      : Long
                 )