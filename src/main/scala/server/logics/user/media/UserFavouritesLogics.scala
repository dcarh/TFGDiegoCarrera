package server.logics.user.media

import cats.effect.IO
import dummies.repositories.UserRepository
import modelClasses.app.user.User
import modelClasses.errors.UserError.*
import modelClasses.ids.User.UserId
import modelClasses.app.user.UserFavourites
import modelClasses.ids.Media.{BookId, MovieId, TvShowId, VideogameId}

import server.logics.commonFunctions.CommonFunctions

object UserFavouritesLogics {

  val getFavourites: UserId => IO[Either[UserError, UserFavourites]] =
    userId => IO.pure {
      CommonFunctions.getUser(userId) match
        case Left(error) => Left(error)
        case Right(user) => Right(user.favourites)

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }


  private val addFavourite: (UserId, MovieId | TvShowId | VideogameId | BookId) => Either[UserError, UserFavourites] =
    (userId, mediaId) =>
      CommonFunctions.getUser(userId) match
        case Left(error) => Left(error)
        case Right(user) =>
          val updatedFavourites = mediaId match

            case movieId: MovieId =>
              user.favourites.movie match
                case Some(_) => Left(Conflict("The user already has a favourite movie. Delete it before you add a new one"))
                case None => Right(user.favourites.copy(movie = Some(movieId)))

            case tvShowId: TvShowId =>
              user.favourites.tvShow match
                case Some(_) => Left(Conflict("The user already has a favourite TV show. Delete it before you add a new one"))
                case None => Right(user.favourites.copy(tvShow = Some(tvShowId)))

            case videogameId: VideogameId =>
              user.favourites.videogame match
                case Some(_) => Left(Conflict("The user already has a favourite videogame. Delete it before you add a new one"))
                case None => Right(user.favourites.copy(videogame = Some(videogameId)))

            case bookId: BookId =>
              user.favourites.book match
                case Some(_) => Left(Conflict("The user already has a favourite book. Delete it before you add a new one"))
                case None => Right(user.favourites.copy(book = Some(bookId)))

          updatedFavourites match
            case Left(error) => Left(error)
            case Right(favourites) =>
              val updatedUser = user.copy(favourites = favourites)
              UserRepository.put(userId, user)
              Right(favourites)


  private val deleteFavourite: (UserId, MovieId | TvShowId | VideogameId | BookId) => Either[UserError, Unit] =
    (userId, mediaId) =>
      CommonFunctions.getUser(userId) match
        case Left(error) => Left(error)
        case Right(user) =>
          val updatedFavourites = mediaId match

            case movieId: MovieId =>
              user.favourites.movie match
                case Some(_) => Right(user.favourites.copy(movie = None))
                case None => Left(NotFound("The user does not have a favourite movie"))

            case tvShowId: TvShowId =>
              user.favourites.tvShow match
                case Some(_) => Right(user.favourites.copy(tvShow = None))
                case None => Left(NotFound("The user does not have a favourite TV show"))

            case videogameId: VideogameId =>
              user.favourites.videogame match
                case Some(_) => Right(user.favourites.copy(videogame = None))
                case None => Left(NotFound("The user does not have a favourite videogame"))

            case bookId: BookId =>
              user.favourites.book match
                case Some(_) => Right(user.favourites.copy(book = None))
                case None => Left(NotFound("The user does not have a favourite book"))

          updatedFavourites match
            case Left(error) => Left(error)
            case Right(favourites) =>
              val updatedUser = user.copy(favourites = favourites)
              UserRepository.put(userId, user)
              Right(())


  val addFavouriteMovie: ((UserId, MovieId)) => IO[Either[UserError, UserFavourites]] =
    (userId, movieId) => IO.pure {
      if movieId.value <= 0 then
        Left(BadRequest("Invalid movie ID"))
      else
        addFavourite(userId, movieId)

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val addFavouriteTvShow: ((UserId, TvShowId)) => IO[Either[UserError, UserFavourites]] =
    (userId, tvShowId) => IO.pure {
      if tvShowId.value <= 0 then
        Left(BadRequest("Invalid TV show ID"))
      else
        addFavourite(userId, tvShowId)

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val addFavouriteVideogame: ((UserId, VideogameId)) => IO[Either[UserError, UserFavourites]] =
    (userId, videogameId) => IO.pure {
      if videogameId.value <= 0 then
        Left(BadRequest("Invalid videogame ID"))
      else
        addFavourite(userId, videogameId)

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }
  
  val addFavouriteBook: ((UserId, BookId)) => IO[Either[UserError, UserFavourites]] =
    (userId, bookId) => IO.pure {
      if bookId.value == "" then
        Left(BadRequest("Invalid book ID"))
      else
        addFavourite(userId, bookId)

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val deleteFavouriteMovie: ((UserId, MovieId)) => IO[Either[UserError, Unit]] =
    (userId, movieId) => IO.pure {
      if movieId.value <= 0 then
        Left(BadRequest("Invalid movie ID"))
      else
        deleteFavourite(userId, movieId)

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val deleteFavouriteTvShow: ((UserId, TvShowId)) => IO[Either[UserError, Unit]] =
    (userId, tvShowId) => IO.pure {
      if tvShowId.value <= 0 then
        Left(BadRequest("Invalid TV show ID"))
      else
        deleteFavourite(userId, tvShowId)

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val deleteFavouriteVideogame: ((UserId, VideogameId)) => IO[Either[UserError, Unit]] =
    (userId, videogameId) => IO.pure {
      if videogameId.value <= 0 then
        Left(BadRequest("Invalid videogame ID"))
      else
        deleteFavourite(userId, videogameId)

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val deleteFavouriteBook: ((UserId, BookId)) => IO[Either[UserError, Unit]] =
    (userId, bookId) => IO.pure {
      if bookId.value <= "" then
        Left(BadRequest("Invalid book ID"))
      else
        deleteFavourite(userId, bookId)

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

}
