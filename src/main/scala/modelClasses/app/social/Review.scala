package modelClasses.app.social

import io.circe.generic.auto.*

import modelClasses.ids.Media.{MovieId, TVShowId, VideogameId, BookId}
import modelClasses.ids.Social.{LikeId, ReviewId, ReplyId}
import modelClasses.ids.User.UserId

case class Review(
                   id            : ReviewId,
                   user          : UserId,
                   objectReviewed: MovieId | TVShowId | VideogameId | BookId,
                   review        : String,
                   likes         : List[LikeId],
                   replies       : List[ReplyId],
                   visibility    : Visibility
                 )

// object Review {
//   type Id = Long
// }
