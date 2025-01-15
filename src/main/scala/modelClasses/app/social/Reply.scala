package modelClasses.app.social

import io.circe.generic.auto.*

import modelClasses.ids.Social.{LikeId, MediaListId, ReviewId, ReplyId}
import modelClasses.ids.User.UserId

case class Reply(
                  id             : ReplyId,
                  userId         : UserId,
                  objectRepliedId: MediaListId | ReviewId | ReplyId,
                  reply          : String,
                  likes          : List[LikeId],
                  replies        : List[ReplyId]
                )