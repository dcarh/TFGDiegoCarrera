package server.logics.user.media

import cats.effect.IO
import dummies.repositories.UserRepository
import modelClasses.errors.UserError.*
import modelClasses.ids.User.UserId
import modelClasses.ids.Media.{BookId, SeasonNumber, TvShowId, VideogameId}

object UserOnHoldMediaLogics {

  // TODO: Implementar funcionalidad de sortByOption (en caso de seguir adelante con ello)

  val getAllOnHoldMedia: ((UserId, Option[String], Option[List[String]])) => IO[Either[UserError, List[TvShowId | (TvShowId, SeasonNumber) | VideogameId | BookId]]] =
    (userId, sortByOption, categoryOption) => IO {
      UserRepository.get(userId) match {
        case Some(user) =>
          val onHoldMedia = user.onHold

          val filteredOnHoldMedia = categoryOption match
            case Some(categories) =>
              onHoldMedia.filter {
                case _: TvShowId => categories.contains("tv_show")
                case (_: TvShowId, _: SeasonNumber) => categories.contains("season")
                case videogameId: VideogameId => categories.contains("videogame")
                case bookId: BookId => categories.contains("book")
              }
            case None =>  onHoldMedia

          Right(filteredOnHoldMedia)

        case None if userId.value <= 0 =>
          Left(BadRequest("Invalid user ID"))

        case None =>
          Left(NotFound(s"User with ID ${userId.value} not found"))
      }
    }.handleError {
      case ex: Exception =>
        Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val addOnHoldTvShow:
    ((UserId, TvShowId)) => IO[Either[UserError, List[TvShowId | (TvShowId, SeasonNumber) | VideogameId | BookId]]] =
    (userId, tvShowId) => addOnHoldMedia(userId, tvShowId)

  val addOnHoldSeason:
    ((UserId, TvShowId, SeasonNumber)) => IO[Either[UserError, List[TvShowId | (TvShowId, SeasonNumber) | VideogameId | BookId]]] =
    (userId, tvShowId, seasonNumber) => addOnHoldMedia(userId, (tvShowId, seasonNumber))

  val addOnHoldVideogame:
    ((UserId, VideogameId)) => IO[Either[UserError, List[TvShowId | (TvShowId, SeasonNumber) | VideogameId | BookId]]] =
    (userId, videogameId) => addOnHoldMedia(userId, videogameId)

  val addOnHoldBook:
    ((UserId, BookId)) => IO[Either[UserError, List[TvShowId | (TvShowId, SeasonNumber) | VideogameId | BookId]]] =
    (userId, bookId) => addOnHoldMedia(userId, bookId)

  val deleteOnHoldTvShow: ((UserId,  TvShowId)) => IO[Either[UserError, Unit]] =
    (userId, tvShowId) => deleteOnHoldMedia(userId, tvShowId)

  val deleteOnHoldSeason: ((UserId, TvShowId, SeasonNumber)) => IO[Either[UserError, Unit]] =
    (userId, tvShowId, seasonNumber) => deleteOnHoldMedia(userId, (tvShowId, seasonNumber))

  val deleteOnHoldVideogame: ((UserId, VideogameId)) => IO[Either[UserError, Unit]] =
    (userId, videogameId) => deleteOnHoldMedia(userId, videogameId)

  val deleteOnHoldBook: ((UserId, BookId)) => IO[Either[UserError, Unit]] =
    (userId, bookId) => deleteOnHoldMedia(userId, bookId)

  private val addOnHoldMedia:
    ((UserId, TvShowId | (TvShowId, SeasonNumber) | VideogameId | BookId)) => IO[Either[UserError, List[TvShowId | (TvShowId, SeasonNumber) | VideogameId | BookId]]] =
    (userId, mediaId) => IO {
      UserRepository.get(userId) match
        case None if userId.value <= 0 =>
          Left(BadRequest("Invalid user ID"))

        case None =>
          Left(NotFound(s"User with ID ${userId.value} not found"))

        case Some(user) =>
          if user.onHold.contains(mediaId) then
            Left(Conflict("Media already on hold"))
          else
            val updatedOnHoldMedia = mediaId match
              case tvShowId: TvShowId =>
                if tvShowId.value <= 0 then BadRequest("Invalid TV show ID")
                else
                  tvShowId :: user.onHold

              case (tvShowId: TvShowId, seasonNumber: SeasonNumber) =>
                if tvShowId.value <= 0 then BadRequest("Invalid TV show ID")
                else if seasonNumber.value <= 0 then BadRequest("Invalid season number")
                else
                  (tvShowId, seasonNumber) :: user.onHold

              case videogameId: VideogameId =>
                if videogameId.value <= 0 then BadRequest("Invalid videogame ID")
                else
                  videogameId :: user.onHold

              case bookId: BookId =>
                if bookId.value == "" then BadRequest("Invalid book ID")
                else
                  bookId :: user.onHold

            updatedOnHoldMedia match
              case badRequest: BadRequest =>
                Left(badRequest)

              case list: List[TvShowId | (TvShowId, SeasonNumber) | VideogameId | BookId] =>
                val updatedUser = user.copy(
                  onHold = list
                )
                UserRepository.put(userId, updatedUser)
                Right(updatedUser.onHold)

              case _ =>
                Left(Unknown(500, "An unexpected error occurred"))

    }.handleError {
      case ex: Exception =>
        Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  private val deleteOnHoldMedia: ((UserId, TvShowId | (TvShowId, SeasonNumber) | VideogameId | BookId)) => IO[Either[UserError, Unit]] =
    (userId, mediaId) => IO {
      UserRepository.get(userId) match
        case None if userId.value <= 0 =>
          Left(BadRequest("Invalid user ID"))

        case None =>
          Left(NotFound(s"User with ID ${userId.value} not found"))

        case Some(user) =>
          if !user.onHold.contains(mediaId) then
            Left(BadRequest("Media not on hold yet"))
          else
            val updatedOnHoldMedia = mediaId match
              case tvShowId: TvShowId =>
                if tvShowId.value <= 0 then BadRequest("Invalid TV show ID")
                else
                  user.onHold.filterNot(_ == tvShowId)

              case (tvShowId: TvShowId, seasonNumber: SeasonNumber) =>
                if tvShowId.value <= 0 then BadRequest("Invalid TV show ID")
                else if seasonNumber.value <= 0 then BadRequest("Invalid season number")
                else
                  user.onHold.filterNot(_ == (tvShowId, seasonNumber))

              case videogameId: VideogameId =>
                if videogameId.value <= 0 then BadRequest("Invalid videogame ID")
                else
                  user.onHold.filterNot(_ == videogameId)

              case bookId: BookId =>
                if bookId.value == "" then BadRequest("Invalid book ID")
                else
                  user.onHold.filterNot(_ == bookId)

            updatedOnHoldMedia match
              case badRequest: BadRequest =>
                Left(badRequest)

              case list: List[TvShowId | (TvShowId, SeasonNumber) | VideogameId | BookId] =>
                val updatedUser = user.copy(
                  onHold = list
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
