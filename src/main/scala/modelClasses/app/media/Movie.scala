package modelClasses.app.media

import io.circe.generic.auto.*
import modelClasses.ids.Social.{EntryId, MediaListId}
import modelClasses.tmdb.Common.{Member, Result}
import modelClasses.tmdb.MovieRequests.RequestedMovie

case class Movie(
                  requestedMovie: RequestedMovie,
                  similarMovies: Option[List[Result]],
                  recommendedMovies: Option[List[Result]],
                  cast: Option[List[Member]],
                  crew: Option[List[Member]],

                  averageRating    : Option[Double],
                  entriesIds       : Option[List[EntryId]],
                  listsIds         : Option[List[MediaListId]],
                  numberOfCompleted: Long,
                  numberOfDropped  : Long,
                  numberOfPending  : Long,
                  totalRatings     : Long,
                )
