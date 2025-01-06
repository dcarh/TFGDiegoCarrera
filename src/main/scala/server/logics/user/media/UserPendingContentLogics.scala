package server.logics.user.media

import cats.effect.IO
import dummies.repositories.UserRepository
import modelClasses.errors.UserError.*
import modelClasses.ids.User.UserId
import modelClasses.ids.Media.{BookId, EpisodeNumber, MovieId, SeasonNumber, TVShowId, VideogameId}

object UserPendingContentLogics {

  // TODO: Implementar funcionalidad de sortByOption y categoryOption

  val getPendingLogic: ((UserId, Option[String], Option[List[String]])) => IO[Either[UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    (userId, sortByOption, categoryOption) => IO {
      UserRepository.get(userId) match {
        case Some(user) =>
          Right(user.pending)

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

  val addPendingMovieLogic: ((UserId, MovieId)) => IO[Either[UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    ???

  val addPendingTvShowLogic: ((UserId, TVShowId)) => IO[Either[UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    ???

  val addPendingSeasonLogic: ((UserId, TVShowId, SeasonNumber)) => IO[Either[UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    ???

  val addPendingEpisodeLogic: ((UserId, TVShowId, SeasonNumber, EpisodeNumber)) => IO[Either[UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    ???

  val addPendingVideogameLogic: ((UserId, VideogameId)) => IO[Either[UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    ???

  val addPendingBookLogic: ((UserId, BookId)) => IO[Either[UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    ???

  val deletePendingMovieLogic: ((UserId, MovieId)) => IO[Either[UserError, Unit]] =
    ???

  val deletePendingTvShowLogic: ((UserId, TVShowId)) => IO[Either[UserError, Unit]] =
    ???

  val deletePendingSeasonLogic: ((UserId, TVShowId, SeasonNumber)) => IO[Either[UserError, Unit]] =
    ???

  val deletePendingEpisodeLogic: ((UserId, TVShowId, SeasonNumber, EpisodeNumber)) => IO[Either[UserError, Unit]] =
    ???

  val deletePendingVideogameLogic: ((UserId, VideogameId)) => IO[Either[UserError, Unit]] =
    ???

  val deletePendingBookLogic: ((UserId, BookId)) => IO[Either[UserError, Unit]] =
    ???

}
