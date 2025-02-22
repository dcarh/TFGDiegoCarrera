package modelClasses.app.social

import modelClasses.ids.Media.{MovieId, TvShowId, TvSeasonNumber, TvEpisodeNumber, VideogameId, BookId}
import modelClasses.ids.Social.RatingId
import modelClasses.ids.User.UserId

case class Rating(
                   id          : RatingId,
                   userId      : UserId,
                   mediaRatedId: MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId,
                   rating      : Int
                 )

// TODO: ¿Realmente hace falta esta clase cuando ya tenemos al atributo "rating" en Entry?