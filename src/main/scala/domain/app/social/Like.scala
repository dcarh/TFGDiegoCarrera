package domain.app.social

import domain.ids.Social.{LikeId, MediaListId, ReplyId, ReviewId}
import domain.ids.User.UserId

case class Like(
                 id            : LikeId,
                 userId        : UserId,
                 likedElementId: MediaListId | ReviewId | ReplyId
               )