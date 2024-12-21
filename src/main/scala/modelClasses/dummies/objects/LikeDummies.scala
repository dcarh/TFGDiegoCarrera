package modelClasses.dummies.objects

import modelClasses.app.social.Like
import modelClasses.dummies.ids.{LikeIdDummies, MediaContentListIdDummies, ReplyIdDummies, ReviewIdDummies, UserIdDummies}

object LikeDummies {
  val like1: Like = Like(
    LikeIdDummies.likeId1,
    UserIdDummies.userId2,
    ReviewIdDummies.reviewId3
  )

  val like2: Like = Like(
    LikeIdDummies.likeId2,
    UserIdDummies.userId5,
    MediaContentListIdDummies.mediaContentListId4
  )

  val like3: Like = Like(
    LikeIdDummies.likeId3,
    UserIdDummies.userId1,
    ReplyIdDummies.replyId4
  )

  val like4: Like = Like(
    LikeIdDummies.likeId4,
    UserIdDummies.userId4,
    MediaContentListIdDummies.mediaContentListId1
  )

}