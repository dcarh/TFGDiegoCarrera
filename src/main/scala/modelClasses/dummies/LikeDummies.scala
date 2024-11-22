package modelClasses.dummies

import modelClasses.app.media.Episode
import modelClasses.app.social.Like
import modelClasses.ids.Media.{BookId, EpisodeNumber, MovieId, SeasonNumber, TVShowId, VideogameId}
import modelClasses.ids.Social.{LikeId, MediaContentListId, ReplyId, ReviewId}
import modelClasses.ids.User.UserId
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
    TVShowId(136315)
  )

  val like3: Like = Like(
    LikeIdDummies.likeId3,
    UserIdDummies.userId5,
    MediaContentListIdDummies.mediaContentListId4
  )

  val like4: Like = Like(
    LikeIdDummies.likeId4,
    UserIdDummies.userId3,
    VideogameId(144022)
  )

  val like5: Like = Like(
    LikeIdDummies.likeId5,
    UserIdDummies.userId1,
    ReplyIdDummies.replyId4
  )

  val like6: Like = Like(
    LikeIdDummies.likeId6,
    UserIdDummies.userId2,
    BookId("UU-VAAAACAAJ")
  )

  val like7: Like = Like(
    LikeIdDummies.likeId7,
    UserIdDummies.userId4,
    MediaContentListIdDummies.mediaContentListId1
  )
  
  val like8: Like = Like(
    LikeIdDummies.likeId8,
    UserIdDummies.userId5,
    (TVShowId(136315), SeasonNumber(2), EpisodeNumber(7))
  )
  
  val like9: Like = Like(
    LikeIdDummies.likeId9,
    UserIdDummies.userId1,
    (TVShowId(63247), SeasonNumber(1))
  )

}