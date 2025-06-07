package domain.app.media

import io.circe.generic.auto.*
import domain.ids.Social.{EntryId, MediaListId}
import domain.tmdb.Common.Member
import domain.tmdb.TvEpisodeRequests.TvEpisodeFromTMDB

case class TvEpisode(
                      tvEpisodeFromTMDB: TvEpisodeFromTMDB,
                      cast             : Option[List[Member]],
                      crew             : Option[List[Member]],

                      averageRating    : Option[Double],
                      entriesIds       : Option[List[EntryId]],
                      mediaListsIds    : Option[List[MediaListId]],
                      completedCount   : Long,
                      droppedCount     : Long,
                      pendingCount     : Long,
                      totalRatings     : Long
                  )