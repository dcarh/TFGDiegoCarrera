package dummies.objects

import dummies.ids.{RatingIdDummies, UserIdDummies}
import modelClasses.app.social.Rating
import modelClasses.ids.Media.{BookId, MovieId, SeasonNumber, TVShowId, VideogameId}

object RatingDummies {
  val rating1: Rating = Rating(
    RatingIdDummies.ratingId1,
    UserIdDummies.userId5,
    TVShowId(136315),
    10
  )
  val rating2: Rating = Rating(
    RatingIdDummies.ratingId2,
    UserIdDummies.userId3,
    VideogameId(144022),
    8
  )
  val rating3: Rating = Rating(
    RatingIdDummies.ratingId3,
    UserIdDummies.userId4,
    VideogameId(19564),
    5
  )
  val rating4: Rating = Rating(
    RatingIdDummies.ratingId4,
    UserIdDummies.userId1,
    MovieId(502033),
    10
  )
  val rating5: Rating = Rating(
    RatingIdDummies.ratingId5,
    UserIdDummies.userId2,
    BookId("UU-VAAAACAAJ"),
    7
  )
  val rating6: Rating = Rating(
    RatingIdDummies.ratingId6,
    UserIdDummies.userId2,
    VideogameId(1082),
    3
  )
  val rating7: Rating = Rating(
    RatingIdDummies.ratingId7,
    UserIdDummies.userId3,
    MovieId(837),
    6
  )
  val rating8: Rating = Rating(
    RatingIdDummies.ratingId8,
    UserIdDummies.userId5,
    TVShowId(100565),
    9
  )
  val rating9: Rating = Rating(
    RatingIdDummies.ratingId9,
    UserIdDummies.userId2,
    VideogameId(284925),
    6
  )
  val rating10: Rating = Rating(
    RatingIdDummies.ratingId10,
    UserIdDummies.userId1,
    BookId("ZEfWEAAAQBAJ"),
    4
  )
  
  val rating11: Rating = Rating(
    RatingIdDummies.ratingId11,
    UserIdDummies.userId4,
    MovieId(55347),
    9
  )
  
  val rating12: Rating = Rating(
    RatingIdDummies.ratingId12,
    UserIdDummies.userId1,
    (TVShowId(63247), SeasonNumber(1)),
    8
  )
}