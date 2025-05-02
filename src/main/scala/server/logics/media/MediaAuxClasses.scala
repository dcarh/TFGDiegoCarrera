package server.logics.media

import modelClasses.ids.Social.{EntryId, MediaListId}

object MediaAuxClasses {

  case class StatusCounts(
                           completed : Long,
                           dropped   : Long,
                           inProgress: Long,
                           onHold    : Long,
                           pending   : Long
                         )

  case class MediaMetrics(
                           averageRating: Option[Double],
                           entriesIds   : Option[List[EntryId]],
                           mediaListsIds: Option[List[MediaListId]],
                           statusCounts : StatusCounts,
                           totalRatings : Long,
                         )

}
