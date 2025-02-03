package server.logics.user.media

import cats.effect.IO
import dummies.repositories.UserRepository
import modelClasses.errors.UserError.*
import modelClasses.ids.User.UserId
import modelClasses.ids.Media.{BookId, EpisodeNumber, MovieId, SeasonNumber, TvShowId, VideogameId}

import server.logics.commonFunctions.CommonFunctions

object UserCompletedMediaLogics {
  
  // TODO: Implementar funcionalidad de sortByOption (en caso de seguir adelante con ello)

  val getAllCompletedMedia: ((UserId, Option[String], Option[List[String]])) => IO[Either[UserError, List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    (userId, sortByOption, categoryOption) => IO {
      CommonFunctions.getUser(userId) match
        case Left(error) => Left(error)
        case Right(user) =>
          val completedMedia = user.completed
          
          val filteredCompletedMedia = categoryOption match
            case Some(categories) =>
              completedMedia.filter {
                case _: MovieId => categories.contains("movie")
                case _: TvShowId => categories.contains("tv_show")
                case (_: TvShowId, _: SeasonNumber) => categories.contains("season")
                case (_: TvShowId, _: SeasonNumber, _: EpisodeNumber) => categories.contains("episode")
                case videogameId: VideogameId => categories.contains("videogame")
                case bookId: BookId => categories.contains("book")
              }
            case None => completedMedia

          Right(filteredCompletedMedia)
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val addCompletedMovie:
    ((UserId, MovieId)) => IO[Either[UserError, List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    (userId, movieId) => addCompletedMedia(userId, movieId)

  val addCompletedTvShow:
    ((UserId, TvShowId)) => IO[Either[UserError, List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    (userId, tvShowId) => addCompletedMedia(userId, tvShowId)

  val addCompletedSeason:
    ((UserId, TvShowId, SeasonNumber)) => IO[Either[UserError, List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    (userId, tvShowId, seasonNumber) => addCompletedMedia(userId, (tvShowId, seasonNumber))

  val addCompletedEpisode:
    ((UserId, TvShowId, SeasonNumber, EpisodeNumber)) => IO[Either[UserError, List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    (userId, tvShowId, seasonNumber, episodeNumber) => addCompletedMedia(userId, (tvShowId, seasonNumber, episodeNumber))

  val addCompletedVideogame:
    ((UserId, VideogameId)) => IO[Either[UserError, List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    (userId, videogameId) => addCompletedMedia(userId, videogameId)

  val addCompletedBook: 
    ((UserId, BookId)) => IO[Either[UserError, List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    (userId, bookId) => addCompletedMedia(userId, bookId)

  val deleteCompletedMovie: ((UserId, MovieId)) => IO[Either[UserError, Unit]] =
    (userId, movieId) => deleteCompletedMedia(userId, movieId)

  val deleteCompletedTvShow: ((UserId,  TvShowId)) => IO[Either[UserError, Unit]] =
    (userId, tvShowId) => deleteCompletedMedia(userId, tvShowId)

  val deleteCompletedSeason: ((UserId, TvShowId, SeasonNumber)) => IO[Either[UserError, Unit]] =
    (userId, tvShowId, seasonNumber) => deleteCompletedMedia(userId, (tvShowId, seasonNumber))

  val deleteCompletedEpisode: ((UserId, TvShowId, SeasonNumber, EpisodeNumber)) => IO[Either[UserError, Unit]] =
    (userId, tvShowId, seasonNumber, episodeNumber) => deleteCompletedMedia(userId, (tvShowId, seasonNumber, episodeNumber))

  val deleteCompletedVideogame: ((UserId, VideogameId)) => IO[Either[UserError, Unit]] =
    (userId, videogameId) => deleteCompletedMedia(userId, videogameId)

  val deleteCompletedBook: ((UserId, BookId)) => IO[Either[UserError, Unit]] =
    (userId, bookId) => deleteCompletedMedia(userId, bookId)

  private val addCompletedMedia:
    ((UserId, MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId)) => IO[Either[UserError, List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    (userId, mediaId) => IO {
      CommonFunctions.getUser(userId) match
        case Left(error) => Left(error)
        case Right(user) =>
          if user.completed.contains(mediaId) then
            Left(Conflict("Media already completed"))
          else
            val updatedCompletedMedia = mediaId match
              case movieId: MovieId =>
                if movieId.value <= 0 then Left(BadRequest("Invalid movie ID"))
                else
                  Right(movieId :: user.completed)

              case tvShowId: TvShowId =>
                if tvShowId.value <= 0 then Left(BadRequest("Invalid TV show ID"))
                else
                  Right(tvShowId :: user.completed)

              case (tvShowId: TvShowId, seasonNumber: SeasonNumber) =>
                if tvShowId.value <= 0 then Left(BadRequest("Invalid TV show ID"))
                else if seasonNumber.value <= 0 then Left(BadRequest("Invalid season number"))
                else
                  Right((tvShowId, seasonNumber) :: user.completed)

              case (tvShowId: TvShowId, seasonNumber: SeasonNumber, episodeNumber: EpisodeNumber) =>
                if tvShowId.value <= 0 then Left(BadRequest("Invalid TV show ID"))
                else if seasonNumber.value <= 0 then Left(BadRequest("Invalid season number"))
                else if episodeNumber.value <= 0 then Left(BadRequest("Invalid episode number"))
                else
                  Right((tvShowId, seasonNumber, episodeNumber) :: user.completed)

              case videogameId: VideogameId =>
                if videogameId.value <= 0 then Left(BadRequest("Invalid videogame ID"))
                else
                  Right(videogameId :: user.completed)

              case bookId: BookId =>
                if bookId.value == "" then Left(BadRequest("Invalid book ID"))
                else
                  Right(bookId :: user.completed)

            updatedCompletedMedia match
              case Left(error) => Left(error)
              case Right(list) =>
                val updatedUser = user.copy(
                  completed = list
                )
                UserRepository.put(userId, updatedUser)
                Right(updatedUser.completed)

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  private val deleteCompletedMedia: ((UserId, MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId)) => IO[Either[UserError, Unit]] =
    (userId, mediaId) => IO {
      CommonFunctions.getUser(userId) match
        case Left(error) => Left(error)
        case Right(user) =>
          if  !user.completed.contains(mediaId) then
            Left(BadRequest("Media not completed yet"))
          else
            val updatedCompletedMedia = mediaId match
              case movieId: MovieId =>
                if movieId.value <= 0 then Left(BadRequest("Invalid movie ID"))
                else
                  Right(user.completed.filterNot(_ == movieId))

              case tvShowId: TvShowId =>
                if tvShowId.value <= 0 then Left(BadRequest("Invalid TV show ID"))
                else
                  Right(user.completed.filterNot(_ == tvShowId))

              case (tvShowId: TvShowId, seasonNumber: SeasonNumber) =>
                if tvShowId.value <= 0 then Left(BadRequest("Invalid TV show ID"))
                else if seasonNumber.value <= 0 then Left(BadRequest("Invalid season number"))
                else
                  Right(user.completed.filterNot(_ == (tvShowId, seasonNumber)))

              case (tvShowId: TvShowId, seasonNumber: SeasonNumber, episodeNumber: EpisodeNumber) =>
                if tvShowId.value <= 0 then Left(BadRequest("Invalid TV show ID"))
                else if seasonNumber.value <= 0 then Left(BadRequest("Invalid season number"))
                else if episodeNumber.value <= 0 then Left(BadRequest("Invalid episode number"))
                else
                  Right(user.completed.filterNot(_ == (tvShowId, seasonNumber, episodeNumber)))

              case videogameId: VideogameId =>
                if videogameId.value <= 0 then Left(BadRequest("Invalid videogame ID"))
                else
                  Right(user.completed.filterNot(_ == videogameId))

              case bookId: BookId =>
                if bookId.value == "" then Left(BadRequest("Invalid book ID"))
                else
                  Right(user.completed.filterNot(_ == bookId))

            updatedCompletedMedia match
              case Left(error) => Left(error)
              case Right(list) =>
                val updatedUser = user.copy(
                  completed = list
                )
                UserRepository.put(userId, updatedUser)
                Right(())

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

}
