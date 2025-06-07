package memory.objects

import memory.ids.{RatingIds, UserIds}
import domain.app.social.Rating
import domain.ids.Media.{BookId, MovieId, TvSeasonNumber, TvShowId, VideogameId}

object Ratings {
  val rating1: Rating = Rating(
    RatingIds.ratingId1,
    UserIds.userId5,
    TvShowId(136315),
    10
  )
  val rating2: Rating = Rating(
    RatingIds.ratingId2,
    UserIds.userId3,
    VideogameId(144022),
    8
  )
  val rating3: Rating = Rating(
    RatingIds.ratingId3,
    UserIds.userId4,
    VideogameId(19564),
    5
  )
  val rating4: Rating = Rating(
    RatingIds.ratingId4,
    UserIds.userId1,
    MovieId(502033),
    10
  )
  val rating5: Rating = Rating(
    RatingIds.ratingId5,
    UserIds.userId2,
    BookId("UU-VAAAACAAJ"),
    7
  )
  val rating6: Rating = Rating(
    RatingIds.ratingId6,
    UserIds.userId2,
    VideogameId(1082),
    3
  )
  val rating7: Rating = Rating(
    RatingIds.ratingId7,
    UserIds.userId3,
    MovieId(837),
    6
  )
  val rating8: Rating = Rating(
    RatingIds.ratingId8,
    UserIds.userId5,
    TvShowId(100565),
    9
  )
  val rating9: Rating = Rating(
    RatingIds.ratingId9,
    UserIds.userId2,
    VideogameId(284925),
    6
  )
  val rating10: Rating = Rating(
    RatingIds.ratingId10,
    UserIds.userId1,
    BookId("ZEfWEAAAQBAJ"),
    4
  )
  
  val rating11: Rating = Rating(
    RatingIds.ratingId11,
    UserIds.userId4,
    MovieId(55347),
    9
  )
  
  val rating12: Rating = Rating(
    RatingIds.ratingId12,
    UserIds.userId1,
    (TvShowId(63247), TvSeasonNumber(1)),
    8
  )

  val rating13: Rating = Rating(
    RatingIds.ratingId13,
    UserIds.userId5,
    VideogameId(19564),
    8
  )

  val rating14: Rating = Rating(
    RatingIds.ratingId14,
    UserIds.userId2,
    BookId("WLmqonsyH0QC"),
    7
  )
}