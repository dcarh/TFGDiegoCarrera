package server.logics.user.media

import cats.effect.IO
import dummies.repositories.UserRepository
import modelClasses.errors.UserError.*
import modelClasses.ids.User.UserId
import modelClasses.ids.Media.{BookId, EpisodeNumber, MovieId, SeasonNumber, TvShowId, VideogameId}

import server.logics.commonFunctions.CommonFunctions

object UserDroppedMediaLogics {

  // TODO: Implementar funcionalidad de sortByOption (en caso de seguir adelante con ello)

  val getAllDroppedMedia: ((UserId, Option[String], Option[List[String]])) => IO[Either[UserError, List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    (userId, sortByOption, categoryOption) => IO {
      CommonFunctions.getUser(userId) match
        case Left(error) => Left(error)
        case Right(user) =>
          val droppedMedia = user.dropped

          val filteredDroppedMedia = categoryOption match
            case Some(categories) =>
              droppedMedia.filter {
                case _: MovieId => categories.contains("movie")
                case _: TvShowId => categories.contains("tv_show")
                case (_: TvShowId, _: SeasonNumber) => categories.contains("season")
                case (_: TvShowId, _: SeasonNumber, _: EpisodeNumber) => categories.contains("episode")
                case videogameId: VideogameId => categories.contains("videogame")
                case bookId: BookId => categories.contains("book")
              }
            case None => droppedMedia

          Right(filteredDroppedMedia)
          
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val addDroppedMovie:
    ((UserId, MovieId)) => IO[Either[UserError, List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    (userId, movieId) => addDroppedMedia(userId, movieId)

  val addDroppedTvShow:
    ((UserId, TvShowId)) => IO[Either[UserError, List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    (userId, tvShowId) => addDroppedMedia(userId, tvShowId)

  val addDroppedSeason:
    ((UserId, TvShowId, SeasonNumber)) => IO[Either[UserError, List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    (userId, tvShowId, seasonNumber) => addDroppedMedia(userId, (tvShowId, seasonNumber))

  val addDroppedEpisode:
    ((UserId, TvShowId, SeasonNumber, EpisodeNumber)) => IO[Either[UserError, List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    (userId, tvShowId, seasonNumber, episodeNumber) => addDroppedMedia(userId, (tvShowId, seasonNumber, episodeNumber))

  val addDroppedVideogame:
    ((UserId, VideogameId)) => IO[Either[UserError, List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    (userId, videogameId) => addDroppedMedia(userId, videogameId)

  val addDroppedBook:
    ((UserId, BookId)) => IO[Either[UserError, List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    (userId, bookId) => addDroppedMedia(userId, bookId)

  val deleteDroppedMovie: ((UserId, MovieId)) => IO[Either[UserError, Unit]] =
    (userId, movieId) => deleteDroppedMedia(userId, movieId)

  val deleteDroppedTvShow: ((UserId,  TvShowId)) => IO[Either[UserError, Unit]] =
    (userId, tvShowId) => deleteDroppedMedia(userId, tvShowId)

  val deleteDroppedSeason: ((UserId, TvShowId, SeasonNumber)) => IO[Either[UserError, Unit]] =
    (userId, tvShowId, seasonNumber) => deleteDroppedMedia(userId, (tvShowId, seasonNumber))

  val deleteDroppedEpisode: ((UserId, TvShowId, SeasonNumber, EpisodeNumber)) => IO[Either[UserError, Unit]] =
    (userId, tvShowId, seasonNumber, episodeNumber) => deleteDroppedMedia(userId, (tvShowId, seasonNumber, episodeNumber))

  val deleteDroppedVideogame: ((UserId, VideogameId)) => IO[Either[UserError, Unit]] =
    (userId, videogameId) => deleteDroppedMedia(userId, videogameId)

  val deleteDroppedBook: ((UserId, BookId)) => IO[Either[UserError, Unit]] =
    (userId, bookId) => deleteDroppedMedia(userId, bookId)

  private val addDroppedMedia:
    ((UserId, MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId)) => IO[Either[UserError, List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    (userId, mediaId) => IO {
      CommonFunctions.getUser(userId) match
        case Left(error) => Left(error)
        case Right(user) =>
          if user.dropped.contains(mediaId) then
            Left(Conflict("Media already dropped"))
          else
            val updatedDroppedMedia = mediaId match
              case movieId: MovieId =>
                if movieId.value <= 0 then Left(BadRequest("Invalid movie ID"))
                else
                  Right(movieId :: user.dropped)

              case tvShowId: TvShowId =>
                if tvShowId.value <= 0 then Left(BadRequest("Invalid TV show ID"))
                else
                  Right(tvShowId :: user.dropped)

              case (tvShowId: TvShowId, seasonNumber: SeasonNumber) =>
                if tvShowId.value <= 0 then Left(BadRequest("Invalid TV show ID"))
                else if seasonNumber.value <= 0 then Left(BadRequest("Invalid season number"))
                else
                  Right((tvShowId, seasonNumber) :: user.dropped)

              case (tvShowId: TvShowId, seasonNumber: SeasonNumber, episodeNumber: EpisodeNumber) =>
                if tvShowId.value <= 0 then Left(BadRequest("Invalid TV show ID"))
                else if seasonNumber.value <= 0 then Left(BadRequest("Invalid season number"))
                else if episodeNumber.value <= 0 then Left(BadRequest("Invalid episode number"))
                else
                  Right((tvShowId, seasonNumber, episodeNumber) :: user.dropped)

              case videogameId: VideogameId =>
                if videogameId.value <= 0 then Left(BadRequest("Invalid videogame ID"))
                else
                  Right(videogameId :: user.dropped)

              case bookId: BookId =>
                if bookId.value == "" then Left(BadRequest("Invalid book ID"))
                else
                  Right(bookId :: user.dropped)

            updatedDroppedMedia match
              case Left(error) => Left(error)
              case Right(list) =>
                val updatedUser = user.copy(
                  dropped = list
                )
                UserRepository.put(userId, updatedUser)
                Right(updatedUser.dropped)

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  private val deleteDroppedMedia: ((UserId, MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId)) => IO[Either[UserError, Unit]] =
    (userId, mediaId) => IO {
      CommonFunctions.getUser(userId) match
        case Left(error) => Left(error)
        case Right(user) =>
          if !user.dropped.contains(mediaId) then
            Left(BadRequest("Media not dropped yet"))
          else
            val updatedDroppedMedia = mediaId match
              case movieId: MovieId =>
                if movieId.value <= 0 then Left(BadRequest("Invalid movie ID"))
                else
                  Right(user.dropped.filterNot(_ == movieId))

              case tvShowId: TvShowId =>
                if tvShowId.value <= 0 then Left(BadRequest("Invalid TV show ID"))
                else
                  Right(user.dropped.filterNot(_ == tvShowId))

              case (tvShowId: TvShowId, seasonNumber: SeasonNumber) =>
                if tvShowId.value <= 0 then Left(BadRequest("Invalid TV show ID"))
                else if seasonNumber.value <= 0 then Left(BadRequest("Invalid season number"))
                else
                  Right(user.dropped.filterNot(_ == (tvShowId, seasonNumber)))

              case (tvShowId: TvShowId, seasonNumber: SeasonNumber, episodeNumber: EpisodeNumber) =>
                if tvShowId.value <= 0 then Left(BadRequest("Invalid TV show ID"))
                else if seasonNumber.value <= 0 then Left(BadRequest("Invalid season number"))
                else if episodeNumber.value <= 0 then Left(BadRequest("Invalid episode number"))
                else
                  Right(user.dropped.filterNot(_ == (tvShowId, seasonNumber, episodeNumber)))

              case videogameId: VideogameId =>
                if videogameId.value <= 0 then Left(BadRequest("Invalid videogame ID"))
                else
                  Right(user.dropped.filterNot(_ == videogameId))

              case bookId: BookId =>
                if bookId.value == "" then Left(BadRequest("Invalid book ID"))
                else
                  Right(user.dropped.filterNot(_ == bookId))

            updatedDroppedMedia match
              case Left(error) => Left(error)
              case Right(list) =>
                val updatedUser = user.copy(
                  dropped = list
                )
                UserRepository.put(userId, updatedUser)
                Right(())

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

}
