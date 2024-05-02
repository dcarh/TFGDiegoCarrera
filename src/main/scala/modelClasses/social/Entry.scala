package modelClasses.social

import modelClasses.Time

case class Entry(
                id           : Entry.Id,
                elementId    : Int,
                // elementTitle: String,
                // elementType : String,
                rating       : Int,
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