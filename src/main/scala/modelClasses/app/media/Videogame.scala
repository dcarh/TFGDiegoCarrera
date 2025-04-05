package modelClasses.app.media

import io.circe.generic.auto.*
import modelClasses.ids.Social.{EntryId, MediaListId}
import modelClasses.igdb.VideogameRequests.VideogameAllFields

case class Videogame(
                      requestedVideogame: VideogameAllFields,

                      averageRating     : Option[Double],
                      entriesIds        : Option[List[EntryId]],
                      listsIds          : Option[List[MediaListId]],
                      numberOfCompleted : Long,
                      numberOfDropped   : Long,
                      numberOfInProgress: Long,
                      numberOfOnHold    : Long,
                      numberOfPending   : Long,
                      totalRatings      : Long
                    )
