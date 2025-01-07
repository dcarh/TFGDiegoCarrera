package server.logics.user.media

import cats.effect.IO
import dummies.repositories.UserRepository
import modelClasses.errors.UserError.*
import modelClasses.ids.User.UserId
import modelClasses.ids.Media.{BookId, EpisodeNumber, MovieId, SeasonNumber, TVShowId, VideogameId}

object UserDroppedMediaLogics {

  // TODO: Implementar funcionalidad de sortByOption y categoryOption

  val getAllDroppedMedia: ((UserId, Option[String], Option[List[String]])) => IO[Either[UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
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

  val addDroppedMovie: ((UserId, MovieId)) => IO[Either[UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    ???

  val addDroppedTvShow: ((UserId, TVShowId)) => IO[Either[UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    ???

  val addDroppedSeason: ((UserId, TVShowId, SeasonNumber)) => IO[Either[UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    ???

  val addDroppedEpisode: ((UserId, TVShowId, SeasonNumber, EpisodeNumber)) => IO[Either[UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    ???

  val addDroppedVideogame: ((UserId, VideogameId)) => IO[Either[UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    ???

  val addDroppedBook: ((UserId, BookId)) => IO[Either[UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    ???

  val deleteDroppedMovie: ((UserId, MovieId)) => IO[Either[UserError, Unit]] =
    ???

  val deleteDroppedTvShow: ((UserId, TVShowId)) => IO[Either[UserError, Unit]] =
    ???

  val deleteDroppedSeason: ((UserId, TVShowId, SeasonNumber)) => IO[Either[UserError, Unit]] =
    ???

  val deleteDroppedEpisode: ((UserId, TVShowId, SeasonNumber, EpisodeNumber)) => IO[Either[UserError, Unit]] =
    ???

  val deleteDroppedVideogame: ((UserId, VideogameId)) => IO[Either[UserError, Unit]] =
    ???

  val deleteDroppedBook: ((UserId, BookId)) => IO[Either[UserError, Unit]] =
    ???

}
