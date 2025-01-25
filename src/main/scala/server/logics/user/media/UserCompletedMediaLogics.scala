package server.logics.user.media

import cats.effect.IO
import dummies.repositories.UserRepository
import modelClasses.errors.UserError.*
import modelClasses.ids.User.UserId
import modelClasses.ids.Media.{BookId, EpisodeNumber, MovieId, SeasonNumber, TvShowId, VideogameId}

object UserCompletedMediaLogics {
  
  // TODO: Implementar funcionalidad de sortByOption (en caso de seguir adelante con ello)

  val getAllCompletedMedia: ((UserId, Option[String], Option[List[String]])) => IO[Either[UserError, List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]]] =
    (userId, sortByOption, categoryOption) => IO {
      UserRepository.get(userId) match 
        case Some(user) =>
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
            case None =>  completedMedia

          Right(filteredCompletedMedia)

        case None if userId.value <= 0 =>
          Left(BadRequest("Invalid user ID"))

        case None =>
          Left(NotFound(s"User with ID ${userId.value} not found"))
      
    }.handleError {
      case ex: Exception =>
        Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
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
      UserRepository.get(userId) match
        case None if userId.value <= 0 =>
          Left(BadRequest("Invalid user ID"))

        case None =>
          Left(NotFound(s"User with ID ${userId.value} not found"))

        case Some(user) =>
          if user.completed.contains(mediaId) then
            Left(Conflict("Media already completed"))
          else
            val updatedCompletedMedia = mediaId match
              case movieId: MovieId =>
                if movieId.value <= 0 then BadRequest("Invalid movie ID")
                else
                  movieId :: user.completed

              case tvShowId: TvShowId =>
                if tvShowId.value <= 0 then BadRequest("Invalid TV show ID")
                else
                  tvShowId :: user.completed

              case (tvShowId: TvShowId, seasonNumber: SeasonNumber) =>
                if tvShowId.value <= 0 then BadRequest("Invalid TV show ID")
                else if seasonNumber.value <= 0 then BadRequest("Invalid season number")
                else
                  (tvShowId, seasonNumber) :: user.completed

              case (tvShowId: TvShowId, seasonNumber: SeasonNumber, episodeNumber: EpisodeNumber) =>
                if tvShowId.value <= 0 then BadRequest("Invalid TV show ID")
                else if seasonNumber.value <= 0 then BadRequest("Invalid season number")
                else if episodeNumber.value <= 0 then BadRequest("Invalid episode number")
                else
                  (tvShowId, seasonNumber, episodeNumber) :: user.completed

              case videogameId: VideogameId =>
                if videogameId.value <= 0 then BadRequest("Invalid videogame ID")
                else
                  videogameId :: user.completed

              case bookId: BookId =>
                if bookId.value == "" then BadRequest("Invalid book ID")
                else
                  bookId :: user.completed

            updatedCompletedMedia match
              case badRequest: BadRequest =>
                Left(badRequest)

              case list: List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId] =>
                val updatedUser = user.copy(
                  completed = list
                )
                UserRepository.put(userId, updatedUser)
                Right(updatedUser.completed)

              case _ =>
                Left(Unknown(500, "An unexpected error occurred"))

    }.handleError {
      case ex: Exception =>
        Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  private val deleteCompletedMedia: ((UserId, MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId)) => IO[Either[UserError, Unit]] =
    (userId, mediaId) => IO {
      UserRepository.get(userId) match
        case None if userId.value <= 0 =>
          Left(BadRequest("Invalid user ID"))

        case None =>
          Left(NotFound(s"User with ID ${userId.value} not found"))

        case Some(user) =>
          if  !user.completed.contains(mediaId) then
            Left(BadRequest("Media not completed yet"))
          else
            val updatedCompletedMedia = mediaId match
              case movieId: MovieId =>
                if movieId.value <= 0 then BadRequest("Invalid movie ID")
                else
                  user.completed.filterNot(_ == movieId)

              case tvShowId: TvShowId =>
                if tvShowId.value <= 0 then BadRequest("Invalid TV show ID")
                else
                  user.completed.filterNot(_ == tvShowId)

              case (tvShowId: TvShowId, seasonNumber: SeasonNumber) =>
                if tvShowId.value <= 0 then BadRequest("Invalid TV show ID")
                else if seasonNumber.value <= 0 then BadRequest("Invalid season number")
                else
                  user.completed.filterNot(_ == (tvShowId, seasonNumber))

              case (tvShowId: TvShowId, seasonNumber: SeasonNumber, episodeNumber: EpisodeNumber) =>
                if tvShowId.value <= 0 then BadRequest("Invalid TV show ID")
                else if seasonNumber.value <= 0 then BadRequest("Invalid season number")
                else if episodeNumber.value <= 0 then BadRequest("Invalid episode number")
                else
                  user.completed.filterNot(_ == (tvShowId, seasonNumber, episodeNumber))

              case videogameId: VideogameId =>
                if videogameId.value <= 0 then BadRequest("Invalid videogame ID")
                else
                  user.completed.filterNot(_ == videogameId)

              case bookId: BookId =>
                if bookId.value == "" then BadRequest("Invalid book ID")
                else
                  user.completed.filterNot(_ == bookId)

            updatedCompletedMedia match
              case badRequest: BadRequest =>
                Left(badRequest)

              case list: List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId] =>
                val updatedUser = user.copy(
                  completed = list
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
