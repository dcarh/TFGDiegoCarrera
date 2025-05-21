package memory.objects

import memory.ids.{LikeIds, ReplyIds, ReviewIds, UserIds}
import modelClasses.app.social.Review
import modelClasses.ids.Media.{BookId, TvEpisodeNumber, MovieId, TvSeasonNumber, TvShowId, VideogameId}

object Reviews {
  val review1: Review = Review(
    ReviewIds.reviewId1,
    UserIds.userId5,
    TvShowId(136315),
    "Fantastic from start to finish.",
    List(),
    true,
    List(),
    false
  )

  val review2: Review = Review(
    ReviewIds.reviewId2,
    UserIds.userId3,
    VideogameId(144022),
    "Sifu is an amazing indie game for those who love combo. It is challenging and rewarding game with a fluid combo. " +
      "It feels great when played on PS5",
    List(),
    true,
    List(),
    false
  )

  val review3: Review = Review(
    ReviewIds.reviewId3,
    UserIds.userId4,
    VideogameId(19564),
    "Weird.",
    List(LikeIds.likeId1),
    false,
    List(),
    false
  )

  val review4: Review = Review(
    ReviewIds.reviewId4,
    UserIds.userId1,
    MovieId(502033),
    "The ending of this film is so authentic and impactful. I could not ask for a better one.",
    List(),
    true,
    List(ReplyIds.replyId4),
    false
  )

  val review5: Review = Review(
    ReviewIds.reviewId5,
    UserIds.userId2,
    BookId("UU-VAAAACAAJ"),
    "In less than 100 pages, this book will make you appreciate your non-fishing hobbies.",
    List(),
    true,
    List(ReplyIds.replyId2),
    false
  )

  val review6: Review = Review(
    ReviewIds.reviewId6,
    UserIds.userId5,
    (TvShowId(136315), TvSeasonNumber(2), TvEpisodeNumber(7)),
    "Wow!",
    List(),
    false,
    List(),
    false
  )

}