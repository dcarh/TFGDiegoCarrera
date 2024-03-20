package modelClasses

import cats.effect.*
import io.circe.Printer
import io.circe.generic.auto._
import io.circe.syntax.*
import modelClasses.{User,Element, Movie, TVShow, Season, Episode, Videogame, Book, ElementList, Comment, Article,
  UserSettings, UserStats, UserTradeInformation}
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import sttp.tapir.model.UsernamePassword

import sttp.tapir.Schema.derived

case class User(
               id: Int,
               // TODO: usernamePassword: UsernamePassword,
               username: String,
               password: String,
               email: String,
               biography: String,
               location: String, 
               // TODO: Cambiar por Location
               favoriteMovieId: Int,
               favoriteTVShowId: Int,
               favoriteVideogameId: Int,
               favoriteBookId: Int,
               completedId: Int,
               pendingId: Int,
               inProgressId: Int,
               onHoldId: Int,
               droppedId: Int,
               wishlistId: Int,
               userListsIds: Seq[Int],
               userReviewsIds: Seq[Int],
               userCommentsIds: Seq[Int],
               userLikesIds: Seq[Int],
               tags: Seq[String],
               followersIds: Seq[Int],
               followingIds: Seq[Int],
               blockedUsersIds: Seq[Int],
               chatsIds: Seq[Int],
               stats: String,
               // TODO: Se ha quedado como String para que no diese errores por el tema de Codecs/Schemas
               //  pero lo suyo es que sea una instancia de UserStats (o separar los posibles campos de dicha clase en 
               //  atributos de esta
               articlesIds: Seq[Int],
               tradeInformation: String,
               // TODO: Lo mismo que con stats, pero siendo una instancia de TradeInformation
               settings: String  //Schema[UserSettings])
               // TODO: Lo mismo que con stats, pero siendo una instancia de UserSettings
               )


