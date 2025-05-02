package modelClasses.app.media

import io.circe.generic.auto.*
import modelClasses.ids.Social.{EntryId, MediaListId}
import modelClasses.tmdb.Common.{Member, Result}
import modelClasses.tmdb.TvShowRequests.RequestedTvShow

case class TvShow(
                   requestedTvShow   : RequestedTvShow,
                   similarTvShows    : Option[List[Result]],
                   recommendedTvShows: Option[List[Result]],
                   cast              : Option[List[Member]], // Viene de los Aggregate Credits y habría que ordenarlos según el campo order.
                   crew              : Option[List[Member]], // Viene de los Aggregate Credits también, aunque no se me ocurre la forma de ordenarlos (Pooularidad?)

                   averageRating     : Option[Double],
                   entriesIds        : Option[List[EntryId]],
                   mediaListsIds     : Option[List[MediaListId]],
                   numberOfCompleted : Long,
                   numberOfDropped   : Long,
                   numberOfInProgress: Long,
                   numberOfOnHold    : Long,
                   numberOfPending   : Long,
                   totalRatings      : Long
                 )