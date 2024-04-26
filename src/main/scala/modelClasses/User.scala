package modelClasses

import cats.effect.*
import io.circe.Printer
import io.circe.generic.auto._
import io.circe.syntax.*
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import sttp.tapir.model.UsernamePassword

import sttp.tapir.Schema.derived

case class User(
               id        : User.Id,
               // TODO: usernamePassword: UsernamePassword,
               profile   : UserProfile,
               favourites: UserFavourites,
               completed : List[MediaContent.Id],
               pending   : List[MediaContent.Id],
               inProgress: List[MediaContent.Id],
               onHold    : List[MediaContent.Id],
               dropped   : List[MediaContent.Id],
               lists     : List[MediaContentList.Id],
               entries   : List[Entry.Id],
               reviews   : List[Review.Id],
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


