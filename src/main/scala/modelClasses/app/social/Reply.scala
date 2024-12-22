package modelClasses.app.social

import io.circe.generic.auto.*

import modelClasses.ids.Media.{MovieId, TVShowId, VideogameId, BookId}
import modelClasses.ids.Social.{LikeId, MediaContentListId, ReviewId, ReplyId}
import modelClasses.ids.User.UserId

case class Reply(
                  id             : ReplyId,
                  userId         : UserId,
                  objectRepliedId: MediaContentListId | ReviewId | ReplyId,
                  reply          : String,
                  likes          : List[LikeId],
                  replies        : List[ReplyId]
                )

// object Reply {
//   type Id = Long
// }
