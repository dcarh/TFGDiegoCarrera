package server.logics.user.media

import cats.effect.IO
import dummies.repositories.UserRepository
import modelClasses.errors.UserError.*
import modelClasses.ids.User.UserId
import modelClasses.ids.Media.{BookId, SeasonNumber, TvShowId, VideogameId}

object UserOnHoldMediaLogics {

  // TODO: Implementar funcionalidad de sortByOption (en caso de seguir adelante con ello)

  val getAllOnHoldMedia: ((UserId, Option[String], Option[List[String]])) => IO[Either[UserError, List[TvShowId | (TvShowId, SeasonNumber) | VideogameId | BookId]]] =
    (userId, sortByOption, categoryOption) => IO {
      UserRepository.get(userId) match {
        case Some(user) =>
          val onHoldMedia = user.onHold

          val filteredOnHoldMedia = categoryOption match
            case Some(categories) =>
              onHoldMedia.filter {
                case _: TvShowId => categories.contains("tv_show")
                case (_: TvShowId, _: SeasonNumber) => categories.contains("season")
                case videogameId: VideogameId => categories.contains("videogame")
                case bookId: BookId => categories.contains("book")
              }
            case None =>  onHoldMedia

          Right(filteredOnHoldMedia)

        case None if userId.value <= 0 =>
          Left(BadRequest("Invalid user ID"))

        case None =>
          Left(NotFound(s"User with ID ${userId.value} not found"))
      }
    }.handleError {
      case ex: Exception =>
        Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  // TODO: Dejar estos endpoints para lo último (implementación avanzada de endpoints)

//  val addOnHoldTvShow: ((UserId, TvShowId)) => IO[Either[UserError, List[TvShowId | (TvShowId, SeasonNumber) | VideogameId | BookId]]] =
//    ???
//
//  val addOnHoldSeason: ((UserId, TvShowId, SeasonNumber)) => IO[Either[UserError, List[TvShowId | (TvShowId, SeasonNumber) | VideogameId | BookId]]] =
//    ???
//
//  val addOnHoldVideogame: ((UserId, VideogameId)) => IO[Either[UserError, List[TvShowId | (TvShowId, SeasonNumber) | VideogameId | BookId]]] =
//    ???
//
//  val addOnHoldBook: ((UserId, BookId)) => IO[Either[UserError, List[TvShowId | (TvShowId, SeasonNumber) | VideogameId | BookId]]] =
//    ???
//
//  val deleteOnHoldTvShow: ((UserId, TvShowId)) => IO[Either[UserError, Unit]] =
//    ???
//
//  val deleteOnHoldSeason: ((UserId, TvShowId, SeasonNumber)) => IO[Either[UserError, Unit]] =
//    ???
//
//  val deleteOnHoldVideogame: ((UserId, VideogameId)) => IO[Either[UserError, Unit]] =
//    ???
//
//  val deleteOnHoldBook: ((UserId, BookId)) => IO[Either[UserError, Unit]] =
//    ???

}
