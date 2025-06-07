package domain.app.social

import io.circe.generic.auto.*

import domain.ids.Media.{BookId, TvEpisodeNumber, MovieId, TvSeasonNumber, TvShowId, VideogameId}
import domain.ids.Social.{LikeId, ReplyId, ReviewId}
import domain.ids.User.UserId

case class Review(
                   id             : ReviewId,
                   userId         : UserId,
                   reviewedMediaId: MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId,
                   review         : String,
                   likesIds       : List[LikeId],
                   allowReplies   : Boolean,
                   repliesIds     : List[ReplyId],
                   spoilers       : Boolean
                 )