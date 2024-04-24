package modelClasses

import cats.effect.*
import io.circe.Printer
import io.circe.generic.auto._
import io.circe.syntax.*
import modelClasses.{User, MediaContent, Movie, TVShow, Season, Episode, Videogame, Book, MediaContentList, Comment, UserSettings, 
  UserStats}
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import sttp.tapir.model.UsernamePassword

import sttp.tapir.Schema.derived

case class User(
               id: User.Id,
               // TODO: usernamePassword: UsernamePassword,
               username: User.Username,
               password: String,
               email: String,
               biography: String,
               location: String, 
               // TODO: Cambiar por Location
               favoriteMovieId: Movie.Id,
               favoriteTVShowId: TVShow.Id,
               favoriteVideogameId: Videogame.Id,
               favoriteBookId: Book.Id,
               completed: List[MediaContent],
               pending: List[MediaContent],
               inProgress: List[MediaContent],
               onHold: List[MediaContent],
               dropped: List[MediaContent],
               wishlist: List[MediaContent],
               userListsIds: List[MediaContentList.Id],
               userReviewsIds: List[Review.Id],
               userCommentsIds: List[Comment.Id],
               userLikesIds: List[Like.Id],
               tags: List[String],
               followersIds: List[User.Id],
               followingIds: List[User.Id],
               blockedUsersIds: List[User.Id],
               chatsIds: List[Chat.Id],
               stats: UserStats,
               settings: UserSettings 
               )

object User {
  type Id = Long
  type Username = String
}


