package modelClasses.app.social

import io.circe.generic.auto.*

import modelClasses.ids.Social.{LikeId, MediaListId, ReviewId, ReplyId}
import modelClasses.ids.User.UserId

case class Reply(
                  id             : ReplyId,
                  userId         : UserId,
                  repliedObjectId: MediaListId | ReviewId | ReplyId,
                  reply          : String,
                  likesIds       : List[LikeId],
                  repliesIds     : List[ReplyId]
                )