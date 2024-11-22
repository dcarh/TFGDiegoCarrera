package modelClasses.app.social

import modelClasses.ids.Media.{MovieId, TVShowId, VideogameId, BookId}
import modelClasses.ids.Social.{LikeId, MediaContentListId, ReviewId, ReplyId}
import modelClasses.ids.User.UserId

case class Reply(
                  id           : ReplyId,
                  user         : UserId,
                  objectReplied: MediaContentListId | ReviewId | ReplyId,
                  reply        : String,
                  likes        : List[LikeId],
                  replies      : List[ReplyId]
                )

// object Reply {
//   type Id = Long
// }
