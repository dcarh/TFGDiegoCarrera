package modelClasses.app.user

import io.circe.generic.auto.*
import modelClasses.ids.Chatting.ChatId
import modelClasses.ids.Media.{BookId, EpisodeNumber, MovieId, SeasonNumber, TVShowId, VideogameId}
import modelClasses.ids.Social.{EntryId, LikeId, MediaContentListId, RatingId, ReplyId, ReviewId}
import modelClasses.ids.User.UserId

case class User(
               id        : UserId,
               profile   : UserProfile,
               favourites: UserFavourites,
               completed : List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId],
               pending   : List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId],
               inProgress: List[TVShowId | (TVShowId, SeasonNumber) | VideogameId | BookId],
               onHold    : List[TVShowId | (TVShowId, SeasonNumber) | VideogameId | BookId],
               dropped   : List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId],
               lists     : List[MediaContentListId],
               entries   : List[EntryId],
               reviews   : List[ReviewId],
               ratings   : List[RatingId],
               likes     : List[LikeId],
               replies   : List[ReplyId],
               tags      : List[String],
               following : List[UserId],
               followers : List[UserId],
               blocked   : List[UserId],
               chats     : List[ChatId],
               stats     : UserStats,
               settings  : UserSettings 
               )

// object User {
//   type Id = Long
// }


