package modelClasses.app.social

import modelClasses.app.Time
import modelClasses.ids.Media.*
import modelClasses.ids.Social.{EntryId, RatingId, ReviewId}
import modelClasses.ids.User.UserId

import java.time.{LocalDate, LocalDateTime}


case class Entry(
                  id           : EntryId,
                  userId       : UserId,
                  mediaId      : MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId,
                  //elementId    : Movie.Id | TVShow.Id | Season.Id | Episode.Id | Videogame.Id | Book.Id,
                  // elementTitle: String,
                  // elementType : String,
                  rating       : Option[RatingId],
                  review       : Option[ReviewId],
                  like         : Boolean,
                  completed    : Boolean,
                  paused       : Option[Boolean],
                  abandoned    : Boolean,
                  repeat       : Boolean,
                  finishedDate : Option[LocalDate], // TODO: Cambiar a Option[Date] y resolver errores que se generan
                  startedDate  : Option[LocalDate], // TODO: Cambiar a Option[Date] y resolver errores que se generan
                  platform     : Option[Int],       // TODO: En vez de un entero que se corresponda con el entero de IGDB, plantear crear un enum de consolas y parsear el entero entrante de IGDB a un valor del enum (así va a ser más legible
                  timeSpent    : Option[Time],
                  tags         : List[String],
                  creationDate : LocalDateTime
                )

// object Entry {
//   type Id = Long
// }