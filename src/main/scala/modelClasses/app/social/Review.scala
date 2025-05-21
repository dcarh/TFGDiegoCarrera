package modelClasses.app.social

import io.circe.generic.auto.*

import modelClasses.ids.Media.{BookId, TvEpisodeNumber, MovieId, TvSeasonNumber, TvShowId, VideogameId}
import modelClasses.ids.Social.{LikeId, ReplyId, ReviewId}
import modelClasses.ids.User.UserId

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