package modelClasses.app.media

import io.circe.generic.auto.*
import modelClasses.ids.Social.{EntryId, MediaListId}
import modelClasses.tmdb.Common.Member
import modelClasses.tmdb.TvEpisodeRequests.RequestedTvEpisode

case class TvEpisode(
                      requestedTvEpisode: RequestedTvEpisode,
                      cast              : Option[List[Member]],
                      crew              : Option[List[Member]],

                      averageRating     : Option[Double],
                      entriesIds        : Option[List[EntryId]],
                      mediaListsIds     : Option[List[MediaListId]],
                      numberOfCompleted : Long,
                      numberOfDropped   : Long,
                      numberOfPending   : Long,
                      totalRatings      : Long
                  )