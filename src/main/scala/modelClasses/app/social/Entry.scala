package modelClasses.app.social

import modelClasses.ids.Media.*
import modelClasses.ids.Social.{EntryId, RatingId, ReviewId}
import modelClasses.ids.User.UserId

import java.time.{LocalDate, LocalDateTime}


case class Entry(
                  id          : EntryId,
                  userId      : UserId,
                  mediaId     : MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId,
                  rating      : Option[RatingId],
                  review      : Option[ReviewId],
                  completed   : Boolean,
                  inProgress  : Option[Boolean],
                  onHold      : Option[Boolean],
                  dropped     : Boolean,
                  repeat      : Boolean,
                  startedDate : Option[LocalDate],
                  finishedDate: Option[LocalDate],
                  platform    : Option[Int], // TODO: En vez de un entero que se corresponda con el entero de IGDB, string de la plataforma (habría que hacer petición a IGDB)
                  creationDate: LocalDateTime,
                )