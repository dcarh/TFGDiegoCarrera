package dummies.objects

import modelClasses.app.user.*
import dummies.ids.*
import modelClasses.ids.Media.{BookId, TvEpisodeNumber, MovieId, TvSeasonNumber, TvShowId, VideogameId}

object UserDummies {
  val user1: User = User(
    UserIdDummies.userId1,
    UserProfile(
      "User1",
      "jk78sdfy8dsfhg",
      "user1@gmail.com",
      "Hey there! I'm user 1",
      "Milwaukee, Wisconsin, USA"
    ),
    UserFavourites(
      Some(MovieId(502033)),
      Some(TvShowId(61222)),
      None,
      Some(BookId("7qylv1KYf0kC"))
    ),
    List(
      MovieId(502033),
      BookId("ZEfWEAAAQBAJ"),
      (TvShowId(63247), TvSeasonNumber(1)),
    ),
    List(),
    List(),
    List(
      BookId("WLmqonsyH0QC"),
    ),
    List(),
    List(
      MediaListIdDummies.mediaListId2,
      MediaListIdDummies.mediaListId3
    ),
    List(
      EntryIdDummies.entryId4,
      EntryIdDummies.entryId10,
      EntryIdDummies.entryId12,
      EntryIdDummies.entryId15
    ),
    List(
      ReviewIdDummies.reviewId4
    ),
    List(
      RatingIdDummies.ratingId4, 
      RatingIdDummies.ratingId10,
      RatingIdDummies.ratingId12
    ),
    List(
      LikeIdDummies.likeId2
    ),
    List(
      ReplyIdDummies.replyId3
    ),
    List(
      UserIdDummies.userId4
    ),
    List(
      UserIdDummies.userId4
    ),
    List(),
    List(
      ChatIdDummies.chatId3
    ),
    List()
  )

  val user2: User = User(
    UserIdDummies.userId2,
    UserProfile(
      "User2",
      "kljhfsdg87ygfjsbhn",
      "user2@gmail.com",
      "Hey there! I'm user 2",
      "London, UK"
    ),
    UserFavourites(
      Some(MovieId(502033)),
      Some(TvShowId(135918)),
      Some(VideogameId(113112)),
      Some(BookId("kHh_EAAAQBAJ"))
    ),
    List(
      BookId("UU-VAAAACAAJ"),
      VideogameId(284925),
      BookId("WLmqonsyH0QC"),
    ),
    List(),
    List(),
    List(),
    List(
      VideogameId(1082),
    ),
    List(
      MediaListIdDummies.mediaListId4
    ),
    List(
      EntryIdDummies.entryId5,
      EntryIdDummies.entryId6,
      EntryIdDummies.entryId9,
      EntryIdDummies.entryId18
      
    ),
    List(
      ReviewIdDummies.reviewId5
    ),
    List(
      RatingIdDummies.ratingId5,
      RatingIdDummies.ratingId6,
      RatingIdDummies.ratingId9,
      RatingIdDummies.ratingId14
    ),
    List(
      LikeIdDummies.likeId1
    ),
    List(
      ReplyIdDummies.replyId2
    ),
    List(
      UserIdDummies.userId4
    ),
    List(
      UserIdDummies.userId4, 
      UserIdDummies.userId5
    ),
    List(),
    List(
      ChatIdDummies.chatId1
    ),
    List(
      ChatIdDummies.chatId6
    )
  )

  val user3: User = User(
    UserIdDummies.userId3,
    UserProfile(
      "User3",
      "89nkj43nhj_9j22az",
      "user3@gmail.com",
      "Hey there! I'm user 3",
      "Milwaukee, Wisconsin, USA"
    ),
    UserFavourites(
      Some(MovieId(94329)),
      Some(TvShowId(77169)),
      Some(VideogameId(144022)),
      None
    ),
    List(
      VideogameId(144022),
      MovieId(837),
      VideogameId(19564),
    ),
    List(),
    List(),
    List(),
    List(),
    List(
      MediaListIdDummies.mediaListId1
    ),
    List(
      EntryIdDummies.entryId2,
      EntryIdDummies.entryId7
    ),
    List(
      ReviewIdDummies.reviewId2
    ),
    List(
      RatingIdDummies.ratingId2,
      RatingIdDummies.ratingId7
    ),
    List(),
    List(
      ReplyIdDummies.replyId4
    ),
    List(
      UserIdDummies.userId5
    ),
    List(),
    List(),
    List(),
    List()
  )

  val user4: User = User(
    UserIdDummies.userId4,
    UserProfile(
      "User4",
      "78hb7njmdsoil0",
      "user4@gmail.com",
      "Hey there! I'm user 4",
      "London, UK"
    ),
    UserFavourites(
      Some(MovieId(55347)),
      None,
      Some(VideogameId(103298)),
      None
    ),
    List(
      MovieId(55347),
    ),
    List(),
    List(),
    List(
      VideogameId(113112),
    ),
    List(
      VideogameId(19564),
    ),
    List(),
    List(
      EntryIdDummies.entryId3,
      EntryIdDummies.entryId11,
      EntryIdDummies.entryId13
    ),
    List(
      ReviewIdDummies.reviewId3
    ),
    List(
      RatingIdDummies.ratingId3,
      RatingIdDummies.ratingId11
    ),
    List(
      LikeIdDummies.likeId4
    ),
    List(
      ReplyIdDummies.replyId1
    ),
    List(
      UserIdDummies.userId1,
      UserIdDummies.userId2
    ),
    List(
      UserIdDummies.userId1,
      UserIdDummies.userId2
    ),
    List(
      UserIdDummies.userId3
    ),
    List(
      ChatIdDummies.chatId4,
      ChatIdDummies.chatId5
    ),
    List()
  )

  val user5: User = User(
    UserIdDummies.userId5,
    UserProfile(
      "User5",
      "129mbnhjiksgad8",
      "user5@gmail.com",
      "Hey there! I'm user 5",
      "Portland, Oregon, USA"
    ),
    UserFavourites(
      None,
      Some(TvShowId(88803)),
      None,
      None
    ),
    List(
      TvShowId(136315),
      TvShowId(100565),
      (TvShowId(136315), TvSeasonNumber(2), TvEpisodeNumber(7)),
      VideogameId(19564),
    ),
    List(),
    List(),
    List(),
    List(),
    List(),
    List(
      EntryIdDummies.entryId1,
      EntryIdDummies.entryId8,
      EntryIdDummies.entryId14,
      EntryIdDummies.entryId16,
    ),
    List(
      ReviewIdDummies.reviewId1,
      ReviewIdDummies.reviewId6
    ),
    List(
      RatingIdDummies.ratingId1,
      RatingIdDummies.ratingId8,
      RatingIdDummies.ratingId13
    ),
    List(
      LikeIdDummies.likeId3
    ),
    List(),
    List(UserIdDummies.userId2),
    List(UserIdDummies.userId3),
    List(),
    List(
      ChatIdDummies.chatId2
    ),
    List()
  )

}