package server.logics.user.media

import cats.effect.IO
import dummies.repositories.UserRepository
import modelClasses.errors.UserError.*
import modelClasses.ids.User.UserId
import modelClasses.ids.Media.{BookId, SeasonNumber, TvShowId, VideogameId}

object UserInProgressMediaLogics {

  // TODO: Implementar funcionalidad de sortByOption (en caso de seguir adelante con ello)

  val getAllInProgressMedia: ((UserId, Option[String], Option[List[String]])) => IO[Either[UserError, List[TvShowId | (TvShowId, SeasonNumber) | VideogameId | BookId]]] =
    (userId, sortByOption, categoryOption) => IO {
      UserRepository.get(userId) match {
        case Some(user) =>
          val inProgressMedia = user.inProgress

          val filteredInProgressMedia = categoryOption match
            case Some(categories) =>
              inProgressMedia.filter {
                case _: TvShowId => categories.contains("tv_show")
                case (_: TvShowId, _: SeasonNumber) => categories.contains("season")
                case videogameId: VideogameId => categories.contains("videogame")
                case bookId: BookId => categories.contains("book")
              }
            case None =>  inProgressMedia

          Right(filteredInProgressMedia)

        case None if userId.value <= 0 =>
          Left(BadRequest("Invalid user ID"))

        case None =>
          Left(NotFound(s"User with ID ${userId.value} not found"))
      }
    }.handleError {
      case ex: Exception =>
        Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }
    
  val addInProgressTvShow:
    ((UserId, TvShowId)) => IO[Either[UserError, List[TvShowId | (TvShowId, SeasonNumber) | VideogameId | BookId]]] =
    (userId, tvShowId) => addInProgressMedia(userId, tvShowId)

  val addInProgressSeason:
    ((UserId, TvShowId, SeasonNumber)) => IO[Either[UserError, List[TvShowId | (TvShowId, SeasonNumber) | VideogameId | BookId]]] =
    (userId, tvShowId, seasonNumber) => addInProgressMedia(userId, (tvShowId, seasonNumber))

  val addInProgressVideogame:
    ((UserId, VideogameId)) => IO[Either[UserError, List[TvShowId | (TvShowId, SeasonNumber) | VideogameId | BookId]]] =
    (userId, videogameId) => addInProgressMedia(userId, videogameId)

  val addInProgressBook:
    ((UserId, BookId)) => IO[Either[UserError, List[TvShowId | (TvShowId, SeasonNumber) | VideogameId | BookId]]] =
    (userId, bookId) => addInProgressMedia(userId, bookId)

  val deleteInProgressTvShow: ((UserId,  TvShowId)) => IO[Either[UserError, Unit]] =
    (userId, tvShowId) => deleteInProgressMedia(userId, tvShowId)

  val deleteInProgressSeason: ((UserId, TvShowId, SeasonNumber)) => IO[Either[UserError, Unit]] =
    (userId, tvShowId, seasonNumber) => deleteInProgressMedia(userId, (tvShowId, seasonNumber))

  val deleteInProgressVideogame: ((UserId, VideogameId)) => IO[Either[UserError, Unit]] =
    (userId, videogameId) => deleteInProgressMedia(userId, videogameId)

  val deleteInProgressBook: ((UserId, BookId)) => IO[Either[UserError, Unit]] =
    (userId, bookId) => deleteInProgressMedia(userId, bookId)

  private val addInProgressMedia:
    ((UserId, TvShowId | (TvShowId, SeasonNumber) | VideogameId | BookId)) => IO[Either[UserError, List[TvShowId | (TvShowId, SeasonNumber) | VideogameId | BookId]]] =
    (userId, mediaId) => IO {
      UserRepository.get(userId) match
        case None if userId.value <= 0 =>
          Left(BadRequest("Invalid user ID"))

        case None =>
          Left(NotFound(s"User with ID ${userId.value} not found"))

        case Some(user) =>
          if user.inProgress.contains(mediaId) then
            Left(Conflict("Media already in progress"))
          else
            val updatedInProgressMedia = mediaId match
              case tvShowId: TvShowId =>
                if tvShowId.value <= 0 then BadRequest("Invalid TV show ID")
                else
                  tvShowId :: user.inProgress

              case (tvShowId: TvShowId, seasonNumber: SeasonNumber) =>
                if tvShowId.value <= 0 then BadRequest("Invalid TV show ID")
                else if seasonNumber.value <= 0 then BadRequest("Invalid season number")
                else
                  (tvShowId, seasonNumber) :: user.inProgress

              case videogameId: VideogameId =>
                if videogameId.value <= 0 then BadRequest("Invalid videogame ID")
                else
                  videogameId :: user.inProgress

              case bookId: BookId =>
                if bookId.value == "" then BadRequest("Invalid book ID")
                else
                  bookId :: user.inProgress

            updatedInProgressMedia match
              case badRequest: BadRequest =>
                Left(badRequest)

              case list: List[TvShowId | (TvShowId, SeasonNumber) | VideogameId | BookId] =>
                val updatedUser = user.copy(
                  inProgress = list
                )
                UserRepository.put(userId, updatedUser)
                Right(updatedUser.inProgress)

              case _ =>
                Left(Unknown(500, "An unexpected error occurred"))

    }.handleError {
      case ex: Exception =>
        Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  private val deleteInProgressMedia: ((UserId, TvShowId | (TvShowId, SeasonNumber) | VideogameId | BookId)) => IO[Either[UserError, Unit]] =
    (userId, mediaId) => IO {
      UserRepository.get(userId) match
        case None if userId.value <= 0 =>
          Left(BadRequest("Invalid user ID"))

        case None =>
          Left(NotFound(s"User with ID ${userId.value} not found"))

        case Some(user) =>
          if !user.inProgress.contains(mediaId) then
            Left(BadRequest("Media not in progress yet"))
          else
            val updatedInProgressMedia = mediaId match
              case tvShowId: TvShowId =>
                if tvShowId.value <= 0 then BadRequest("Invalid TV show ID")
                else
                  user.inProgress.filterNot(_ == tvShowId)

              case (tvShowId: TvShowId, seasonNumber: SeasonNumber) =>
                if tvShowId.value <= 0 then BadRequest("Invalid TV show ID")
                else if seasonNumber.value <= 0 then BadRequest("Invalid season number")
                else
                  user.inProgress.filterNot(_ == (tvShowId, seasonNumber))

              case videogameId: VideogameId =>
                if videogameId.value <= 0 then BadRequest("Invalid videogame ID")
                else
                  user.inProgress.filterNot(_ == videogameId)

              case bookId: BookId =>
                if bookId.value == "" then BadRequest("Invalid book ID")
                else
                  user.inProgress.filterNot(_ == bookId)

            updatedInProgressMedia match
              case badRequest: BadRequest =>
                Left(badRequest)

              case list: List[TvShowId | (TvShowId, SeasonNumber) | VideogameId | BookId] =>
                val updatedUser = user.copy(
                  inProgress = list
                )
                UserRepository.put(userId, updatedUser)
                Right(())

              case _ =>
                Left(Unknown(500, "An unexpected error occurred"))


    }.handleError {
      case ex: Exception =>
        Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }
}
