package domain.app.social

import domain.ids.Media.{MovieId, TvShowId, TvSeasonNumber, TvEpisodeNumber, VideogameId, BookId}
import domain.ids.Social.RatingId
import domain.ids.User.UserId

case class Rating(
                   id          : RatingId,
                   userId      : UserId,
                   ratedMediaId: MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId,
                   rating      : Int
                 )