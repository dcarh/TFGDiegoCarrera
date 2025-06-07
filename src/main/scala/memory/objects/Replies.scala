package memory.objects

import memory.ids.{LikeIds, MediaListIds, ReplyIds, ReviewIds, UserIds}
import domain.app.social.Reply

object Replies {
  val reply1: Reply = Reply(
    ReplyIds.replyId1,
    UserIds.userId4,
    MediaListIds.mediaListId2,
    "Not Dune on the list?",
    List(),
    List(ReplyIds.replyId3)
  )
  
  val reply2: Reply = Reply(
    ReplyIds.replyId2,
    UserIds.userId2,
    ReviewIds.reviewId5,
    "Well, at least it made fishing look less boring",
    List(),
    List()
  )
  
  val reply3: Reply = Reply(
    ReplyIds.replyId3,
    UserIds.userId1,
    ReplyIds.replyId1,
    "I liked it but not that much :(",
    List(),
    List()
  )

  val reply4: Reply = Reply(
    ReplyIds.replyId4,
    UserIds.userId3,
    ReviewIds.reviewId4,
    "Not to mention its first 20 minutes, they're so devastating",
    List(LikeIds.likeId3),
    List()
  )
  
}