package domain.app.social

import io.circe.generic.auto.*

import domain.ids.Social.{LikeId, MediaListId, ReviewId, ReplyId}
import domain.ids.User.UserId

case class Reply(
                  id             : ReplyId,
                  userId         : UserId,
                  repliedObjectId: MediaListId | ReviewId | ReplyId,
                  reply          : String,
                  likesIds       : List[LikeId],
                  repliesIds     : List[ReplyId]
                )