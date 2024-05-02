package modelClasses.user

import modelClasses.Time

case class UserStats(
                    userId                        : User.Id,
                    avgElementsRating             : Double,
                    timeSpentInMovies             : Time,
                    timeSpentInTVShows            : Time,
                    timeSpentInVideogames         : Time,
                    timeSpentInBooks              : Time,
                    totalTimeSpent                : Time,
                    numberOfMoviesCompleted       : Int,
                    numberOfTVShowsCompleted      : Int,
                    numberOfVideogamesCompleted   : Int,
                    numberOfBooksCompleted        : Int,
                    totalNumberOfElementsCompleted: Int,

                    // TODO: Meter más campos
                    )

object UserStats {
  type Id = Long
}