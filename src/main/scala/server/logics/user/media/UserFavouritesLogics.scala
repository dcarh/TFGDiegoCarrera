package server.logics.user.media

import cats.effect.IO
import dummies.repositories.UserRepository
import modelClasses.errors.UserError.*
import modelClasses.ids.User.UserId
import modelClasses.app.user.UserFavourites
import modelClasses.ids.Media.{BookId, MovieId, TVShowId, VideogameId}

object UserFavouritesLogics {

  val getFavouritesLogic: UserId => IO[Either[UserError, UserFavourites]] =
    userId => IO {
      UserRepository.get(userId) match {
        case Some(user) =>
          Right(user.favourites)

        case None if userId.value <= 0 =>
          Left(BadRequest("Invalid user ID"))

        case None =>
          Left(NotFound(s"User with ID ${userId.value} not found"))
      }
    }.handleError {
      case ex: Exception =>
        Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val addFavouriteMovieLogic: ((UserId, MovieId)) => IO[Either[UserError, UserFavourites]] =
    (userId, movieId) => IO {
      if movieId.value <= 0 then
        Left(BadRequest("Invalid movie ID"))
      else
        UserRepository.get(userId) match
          case Some(user) =>
            user.favourites.movie match
              case Some(_) =>
                Left(Conflict("The user already has a favourite movie. Delete it before you add a new one"))
              case None =>
                val newFavourites = user.favourites.copy(movie = Some(movieId))
                val updatedUser = user.copy(favourites = newFavourites)
                Right(newFavourites)
          case None if userId.value <= 0 =>
            Left(BadRequest("Invalid user ID"))
          case None =>
            Left(NotFound(s"User with ID ${userId.value} not found"))
    }.handleError {
      case ex: Exception =>
        Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val addFavouriteTvShowLogic: ((UserId, TVShowId)) => IO[Either[UserError, UserFavourites]] =
    (userId, tvShowId) => IO {
      if tvShowId.value <= 0 then
        Left(BadRequest("Invalid TV show ID"))
      else
        UserRepository.get(userId) match
          case Some(user) =>
            user.favourites.tvShow match
              case Some(_) =>
                Left(Conflict("The user already has a favourite TV show. Delete it before you add a new one"))
              case None =>
                val newFavourites = user.favourites.copy(tvShow = Some(tvShowId))
                val updatedUser = user.copy(favourites = newFavourites)
                Right(newFavourites)
          case None if userId.value <= 0 =>
            Left(BadRequest("Invalid user ID"))
          case None =>
            Left(NotFound(s"User with ID ${userId.value} not found"))
    }.handleError {
      case ex: Exception =>
        Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val addFavouriteVideogameLogic: ((UserId, VideogameId)) => IO[Either[UserError, UserFavourites]] =
    (userId, videogameId) => IO {
      if videogameId.value <= 0 then
        Left(BadRequest("Invalid videogame ID"))
      else
        UserRepository.get(userId) match
          case Some(user) =>
            user.favourites.videogame match
              case Some(_) =>
                Left(Conflict("The user already has a favourite videogame. Delete it before you add a new one"))
              case None =>
                val newFavourites = user.favourites.copy(videogame = Some(videogameId))
                val updatedUser = user.copy(favourites = newFavourites)
                Right(newFavourites)
          case None if userId.value <= 0 =>
            Left(BadRequest("Invalid user ID"))
          case None =>
            Left(NotFound(s"User with ID ${userId.value} not found"))
    }.handleError {
      case ex: Exception =>
        Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val addFavouriteBookLogic: ((UserId, BookId)) => IO[Either[UserError, UserFavourites]] =
    (userId, bookId) => IO {
      if bookId.value == "" then
        Left(BadRequest("Invalid book ID"))
      else
        UserRepository.get(userId) match
          case Some(user) =>
            user.favourites.book match
              case Some(_) =>
                Left(Conflict("The user already has a favourite book. Delete it before you add a new one"))
              case None =>
                val newFavourites = user.favourites.copy(book = Some(bookId))
                val updatedUser = user.copy(favourites = newFavourites)
                Right(newFavourites)
          case None if userId.value <= 0 =>
            Left(BadRequest("Invalid user ID"))
          case None =>
            Left(NotFound(s"User with ID ${userId.value} not found"))
    }.handleError {
      case ex: Exception =>
        Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val deleteFavouriteMovieLogic: ((UserId, MovieId)) => IO[Either[UserError, Unit]] =
    (userId, movieId) => IO {
      if movieId.value <= 0 then
        Left(BadRequest("Invalid movie ID"))
      else
        UserRepository.get(userId) match
          case Some(user) =>
            user.favourites.movie match
              case Some(_) =>
                val newFavourites = user.favourites.copy(movie = None)
                Right(())
              case None =>
                Left(NotFound(s"Not found favourite movie with ID: $movieId"))
          case None if userId.value <= 0 =>
            Left(BadRequest("Invalid user ID"))
          case None =>
            Left(NotFound(s"User with ID ${userId.value} not found"))
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val deleteFavouriteTvShowLogic: ((UserId, TVShowId)) => IO[Either[UserError, Unit]] =
    (userId, tvShowId) => IO {
      if tvShowId.value <= 0 then
        Left(BadRequest("Invalid TV show ID"))
      else
        UserRepository.get(userId) match
          case Some(user) =>
            user.favourites.tvShow match
              case Some(_) =>
                val newFavourites = user.favourites.copy(tvShow = None)
                Right(())
              case None =>
                Left(NotFound(s"Not found favourite TV show with ID: $tvShowId"))
          case None if userId.value <= 0 =>
            Left(BadRequest("Invalid user ID"))
          case None =>
            Left(NotFound(s"User with ID ${userId.value} not found"))
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val deleteFavouriteVideogameLogic: ((UserId, VideogameId)) => IO[Either[UserError, Unit]] =
    (userId, videogameId) => IO {
      if videogameId.value <= 0 then
        Left(BadRequest("Invalid videogame ID"))
      else
        UserRepository.get(userId) match
          case Some(user) =>
            user.favourites.videogame match
              case Some(_) =>
                val newFavourites = user.favourites.copy(videogame = None)
                Right(())
              case None =>
                Left(NotFound(s"Not found favourite videogame with ID: $videogameId"))
          case None if userId.value <= 0 =>
            Left(BadRequest("Invalid user ID"))
          case None =>
            Left(NotFound(s"User with ID ${userId.value} not found"))
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val deleteFavouriteBookLogic: ((UserId, BookId)) => IO[Either[UserError, Unit]] =
    (userId, bookId) => IO {
      if bookId.value <= "" then
        Left(BadRequest("Invalid book ID"))
      else
        UserRepository.get(userId) match
          case Some(user) =>
            user.favourites.book match
              case Some(_) =>
                val newFavourites = user.favourites.copy(book = None)
                Right(())
              case None =>
                Left(NotFound(s"Not found favourite book with ID: $bookId"))
          case None if userId.value <= 0 =>
            Left(BadRequest("Invalid user ID"))
          case None =>
            Left(NotFound(s"User with ID ${userId.value} not found"))
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

}
