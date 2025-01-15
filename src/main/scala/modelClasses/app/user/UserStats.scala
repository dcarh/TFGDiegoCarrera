package modelClasses.app.user

import modelClasses.app.Time
import modelClasses.ids.User.UserId

case class UserStats(
                    // userId                        : UserId,
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
                    totalNumberOfElementsCompleted: Int

                    // TODO: Meter más campos
                    )