package modelClasses

case class Entry(
                id          : Entry.Id,
                elementId   : Int,
                elementTitle: String,
                elementType : String,
                rating      : Int,
                textReview  : String,
                like        : Boolean,
                firstTime   : Boolean,
                completed   : Boolean,
                paused      : Boolean,
                abandoned   : Boolean,
                date        : String,           // TODO: Cambiar a Date y resolver errores que se generan
                startedDate : Option[String],   // TODO: Cambiar a Date y resolver errores que se generan
                platform    : String,
                timeSpent   : Time,
                tags        : List[String]
                )

object Entry {
  type Id = Long
}