package server.logics.user.media

import cats.effect.IO
import dummies.repositories.UserRepository
import modelClasses.errors.UserError.*
import modelClasses.ids.User.UserId
import modelClasses.ids.Media.{BookId, SeasonNumber, TVShowId, VideogameId}

object UserOnHoldMediaLogics {

  // TODO: Implementar funcionalidad de sortByOption y categoryOption

  val getOnHold: ((UserId, Option[String], Option[List[String]])) => IO[Either[UserError, List[TVShowId | (TVShowId, SeasonNumber) | VideogameId | BookId]]] =
    (userId, sortByOption, categoryOption) => IO {
      UserRepository.get(userId) match {
        case Some(user) =>
          Right(user.onHold)

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

  val addOnHoldTvShow: ((UserId, TVShowId)) => IO[Either[UserError, List[TVShowId | (TVShowId, SeasonNumber) | VideogameId | BookId]]] =
    ???

  val addOnHoldSeason: ((UserId, TVShowId, SeasonNumber)) => IO[Either[UserError, List[TVShowId | (TVShowId, SeasonNumber) | VideogameId | BookId]]] =
    ???

  val addOnHoldVideogame: ((UserId, VideogameId)) => IO[Either[UserError, List[TVShowId | (TVShowId, SeasonNumber) | VideogameId | BookId]]] =
    ???

  val addOnHoldBook: ((UserId, BookId)) => IO[Either[UserError, List[TVShowId | (TVShowId, SeasonNumber) | VideogameId | BookId]]] =
    ???

  val deleteOnHoldTvShow: ((UserId, TVShowId)) => IO[Either[UserError, Unit]] =
    ???

  val deleteOnHoldSeason: ((UserId, TVShowId, SeasonNumber)) => IO[Either[UserError, Unit]] =
    ???

  val deleteOnHoldVideogame: ((UserId, VideogameId)) => IO[Either[UserError, Unit]] =
    ???

  val deleteOnHoldBook: ((UserId, BookId)) => IO[Either[UserError, Unit]] =
    ???

}
