package modelClasses.app.social

import modelClasses.ids.Media.{MovieId, TvShowId, TvSeasonNumber, TvEpisodeNumber, VideogameId, BookId}
import modelClasses.ids.Social.RatingId
import modelClasses.ids.User.UserId

case class Rating(
                   id          : RatingId,
                   userId      : UserId,
                   ratedMediaId: MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId,
                   rating      : Int
                 )