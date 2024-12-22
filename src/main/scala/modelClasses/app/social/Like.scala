package modelClasses.app.social

import modelClasses.ids.Social.{LikeId, MediaContentListId, ReplyId, ReviewId}
import modelClasses.ids.User.UserId

case class Like(
               id            : LikeId,
               userId        : UserId,
               elementLikedId: MediaContentListId | ReviewId | ReplyId
               )