package domain.app.media

import io.circe.generic.auto.*
import domain.ids.Social.{EntryId, MediaListId}
import domain.tmdb.Common.{Member, Result}
import domain.tmdb.TvShowRequests.TvShowFromTMDB

case class TvShow(
                   tvShowFromTMDB    : TvShowFromTMDB,
                   similarTvShows    : Option[List[Result]],
                   recommendedTvShows: Option[List[Result]],
                   cast              : Option[List[Member]], // Viene de los Aggregate Credits y habría que ordenarlos según el campo order.
                   crew              : Option[List[Member]], // Viene de los Aggregate Credits también, aunque no se me ocurre la forma de ordenarlos (Pooularidad?)

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