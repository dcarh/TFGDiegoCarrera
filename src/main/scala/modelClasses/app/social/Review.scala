package modelClasses.app.social

import io.circe.generic.auto.*

import modelClasses.ids.Media.{BookId, TvEpisodeNumber, MovieId, TvSeasonNumber, TvShowId, VideogameId}
import modelClasses.ids.Social.{LikeId, ReplyId, ReviewId}
import modelClasses.ids.User.UserId

case class Review(
                   id             : ReviewId,
                   userId         : UserId,
                   mediaReviewedId: MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId,
                   review         : String,
                   likes          : List[LikeId],
                   allowReplies   : Boolean,
                   replies        : List[ReplyId],
                   spoilers       : Boolean
                 )