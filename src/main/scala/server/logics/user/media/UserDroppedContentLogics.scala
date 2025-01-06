package server.logics.user.media

import cats.effect.IO
import dummies.repositories.UserRepository
import modelClasses.errors.UserError.*
import modelClasses.ids.User.UserId
import modelClasses.ids.Media.{BookId, EpisodeNumber, MovieId, SeasonNumber, TVShowId, VideogameId}

object UserDroppedContentLogics {

  // TODO: Implementar funcionalidad de sortByOption y categoryOption

  val getDroppedLogic: ((UserId, Option[String], Option[List[String]])) => IO[Either[UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    (userId, sortByOption, categoryOption) => IO {
      UserRepository.get(userId) match {
        case Some(user) =>
          Right(user.dropped)

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

  val addDroppedMovieLogic: ((UserId, MovieId)) => IO[Either[UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    ???

  val addDroppedTvShowLogic: ((UserId, TVShowId)) => IO[Either[UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    ???

  val addDroppedSeasonLogic: ((UserId, TVShowId, SeasonNumber)) => IO[Either[UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    ???

  val addDroppedEpisodeLogic: ((UserId, TVShowId, SeasonNumber, EpisodeNumber)) => IO[Either[UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    ???

  val addDroppedVideogameLogic: ((UserId, VideogameId)) => IO[Either[UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    ???

  val addDroppedBookLogic: ((UserId, BookId)) => IO[Either[UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    ???

  val deleteDroppedMovieLogic: ((UserId, MovieId)) => IO[Either[UserError, Unit]] =
    ???

  val deleteDroppedTvShowLogic: ((UserId, TVShowId)) => IO[Either[UserError, Unit]] =
    ???

  val deleteDroppedSeasonLogic: ((UserId, TVShowId, SeasonNumber)) => IO[Either[UserError, Unit]] =
    ???

  val deleteDroppedEpisodeLogic: ((UserId, TVShowId, SeasonNumber, EpisodeNumber)) => IO[Either[UserError, Unit]] =
    ???

  val deleteDroppedVideogameLogic: ((UserId, VideogameId)) => IO[Either[UserError, Unit]] =
    ???

  val deleteDroppedBookLogic: ((UserId, BookId)) => IO[Either[UserError, Unit]] =
    ???

}
