package modelClasses.user

import io.circe.generic.auto._

import modelClasses.chatting.Chat
import modelClasses.media.MediaMainContent
import modelClasses.social.*

case class User(
               id        : User.Id,
               profile   : UserProfile,
               favourites: UserFavourites,
               completed : List[MediaMainContent.Id],
               pending   : List[MediaMainContent.Id],
               inProgress: List[MediaMainContent.Id],
               onHold    : List[MediaMainContent.Id],
               dropped   : List[MediaMainContent.Id],
               lists     : List[MediaContentList.Id],
               entries   : List[Entry.Id],
               reviews   : List[Review.Id],
               ratings   : List[Rating], // o List[(Either[MediaMainContent, MediaSecondaryContent], Int)]
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


