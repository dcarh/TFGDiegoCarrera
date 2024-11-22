package modelClasses.dummies

import modelClasses.app.social.{MediaContentList, Visibility}
import modelClasses.dummies.ids.{LikeIdDummies, MediaContentListIdDummies, ReplyIdDummies, UserIdDummies}
import modelClasses.ids.Media.{BookId, MovieId, TVShowId, VideogameId}
import modelClasses.ids.Social.{LikeId, MediaContentListId, ReplyId}
import modelClasses.ids.User.UserId

import java.time.LocalDateTime

object MediaContentListDummies {
  val mediaContentList1: MediaContentList = MediaContentList(
    MediaContentListIdDummies.mediaContentListId1,
    UserIdDummies.userId3,
    "Martial arts",
    "",
    List(
      MovieId(94329),
      MovieId(180299),
      MovieId(449992),
      MovieId(146),
      MovieId(9550),
      VideogameId(144022),
      MovieId(14756),
      MovieId(37472),
      MovieId(365222),
      MovieId(449924),
      MovieId(12580),
      VideogameId(1267),
      VideogameId(11397),
      TVShowId(56425),
      TVShowId(77169)
    ),
    Visibility.Public,
    false,
    false,
    LocalDateTime.of(2024, 5, 29, 20, 43, 12),
    LocalDateTime.of(2024, 8, 3, 16, 9, 27),
    List(LikeIdDummies.likeId7),
    List()
  )
  
  val mediaContentList2: MediaContentList = MediaContentList(
    MediaContentListIdDummies.mediaContentListId2,
    UserIdDummies.userId1,
    "Top 5 movies 2021",
    "My top 5 movies released in 2021",
    List(
      MovieId(758866),
      MovieId(632617),
      MovieId(749004),
      MovieId(660120),
      MovieId(16372)
    ),
    Visibility.Followers,
    true,
    true,
    LocalDateTime.of(2024, 6, 18, 16, 57, 51),
    LocalDateTime.of(2024, 6, 18, 16, 57, 51),
    List(),
    List(ReplyIdDummies.replyId1)
  )
  
  val mediaContentList3: MediaContentList = MediaContentList(
    MediaContentListIdDummies.mediaContentListId3,
    UserIdDummies.userId1,
    "Books read in 2024",
    "A list with the books I've read in 2024 so far",
    List(
      BookId("ydQiDQAAQBAJ"),
      BookId("zyUpEQAAQBAJ"),
      BookId("RDooAQAAIAAJ"),
      BookId("OQ2yHDIMQC")
    ),
    Visibility.Private,
    false,
    false,
    LocalDateTime.of(2024, 6, 29, 17, 48, 12),
    LocalDateTime.of(2024, 12, 17, 22, 34, 29),
    List(),
    List()
  )
  
  val mediaContentList4: MediaContentList = MediaContentList(
    MediaContentListIdDummies.mediaContentListId4,
    UserIdDummies.userId2,
    "Movies, games, etc. to get to know me",
    "Hope you enjoy them as much as I've done",
    List(
      MovieId(10402),
      MovieId(376867),
      MovieId(712454),
      VideogameId(7346),
      VideogameId(253148),
      VideogameId(113112),
      VideogameId(21865),
      TVShowId(135918),
      TVShowId(1402),
      BookId("kHh_EAAAQBAJ")
    ),
    Visibility.Followers,
    true,
    false,
    LocalDateTime.of(2024, 11, 3, 19, 12, 18),
    LocalDateTime.of(2024, 11, 3, 19, 12, 18),
    List(LikeIdDummies.likeId3),
    List()
  )
  
}