package modelClasses.dummies

import modelClasses.app.user.*
import modelClasses.dummies.ids.*
import modelClasses.ids.Chatting.ChatId
import modelClasses.ids.Media.{BookId, EpisodeNumber, MovieId, SeasonNumber, TVShowId, VideogameId}
import modelClasses.ids.Social.{EntryId, LikeId, MediaContentListId, RatingId, ReplyId, ReviewId}
import modelClasses.ids.User.UserId
import modelClasses.app.Time

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
      Some(TVShowId(61222)),
      None,
      Some(BookId("7qylv1KYf0kC"))
    ),
    List(
      MovieId(502033),
      BookId("ZEfWEAAAQBAJ"),
      (TVShowId(63247), SeasonNumber(1)),
    ),
    List(),
    List(),
    List(
      BookId("WLmqonsyH0QC"),
    ),
    List(),
    List(
      MediaContentListIdDummies.mediaContentListId2,
      MediaContentListIdDummies.mediaContentListId3
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
    List(),
    List(
      UserIdDummies.userId4
    ),
    List(
      UserIdDummies.userId4
    ),
    List(),
    List(
      ChatIdDummies.chatId2
    ),
    UserStats(
      7.33,
      Time(0, 2, 0),
      Time(0, 10, 0),
      Time(0, 0, 0),
      Time(0, 3, 15),
      Time(0, 15, 15),
      1,
      1,
      0,
      0,
      2
    ),
    UserSettings(
      false
    )
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
      Some(TVShowId(135918)),
      Some(VideogameId(113112)),
      Some(BookId("kHh_EAAAQBAJ"))
    ),
    List(
      BookId("UU-VAAAACAAJ"),
      VideogameId(284925),
    ),
    List(),
    List(),
    List(),
    List(
      VideogameId(1082),
    ),
    List(
      MediaContentListIdDummies.mediaContentListId4
    ),
    List(
      EntryIdDummies.entryId5,
      EntryIdDummies.entryId6,
      EntryIdDummies.entryId9
      
    ),
    List(
      ReviewIdDummies.reviewId5
    ),
    List(
      RatingIdDummies.ratingId5,
      RatingIdDummies.ratingId6,
      RatingIdDummies.ratingId9
    ),
    List(
      LikeIdDummies.likeId1
    ),
    List(
      ReplyIdDummies.replyId2
    ),
    List(
      "survival horror", 
      "RE"
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
      ChatIdDummies.chatId1,
      ChatIdDummies.chatId3
    ),
    UserStats(
      5.33,
      Time(0, 0, 0),
      Time(0, 0, 0),
      Time(0, 17, 0),
      Time(0, 4, 53),
      Time(0, 21, 53),
      0,
      0,
      1,
      1,
      2
    ),
    UserSettings(
      true
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
      Some(TVShowId(77169)),
      Some(VideogameId(144022)),
      None
    ),
    List(
      VideogameId(144022),
      MovieId(837),
    ),
    List(),
    List(),
    List(),
    List(),
    List(
      MediaContentListIdDummies.mediaContentListId1
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
    List(),
    List(
      UserIdDummies.userId5
    ),
    List(),
    List(),
    List(),
    UserStats(
      7,
      Time(0, 1, 28),
      Time(0, 0, 0),
      Time(0, 12, 12),
      Time(0, 0, 0),
      Time(0, 13, 40),
      1,
      0,
      1,
      0,
      2
    ),
    UserSettings(
      false
    )
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
      "walking simulator", 
      "kojima", 
      "roguelike", 
      "greek mythology"
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
      ChatIdDummies.chatId2,
      ChatIdDummies.chatId3
    ),
    UserStats(
      7,
      Time(0, 1, 45),
      Time(0, 0, 0),
      Time(0, 20, 43),
      Time(0, 0, 0),
      Time(0, 22, 28),
      1,
      0,
      0,
      0,
      1
    ),
    UserSettings(
      true
    )
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
      Some(TVShowId(88803)),
      None,
      None
    ),
    List(
      TVShowId(136315),
      TVShowId(100565),
      (TVShowId(136315), SeasonNumber(2), EpisodeNumber(7)),
    ),
    List(),
    List(),
    List(),
    List(),
    List(),
    List(
      EntryIdDummies.entryId1,
      EntryIdDummies.entryId8,
      EntryIdDummies.entryId14
    ),
    List(
      ReviewIdDummies.reviewId1,
      ReviewIdDummies.reviewId6
    ),
    List(
      RatingIdDummies.ratingId1,
      RatingIdDummies.ratingId8
    ),
    List(
      LikeIdDummies.likeId3
    ),
    List(),
    List(
      "disney", 
      "trauma", 
      "anime", 
      "mecha"
    ),
    List(UserIdDummies.userId2),
    List(UserIdDummies.userId3),
    List(),
    List(
      ChatIdDummies.chatId1
    ),
    UserStats(
      9.5,
      Time(0, 0, 0),
      Time(0, 22, 0),
      Time(0, 0, 0),
      Time(0, 0, 0),
      Time(0, 22, 0),
      2,
      0,
      0,
      0,
      2
    ),
    UserSettings(
      false
    )
  )

}