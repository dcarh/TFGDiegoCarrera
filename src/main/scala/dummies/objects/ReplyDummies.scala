package dummies.objects

import dummies.ids.{LikeIdDummies, MediaContentListIdDummies, ReplyIdDummies, ReviewIdDummies, UserIdDummies}
import modelClasses.app.social.Reply

object ReplyDummies {
  val reply1: Reply = Reply(
    ReplyIdDummies.replyId1,
    UserIdDummies.userId4,
    MediaContentListIdDummies.mediaContentListId2,
    "Not Dune on the list?",
    List(),
    List(ReplyIdDummies.replyId3)
  )
  
  val reply2: Reply = Reply(
    ReplyIdDummies.replyId2,
    UserIdDummies.userId2,
    ReviewIdDummies.reviewId5,
    "Well, at least it made fishing look less boring",
    List(),
    List()
  )
  
  val reply3: Reply = Reply(
    ReplyIdDummies.replyId3,
    UserIdDummies.userId1,
    ReplyIdDummies.replyId1,
    "I liked it but not that much :(",
    List(),
    List()
  )

  val reply4: Reply = Reply(
    ReplyIdDummies.replyId4,
    UserIdDummies.userId3,
    ReviewIdDummies.reviewId4,
    "Not to mention its first 20 minutes, they're so devastating",
    List(LikeIdDummies.likeId3),
    List()
  )
  
}