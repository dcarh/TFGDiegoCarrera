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
               completed: Seq[Element],
               pending: Seq[Element],
               inProgress: Seq[Element],
               onHold: Seq[Element],
               dropped: Seq[Element],
               wishlist: Seq[Element],
               userListsIds: Seq[ElementList.Id],
               userReviewsIds: Seq[Review.Id],
               userCommentsIds: Seq[Comment.Id],
               userLikesIds: Seq[Like.Id],
               tags: Seq[String],
               followersIds: Seq[User.Id],
               followingIds: Seq[User.Id],
               blockedUsersIds: Seq[User.Id],
               chatsIds: Seq[Chat.Id],
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


