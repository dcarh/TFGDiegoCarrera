package modelClasses.app.social

import modelClasses.app.Time

import modelClasses.ids.Media.*
import modelClasses.ids.Social.{EntryId, RatingId, ReviewId}
import modelClasses.ids.User.UserId


case class Entry(
                id           : EntryId,
                userId       : UserId,
                elementId    : MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId,
                //elementId    : Movie.Id | TVShow.Id | Season.Id | Episode.Id | Videogame.Id | Book.Id,
                // elementTitle: String,
                // elementType : String,
                rating       : RatingId,
                review       : ReviewId,
                like         : Boolean,
                firstTime    : Boolean,
                completed    : Boolean,
                paused       : Boolean,
                abandoned    : Boolean,
                finishedDate : String,           // TODO: Cambiar a Date y resolver errores que se generan
                startedDate  : Option[String],   // TODO: Cambiar a Date y resolver errores que se generan
                platform     : Option[Int],
                timeSpent    : Time,
                tags         : List[String]
                )

// object Entry {
//   type Id = Long
// }