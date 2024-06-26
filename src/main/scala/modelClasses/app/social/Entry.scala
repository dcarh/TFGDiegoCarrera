package modelClasses.app.social

import modelClasses.app.Time
import modelClasses.app.media.{Book, Episode, Movie, Season, TVShow, Videogame}
import modelClasses.app.user.User

case class Entry(
                id           : Entry.Id,
                userId       : User.Id,
                elementId    : Movie.Id | TVShow.Id | (TVShow.Id, Season.Number) | (TVShow.Id, Season.Number, Episode.Number) | Videogame.Id | Book.Id,
                //elementId    : Movie.Id | TVShow.Id | Season.Id | Episode.Id | Videogame.Id | Book.Id,
                // elementTitle: String,
                // elementType : String,
                rating       : Rating.Id,
                review       : Review.Id,
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

object Entry {
  type Id = Long
}