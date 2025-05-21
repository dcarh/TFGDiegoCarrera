package modelClasses.app.media

import io.circe.generic.auto.*
import modelClasses.ids.Social.{EntryId, MediaListId}
import modelClasses.igdb.VideogameRequests.VideogameFromIGDB

case class Videogame(
                      videogameFromIGDB: VideogameFromIGDB,

                      averageRating  : Option[Double],
                      entriesIds     : Option[List[EntryId]],
                      mediaListsIds  : Option[List[MediaListId]],
                      completedCount : Long,
                      droppedCount   : Long,
                      inProgressCount: Long,
                      onHoldCount    : Long,
                      pendingCount   : Long,
                      totalRatings   : Long
                    )
