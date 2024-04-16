package modelClasses

import cats.effect.*
import io.circe.Printer
import io.circe.generic.auto._
import io.circe.syntax.*
import modelClasses.{User,Element, Movie, TVShow, Season, Episode, Videogame, Book, ElementList, Comment, UserSettings, 
  UserStats, UserTradeInformation}
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
               completed: List[Element],
               pending: List[Element],
               inProgress: List[Element],
               onHold: List[Element],
               dropped: List[Element],
               wishlist: List[Element],
               userListsIds: List[ElementList.Id],
               userReviewsIds: List[Review.Id],
               userCommentsIds: List[Comment.Id],
               userLikesIds: List[Like.Id],
               tags: List[String],
               followersIds: List[User.Id],
               followingIds: List[User.Id],
               blockedUsersIds: List[User.Id],
               chatsIds: List[Chat.Id],
               stats: UserStats,
               // TODO: Se ha quedado como String para que no diese errores por el tema de Codecs/Schemas
               //  pero lo suyo es que sea una instancia de UserStats (o separar los posibles campos de dicha clase en 
               //  atributos de esta
               settings: UserSettings  //Schema[UserSettings])
               // TODO: Lo mismo que con stats, pero siendo una instancia de UserSettings
               )

object User {
  type Id = Long
  type Username = String
}


