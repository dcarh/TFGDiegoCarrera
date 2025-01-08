package modelClasses.app.social

import io.circe.generic.auto.*

import modelClasses.ids.Media.{BookId, MovieId, TvShowId, VideogameId}
import modelClasses.ids.Social.{LikeId, MediaContentListId, ReplyId}
import modelClasses.ids.User.UserId

import java.time.LocalDateTime

case class MediaContentList(
                             id              : MediaContentListId,
                             userId          : UserId,
                             title           : String,
                             description     : String,
                             mediaContentsIds: List[MovieId | TvShowId | VideogameId | BookId],
                             visibility      : Visibility,
                             allowReplies    : Boolean,
                             ranked          : Boolean,
                             creationDate    : LocalDateTime,
                             updateDate      : LocalDateTime,
                             likes           : List[LikeId],
                             replies         : List[ReplyId]
                           )

// object MediaContentList {
//   type Id = Long
// }
