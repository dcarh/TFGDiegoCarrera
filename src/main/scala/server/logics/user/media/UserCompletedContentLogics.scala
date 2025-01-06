package server.logics.user.media

import cats.effect.IO
import dummies.repositories.UserRepository
import modelClasses.errors.UserError.*
import modelClasses.ids.User.UserId
import modelClasses.ids.Media.{BookId, EpisodeNumber, MovieId, SeasonNumber, TVShowId, VideogameId}

object UserCompletedContentLogics {
  
  // TODO: Implementar funcionalidad de sortByOption y categoryOption

  val getCompletedLogic: ((UserId, Option[String], Option[List[String]])) => IO[Either[UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    (userId, sortByOption, categoryOption) => IO {
      UserRepository.get(userId) match 
        case Some(user) =>
          Right(user.completed)

        case None if userId.value <= 0 =>
          Left(BadRequest("Invalid user ID"))

        case None =>
          Left(NotFound(s"User with ID ${userId.value} not found"))
      
    }.handleError {
      case ex: Exception =>
        Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }
    
  // TODO: Dejar estos endpoints para lo último (implementación avanzada de endpoints)

  val addCompletedMovieLogic: ((UserId, MovieId)) => IO[Either[UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    ???
//    (userId, movieId) => IO {
//      if movieId.value <= 0 then ???
//      else
//        UserRepository.get(userId) match 
//          case Some(user) =>
//            ???
//  
//          case None if userId.value <= 0 =>
//            Left(BadRequest("Invalid user ID"))
//  
//          case None =>
//            Left(NotFound(s"User with ID ${userId.value} not found"))
//      
//    }.handleError {
//      case ex: Exception =>
//        Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
//    }
    
  val addCompletedTvShowLogic: ((UserId, TVShowId)) => IO[Either[UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    ???
    
  val addCompletedSeasonLogic: ((UserId, TVShowId, SeasonNumber)) => IO[Either[UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    ???
    
  val addCompletedEpisodeLogic: ((UserId, TVShowId, SeasonNumber, EpisodeNumber)) => IO[Either[UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    ???
    
  val addCompletedVideogameLogic: ((UserId, VideogameId)) => IO[Either[UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    ???
    
  val addCompletedBookLogic: ((UserId, BookId)) => IO[Either[UserError, List[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    ???
    
  val deleteCompletedMovieLogic: ((UserId, MovieId)) => IO[Either[UserError, Unit]] =
    ???
    
  val deleteCompletedTvShowLogic: ((UserId, TVShowId)) => IO[Either[UserError, Unit]] =
    ???
    
  val deleteCompletedSeasonLogic: ((UserId, TVShowId, SeasonNumber)) => IO[Either[UserError, Unit]] =
    ???
    
  val deleteCompletedEpisodeLogic: ((UserId, TVShowId, SeasonNumber, EpisodeNumber)) => IO[Either[UserError, Unit]] =
    ???
    
  val deleteCompletedVideogameLogic: ((UserId, VideogameId)) => IO[Either[UserError, Unit]] =
    ???
    
  val deleteCompletedBookLogic: ((UserId, BookId)) => IO[Either[UserError, Unit]] =
    ???

}
