package server.logics.user.media

import cats.effect.IO
import dummies.repositories.UserRepository
import modelClasses.errors.UserError.*
import modelClasses.ids.User.UserId
import modelClasses.app.user.UserFavourites
import modelClasses.ids.Media.{BookId, MovieId, TvShowId, VideogameId}

import server.logics.commonFunctions.CommonFunctions

object UserFavouritesLogics {

  val getFavourites: UserId => IO[Either[UserError, UserFavourites]] =
    userId => IO {
      CommonFunctions.getUser(userId) match
        case Left(error) => Left(error)
        case Right(user) => Right(user.favourites)

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val addFavouriteMovie: ((UserId, MovieId)) => IO[Either[UserError, UserFavourites]] =
    (userId, movieId) => IO {
      if movieId.value <= 0 then
        Left(BadRequest("Invalid movie ID"))
      else
        CommonFunctions.getUser(userId) match
          case Left(error) => Left(error)
          case Right(user) =>
            user.favourites.movie match
              case Some(_) =>
                Left(Conflict("The user already has a favourite movie. Delete it before you add a new one"))
              case None =>
                val newFavourites = user.favourites.copy(movie = Some(movieId))
                val updatedUser = user.copy(favourites = newFavourites)
                UserRepository.put(userId, updatedUser)
                Right(newFavourites)

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val addFavouriteTvShow: ((UserId, TvShowId)) => IO[Either[UserError, UserFavourites]] =
    (userId, tvShowId) => IO {
      if tvShowId.value <= 0 then
        Left(BadRequest("Invalid TV show ID"))
      else
        CommonFunctions.getUser(userId) match
          case Left(error) => Left(error)
          case Right(user) =>
            user.favourites.tvShow match
              case Some(_) =>
                Left(Conflict("The user already has a favourite TV show. Delete it before you add a new one"))
              case None =>
                val newFavourites = user.favourites.copy(tvShow = Some(tvShowId))
                val updatedUser = user.copy(favourites = newFavourites)
                UserRepository.put(userId, updatedUser)
                Right(newFavourites)

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val addFavouriteVideogame: ((UserId, VideogameId)) => IO[Either[UserError, UserFavourites]] =
    (userId, videogameId) => IO {
      if videogameId.value <= 0 then
        Left(BadRequest("Invalid videogame ID"))
      else
        CommonFunctions.getUser(userId) match
          case Left(error) => Left(error)
          case Right(user) =>
            user.favourites.videogame match
              case Some(_) =>
                Left(Conflict("The user already has a favourite videogame. Delete it before you add a new one"))
              case None =>
                val newFavourites = user.favourites.copy(videogame = Some(videogameId))
                val updatedUser = user.copy(favourites = newFavourites)
                UserRepository.put(userId, updatedUser)
                Right(newFavourites)

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val addFavouriteBook: ((UserId, BookId)) => IO[Either[UserError, UserFavourites]] =
    (userId, bookId) => IO {
      if bookId.value == "" then
        Left(BadRequest("Invalid book ID"))
      else
        CommonFunctions.getUser(userId) match
          case Left(error) => Left(error)
          case Right(user) =>
            user.favourites.book match
              case Some(_) =>
                Left(Conflict("The user already has a favourite book. Delete it before you add a new one"))
              case None =>
                val newFavourites = user.favourites.copy(book = Some(bookId))
                val updatedUser = user.copy(favourites = newFavourites)
                UserRepository.put(userId, updatedUser)
                Right(newFavourites)

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val deleteFavouriteMovie: ((UserId, MovieId)) => IO[Either[UserError, Unit]] =
    (userId, movieId) => IO {
      if movieId.value <= 0 then
        Left(BadRequest("Invalid movie ID"))
      else
        CommonFunctions.getUser(userId) match
          case Left(error) => Left(error)
          case Right(user) =>
            user.favourites.movie match
              case Some(_) =>
                val newFavourites = user.favourites.copy(movie = None)
                val updatedUser = user.copy(favourites = newFavourites)
                UserRepository.put(userId, updatedUser)
                Right(())
              case None =>
                Left(NotFound(s"Not found favourite movie with ID: $movieId"))

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val deleteFavouriteTvShow: ((UserId, TvShowId)) => IO[Either[UserError, Unit]] =
    (userId, tvShowId) => IO {
      if tvShowId.value <= 0 then
        Left(BadRequest("Invalid TV show ID"))
      else
        CommonFunctions.getUser(userId) match
          case Left(error) => Left(error)
          case Right(user) =>
            user.favourites.tvShow match
              case Some(_) =>
                val newFavourites = user.favourites.copy(tvShow = None)
                val updatedUser = user.copy(favourites = newFavourites)
                UserRepository.put(userId, updatedUser)
                Right(())
              case None =>
                Left(NotFound(s"Not found favourite TV show with ID: $tvShowId"))

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val deleteFavouriteVideogame: ((UserId, VideogameId)) => IO[Either[UserError, Unit]] =
    (userId, videogameId) => IO {
      if videogameId.value <= 0 then
        Left(BadRequest("Invalid videogame ID"))
      else
        CommonFunctions.getUser(userId) match
          case Left(error) => Left(error)
          case Right(user) =>
            user.favourites.videogame match
              case Some(_) =>
                val newFavourites = user.favourites.copy(videogame = None)
                val updatedUser = user.copy(favourites = newFavourites)
                UserRepository.put(userId, updatedUser)
                Right(())
              case None =>
                Left(NotFound(s"Not found favourite videogame with ID: $videogameId"))

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val deleteFavouriteBook: ((UserId, BookId)) => IO[Either[UserError, Unit]] =
    (userId, bookId) => IO {
      if bookId.value <= "" then
        Left(BadRequest("Invalid book ID"))
      else
        CommonFunctions.getUser(userId) match
          case Left(error) => Left(error)
          case Right(user) =>
            user.favourites.book match
              case Some(_) =>
                val newFavourites = user.favourites.copy(book = None)
                val updatedUser = user.copy(favourites = newFavourites)
                UserRepository.put(userId, updatedUser)
                Right(())
              case None =>
                Left(NotFound(s"Not found favourite book with ID: $bookId"))

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

}
