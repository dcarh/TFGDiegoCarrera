package modelClasses.social

import modelClasses.Time
import modelClasses.user.User
import modelClasses.media._

case class Entry(
                id           : Entry.Id,
                userId       : User.Id,
                elementId    : Movie.Id | TVShow.Id | Season.Id | Episode.Id | Videogame.Id| Book.Id,
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