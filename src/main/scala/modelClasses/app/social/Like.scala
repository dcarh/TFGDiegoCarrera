package modelClasses.app.social

import modelClasses.ids.Social.{LikeId, MediaListId, ReplyId, ReviewId}
import modelClasses.ids.User.UserId

case class Like(
               id            : LikeId,
               userId        : UserId,
               elementLikedId: MediaListId | ReviewId | ReplyId
               )