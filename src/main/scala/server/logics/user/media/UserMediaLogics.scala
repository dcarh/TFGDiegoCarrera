package server.logics.user.media

import cats.effect.IO
import modelClasses.errors.UserError.*
import modelClasses.ids.User.UserId
import modelClasses.ids.Media.{BookId, EpisodeNumber, MovieId, SeasonNumber, TvShowId, VideogameId}
import server.logics.commonFunctions.CommonFunctions

object UserMediaLogics {

  // TODO: Implementar funcionalidad de sortByOption (en caso de seguir adelante con ello)

  val getAllMedia: ((UserId, String, Option[String], Option[List[String]])) => IO[
    Either[
      UserError,
      List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]
    ]
  ] =
    (userId, field, sortByOption, categoryOption) => IO {
      CommonFunctions.getAllMedia(userId, field, sortByOption, categoryOption) match
        case Left(error) => Left(error)
        case Right(Left(list)) => Right(list)
        case Right(Right(list)) => Right(list)
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val addMovie:
    ((UserId, String, MovieId)) => IO[Either[UserError, List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    (userId, field, movieId) => IO {
      CommonFunctions.addMedia(userId, field, movieId) match
        case Left(error) => Left(error)
        case Right(Left(list)) => Right(list)
        case Right(Right(_)) => Left(Unknown(500, "Internal server error"))
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val addTvShow:
    ((UserId, String, TvShowId)) => IO[Either[UserError, List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    (userId, field, tvShowId) => IO {
      CommonFunctions.addMedia(userId, field, tvShowId) match
        case Left(error) => Left(error)
        case Right(Left(list)) => Right(list)
        case Right(Right(list)) => Right(list)
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val addSeason:
    ((UserId, String, TvShowId, SeasonNumber)) => IO[Either[UserError, List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    (userId, field, tvShowId, seasonNumber) => IO {
      CommonFunctions.addMedia(userId, field, (tvShowId, seasonNumber)) match
        case Left(error) => Left(error)
        case Right(Left(list)) => Right(list)
        case Right(Right(list)) => Right(list)
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val addEpisode:
    ((UserId, String, TvShowId, SeasonNumber, EpisodeNumber)) => IO[Either[UserError, List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    (userId, field, tvShowId, seasonNumber, episodeNumber) => IO {
      CommonFunctions.addMedia(userId, field, (tvShowId, seasonNumber, episodeNumber)) match
        case Left(error) => Left(error)
        case Right(Left(list)) => Right(list)
        case Right(Right(_)) => Left(Unknown(500, "Internal server error"))
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val addVideogame:
    ((UserId, String, VideogameId)) => IO[Either[UserError, List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    (userId, field, videogameId) => IO {
      CommonFunctions.addMedia(userId, field, videogameId) match
        case Left(error) => Left(error)
        case Right(Left(list)) => Right(list)
        case Right(Right(list)) => Right(list)
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val addBook:
    ((UserId, String, BookId)) => IO[Either[UserError, List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    (userId, field, bookId) => IO {
      CommonFunctions.addMedia(userId, field, bookId) match
        case Left(error) => Left(error)
        case Right(Left(list)) => Right(list)
        case Right(Right(list)) => Right(list)
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val deleteMovie: ((UserId, String, MovieId)) => IO[Either[UserError, Unit]] =
    (userId, field, movieId) => IO {
      CommonFunctions.deleteMedia(userId, field, movieId)
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val deleteTvShow: ((UserId, String, TvShowId)) => IO[Either[UserError, Unit]] =
    (userId, field, tvShowId) => IO {
      CommonFunctions.deleteMedia(userId, field, tvShowId)
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val deleteSeason: ((UserId, String, TvShowId, SeasonNumber)) => IO[Either[UserError, Unit]] =
    (userId, field, tvShowId, seasonNumber) => IO {
      CommonFunctions.deleteMedia(userId, field, (tvShowId, seasonNumber))
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val deleteEpisode: ((UserId, String, TvShowId, SeasonNumber, EpisodeNumber)) => IO[Either[UserError, Unit]] =
    (userId, field, tvShowId, seasonNumber, episodeNumber) => IO {
      CommonFunctions.deleteMedia(userId, field, (tvShowId, seasonNumber, episodeNumber))
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val deleteVideogame: ((UserId, String, VideogameId)) => IO[Either[UserError, Unit]] =
    (userId, field, videogameId) => IO {
      CommonFunctions.deleteMedia(userId, field, videogameId)
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val deleteBook: ((UserId, String, BookId)) => IO[Either[UserError, Unit]] =
    (userId, field, bookId) => IO {
      CommonFunctions.deleteMedia(userId, field, bookId)
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

}
