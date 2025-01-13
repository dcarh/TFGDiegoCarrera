package modelClasses.app.user

import io.circe.generic.auto.*
import modelClasses.ids.Chatting.ChatId
import modelClasses.ids.Media.{BookId, EpisodeNumber, MovieId, SeasonNumber, TvShowId, VideogameId}
import modelClasses.ids.Social.{EntryId, LikeId, MediaListId, RatingId, ReplyId, ReviewId}
import modelClasses.ids.User.UserId

case class User(
               id        : UserId,
               profile   : UserProfile,
               favourites: UserFavourites,
               completed : List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId],
               pending   : List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId],
               inProgress: List[TvShowId | (TvShowId, SeasonNumber) | VideogameId | BookId],
               onHold    : List[TvShowId | (TvShowId, SeasonNumber) | VideogameId | BookId],
               dropped   : List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId],
               lists     : List[MediaListId],
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


