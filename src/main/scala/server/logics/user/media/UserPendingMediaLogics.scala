package server.logics.user.media

import cats.effect.IO
import dummies.repositories.UserRepository
import modelClasses.errors.UserError.*
import modelClasses.ids.User.UserId
import modelClasses.ids.Media.{BookId, EpisodeNumber, MovieId, SeasonNumber, TvShowId, VideogameId}

import server.logics.commonFunctions.CommonFunctions

object UserPendingMediaLogics {

  // TODO: Implementar funcionalidad de sortByOption (en caso de seguir adelante con ello)

  val getAllPendingMedia: ((UserId, Option[String], Option[List[String]])) => IO[Either[UserError, List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    (userId, sortByOption, categoryOption) => IO {
      CommonFunctions.getUser(userId) match
        case Left(error) => Left(error)
        case Right(user) =>
          val pendingMedia = user.pending

          val filteredPendingMedia = categoryOption match
            case Some(categories) =>
              pendingMedia.filter {
                case _: MovieId => categories.contains("movie")
                case _: TvShowId => categories.contains("tv_show")
                case (_: TvShowId, _: SeasonNumber) => categories.contains("season")
                case (_: TvShowId, _: SeasonNumber, _: EpisodeNumber) => categories.contains("episode")
                case videogameId: VideogameId => categories.contains("videogame")
                case bookId: BookId => categories.contains("book")
              }
            case None =>  pendingMedia

          Right(filteredPendingMedia)
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val addPendingMovie:
    ((UserId, MovieId)) => IO[Either[UserError, List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    (userId, movieId) => addPendingMedia(userId, movieId)

  val addPendingTvShow:
    ((UserId, TvShowId)) => IO[Either[UserError, List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    (userId, tvShowId) => addPendingMedia(userId, tvShowId)

  val addPendingSeason:
    ((UserId, TvShowId, SeasonNumber)) => IO[Either[UserError, List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    (userId, tvShowId, seasonNumber) => addPendingMedia(userId, (tvShowId, seasonNumber))

  val addPendingEpisode:
    ((UserId, TvShowId, SeasonNumber, EpisodeNumber)) => IO[Either[UserError, List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    (userId, tvShowId, seasonNumber, episodeNumber) => addPendingMedia(userId, (tvShowId, seasonNumber, episodeNumber))

  val addPendingVideogame:
    ((UserId, VideogameId)) => IO[Either[UserError, List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    (userId, videogameId) => addPendingMedia(userId, videogameId)

  val addPendingBook:
    ((UserId, BookId)) => IO[Either[UserError, List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    (userId, bookId) => addPendingMedia(userId, bookId)

  val deletePendingMovie: ((UserId, MovieId)) => IO[Either[UserError, Unit]] =
    (userId, movieId) => deletePendingMedia(userId, movieId)

  val deletePendingTvShow: ((UserId,  TvShowId)) => IO[Either[UserError, Unit]] =
    (userId, tvShowId) => deletePendingMedia(userId, tvShowId)

  val deletePendingSeason: ((UserId, TvShowId, SeasonNumber)) => IO[Either[UserError, Unit]] =
    (userId, tvShowId, seasonNumber) => deletePendingMedia(userId, (tvShowId, seasonNumber))

  val deletePendingEpisode: ((UserId, TvShowId, SeasonNumber, EpisodeNumber)) => IO[Either[UserError, Unit]] =
    (userId, tvShowId, seasonNumber, episodeNumber) => deletePendingMedia(userId, (tvShowId, seasonNumber, episodeNumber))

  val deletePendingVideogame: ((UserId, VideogameId)) => IO[Either[UserError, Unit]] =
    (userId, videogameId) => deletePendingMedia(userId, videogameId)

  val deletePendingBook: ((UserId, BookId)) => IO[Either[UserError, Unit]] =
    (userId, bookId) => deletePendingMedia(userId, bookId)

  private val addPendingMedia:
    ((UserId, MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId)) => IO[Either[UserError, List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
      (userId, mediaId) => IO {
        CommonFunctions.getUser(userId) match
          case Left(error) => Left(error)
          case Right(user) =>
            if user.pending.contains(mediaId) then
              Left(Conflict("Media already pending"))
            else
              val updatedPendingMedia = mediaId match
                case movieId: MovieId =>
                  if movieId.value <= 0 then Left(BadRequest("Invalid movie ID"))
                  else
                    Right(movieId :: user.pending)
  
                case tvShowId: TvShowId =>
                  if tvShowId.value <= 0 then Left(BadRequest("Invalid TV show ID"))
                  else
                    Right(tvShowId :: user.pending)
  
                case (tvShowId: TvShowId, seasonNumber: SeasonNumber) =>
                  if tvShowId.value <= 0 then Left(BadRequest("Invalid TV show ID"))
                  else if seasonNumber.value <= 0 then Left(BadRequest("Invalid season number"))
                  else
                    Right((tvShowId, seasonNumber) :: user.pending)
  
                case (tvShowId: TvShowId, seasonNumber: SeasonNumber, episodeNumber: EpisodeNumber) =>
                  if tvShowId.value <= 0 then Left(BadRequest("Invalid TV show ID"))
                  else if seasonNumber.value <= 0 then Left(BadRequest("Invalid season number"))
                  else if episodeNumber.value <= 0 then Left(BadRequest("Invalid episode number"))
                  else
                    Right((tvShowId, seasonNumber, episodeNumber) :: user.pending)
  
                case videogameId: VideogameId =>
                  if videogameId.value <= 0 then Left(BadRequest("Invalid videogame ID"))
                  else
                    Right(videogameId :: user.pending)
  
                case bookId: BookId =>
                  if bookId.value == "" then Left(BadRequest("Invalid book ID"))
                  else
                    Right(bookId :: user.pending)
  
              updatedPendingMedia match
                case Left(error) => Left(error)
                case Right(list) =>
                  val updatedUser = user.copy(
                    pending = list
                  )
                  UserRepository.put(userId, updatedUser)
                  Right(updatedUser.pending)
  
      }.handleError {
        case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
      }

  private val deletePendingMedia: ((UserId, MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId)) => IO[Either[UserError, Unit]] =
    (userId, mediaId) => IO {
      CommonFunctions.getUser(userId) match
        case Left(error) => Left(error)
        case Right(user) =>
          if !user.pending.contains(mediaId) then
            Left(BadRequest("Media not pending yet"))
          else
            val updatedPendingMedia = mediaId match
              case movieId: MovieId =>
                if movieId.value <= 0 then Left(BadRequest("Invalid movie ID"))
                else
                  Right(user.pending.filterNot(_ == movieId))

              case tvShowId: TvShowId =>
                if tvShowId.value <= 0 then Left(BadRequest("Invalid TV show ID"))
                else
                  Right(user.pending.filterNot(_ == tvShowId))

              case (tvShowId: TvShowId, seasonNumber: SeasonNumber) =>
                if tvShowId.value <= 0 then Left(BadRequest("Invalid TV show ID"))
                else if seasonNumber.value <= 0 then Left(BadRequest("Invalid season number"))
                else
                  Right(user.pending.filterNot(_ == (tvShowId, seasonNumber)))

              case (tvShowId: TvShowId, seasonNumber: SeasonNumber, episodeNumber: EpisodeNumber) =>
                if tvShowId.value <= 0 then Left(BadRequest("Invalid TV show ID"))
                else if seasonNumber.value <= 0 then Left(BadRequest("Invalid season number"))
                else if episodeNumber.value <= 0 then Left(BadRequest("Invalid episode number"))
                else
                  Right(user.pending.filterNot(_ == (tvShowId, seasonNumber, episodeNumber)))

              case videogameId: VideogameId =>
                if videogameId.value <= 0 then Left(BadRequest("Invalid videogame ID"))
                else
                  Right(user.pending.filterNot(_ == videogameId))

              case bookId: BookId =>
                if bookId.value == "" then Left(BadRequest("Invalid book ID"))
                else
                  Right(user.pending.filterNot(_ == bookId))

            updatedPendingMedia match
              case Left(error) => Left(error)
              case Right(list) =>
                val updatedUser = user.copy(
                  pending = list
                )
                UserRepository.put(userId, updatedUser)
                Right(())
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }
}
