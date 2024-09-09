package modelClasses.app.user

import upickle.default.*

import io.circe.generic.auto.*

import modelClasses.app.chatting.Chat
import modelClasses.app.media.{Book, Movie, TVShow, Videogame}
import modelClasses.app.social.{Entry, Like, MediaContentList, Rating, Reply, Review}

import modelClasses.ids.Chatting.ChatId
import modelClasses.ids.Media.{MovieId, TVShowId, VideogameId, BookId}
import modelClasses.ids.Social.{EntryId, LikeId, MediaContentListId, RatingId, ReviewId, ReplyId}
import modelClasses.ids.User.UserId

case class User(
               id        : UserId,
               profile   : UserProfile,
               favourites: UserFavourites,
               completed : List[MovieId | TVShowId | VideogameId | BookId],
               pending   : List[MovieId | TVShowId | VideogameId | BookId],
               inProgress: List[MovieId | TVShowId | VideogameId | BookId],
               onHold    : List[MovieId | TVShowId | VideogameId | BookId],
               dropped   : List[MovieId | TVShowId | VideogameId | BookId],
               lists     : List[MediaContentListId],
               entries   : List[EntryId],
               reviews   : List[ReviewId],
               ratings   : List[RatingId],
               likes     : List[LikeId],
               replies   : List[ReplyId],
               // tags   : List[String],
               following : List[UserId],
               followers : List[UserId],
               blocked   : List[UserId],
               chats     : List[ChatId],
               stats     : UserStats,
               settings  : UserSettings 
               ) derives ReadWriter

// object User {
//   type Id = Long
// }


