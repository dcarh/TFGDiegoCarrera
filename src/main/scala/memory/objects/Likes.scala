package memory.objects

import memory.ids.{LikeIds, MediaListIds, ReplyIds, ReviewIds, UserIds}
import domain.app.social.Like

object Likes {
  val like1: Like = Like(
    LikeIds.likeId1,
    UserIds.userId2,
    ReviewIds.reviewId3
  )

  val like2: Like = Like(
    LikeIds.likeId2,
    UserIds.userId5,
    MediaListIds.mediaListId4
  )

  val like3: Like = Like(
    LikeIds.likeId3,
    UserIds.userId1,
    ReplyIds.replyId4
  )

  val like4: Like = Like(
    LikeIds.likeId4,
    UserIds.userId4,
    MediaListIds.mediaListId1
  )

}