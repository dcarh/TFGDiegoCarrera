package modelClasses.app.user

import io.circe.generic.auto.*
import modelClasses.app.chatting.Chat
import modelClasses.app.media.{Book, Movie, TVShow, Videogame}
import modelClasses.app.social.{Entry, Like, MediaContentList, Rating, Reply, Review}

case class User(
               id        : User.Id,
               profile   : UserProfile,
               favourites: UserFavourites,
               completed : List[Movie.Id | TVShow.Id | Videogame.Id | Book.Id],
               pending   : List[Movie.Id | TVShow.Id | Videogame.Id | Book.Id],
               inProgress: List[Movie.Id | TVShow.Id | Videogame.Id | Book.Id],
               onHold    : List[Movie.Id | TVShow.Id | Videogame.Id | Book.Id],
               dropped   : List[Movie.Id | TVShow.Id | Videogame.Id | Book.Id],
               lists     : List[MediaContentList.Id],
               entries   : List[Entry.Id],
               reviews   : List[Review.Id],
               ratings   : List[Rating.Id],
               likes     : List[Like.Id],
               replies   : List[Reply.Id],
               // tags   : List[String],
               following : List[User.Id],
               followers : List[User.Id],
               blocked   : List[User.Id],
               chats     : List[Chat.Id],
               stats     : UserStats,
               settings  : UserSettings 
               )

object User {
  type Id = Long
}


