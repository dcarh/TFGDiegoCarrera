package dummies.objects

import dummies.ids.{LikeIdDummies, ReplyIdDummies, ReviewIdDummies, UserIdDummies}
import modelClasses.app.social.Review
import modelClasses.ids.Media.{BookId, EpisodeNumber, MovieId, SeasonNumber, TvShowId, VideogameId}

object ReviewDummies {
  val review1: Review = Review(
    ReviewIdDummies.reviewId1,
    UserIdDummies.userId5,
    TvShowId(136315),
    "Fantastic from start to finish.",
    List(),
    true,
    List(),
    false
  )

  val review2: Review = Review(
    ReviewIdDummies.reviewId2,
    UserIdDummies.userId3,
    VideogameId(144022),
    "Sifu is an amazing indie game for those who love combo. It is challenging and rewarding game with a fluid combo. " +
      "It feels great when played on PS5",
    List(),
    true,
    List(ReplyIdDummies.replyId2),
    false
  )

  val review3: Review = Review(
    ReviewIdDummies.reviewId3,
    UserIdDummies.userId4,
    VideogameId(19564),
    "Weird.",
    List(LikeIdDummies.likeId1),
    false,
    List(),
    false
  )

  val review4: Review = Review(
    ReviewIdDummies.reviewId4,
    UserIdDummies.userId1,
    MovieId(502033),
    "The ending of this film is so authentic and impactful. I could not ask for a better one.",
    List(),
    true,
    List(ReplyIdDummies.replyId4),
    false
  )

  val review5: Review = Review(
    ReviewIdDummies.reviewId5,
    UserIdDummies.userId2,
    BookId("UU-VAAAACAAJ"),
    "In less than 100 pages, this book will make you appreciate your non-fishing hobbies.",
    List(),
    true,
    List(),
    false
  )

  val review6: Review = Review(
    ReviewIdDummies.reviewId6,
    UserIdDummies.userId5,
    (TvShowId(136315), SeasonNumber(2), EpisodeNumber(7)),
    "Wow!",
    List(),
    false,
    List(),
    false
  )

}