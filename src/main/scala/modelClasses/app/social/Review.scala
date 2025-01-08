package modelClasses.app.social

import io.circe.generic.auto.*

import modelClasses.ids.Media.{BookId, EpisodeNumber, MovieId, SeasonNumber, TvShowId, VideogameId}
import modelClasses.ids.Social.{LikeId, ReplyId, ReviewId}
import modelClasses.ids.User.UserId

case class Review(
                   id             : ReviewId,
                   userId         : UserId,
                   mediaReviewedId: MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId,
                   review         : String,
                   likes          : List[LikeId],
                   allowReplies   : Boolean,
                   replies        : List[ReplyId],
                   spoilers       : Boolean
                 )

// object Review {
//   type Id = Long
// }
