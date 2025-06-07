package server.logics.user.media

import cats.effect.IO
import memory.repositories.UserRepository
import domain.errors.UserError.*
import domain.ids.User.UserId
import domain.ids.Media.{BookId, MovieId, TvEpisodeNumber, TvSeasonNumber, TvShowId, VideogameId}
import domain.ids.Social.EntryId
import server.logics.commonFunctions.CommonFunctions

object UserMediaLogics {
  
  val getAllMedia: ((UserId, String, Option[List[String]])) => IO[
    Either[
      UserError,
      List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId]
    ]
  ] =
    (userId, field, categoryOption) => IO.pure {
      getAllMedia(userId, field, categoryOption) match
        case Left(error)        => Left(error)
        case Right(Left(list))  => Right(list)
        case Right(Right(list)) => Right(list)
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val addMovie:
    ((UserId, String, MovieId)) => IO[Either[UserError, List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId]]] =
    (userId, field, movieId) => IO.pure {
      addMedia(userId, field, movieId) match
        case Left(error)       => Left(error)
        case Right(Left(list)) => Right(list)
        case Right(Right(_))   => Left(Unknown(500, "Internal server error"))
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val addTvShow:
    ((UserId, String, TvShowId)) => IO[Either[UserError, List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId]]] =
    (userId, field, tvShowId) => IO.pure {
      addMedia(userId, field, tvShowId) match
        case Left(error)        => Left(error)
        case Right(Left(list))  => Right(list)
        case Right(Right(list)) => Right(list)
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val addTvSeason:
    ((UserId, String, TvShowId, TvSeasonNumber)) => IO[Either[UserError, List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId]]] =
    (userId, field, tvShowId, seasonNumber) => IO.pure {
      addMedia(userId, field, (tvShowId, seasonNumber)) match
        case Left(error)        => Left(error)
        case Right(Left(list))  => Right(list)
        case Right(Right(list)) => Right(list)
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val addTvEpisode:
    ((UserId, String, TvShowId, TvSeasonNumber, TvEpisodeNumber)) => IO[Either[UserError, List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId]]] =
    (userId, field, tvShowId, seasonNumber, episodeNumber) => IO.pure {
      addMedia(userId, field, (tvShowId, seasonNumber, episodeNumber)) match
        case Left(error)       => Left(error)
        case Right(Left(list)) => Right(list)
        case Right(Right(_))   => Left(Unknown(500, "Internal server error"))
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val addVideogame:
    ((UserId, String, VideogameId)) => IO[Either[UserError, List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId]]] =
    (userId, field, videogameId) => IO.pure {
      addMedia(userId, field, videogameId) match
        case Left(error)        => Left(error)
        case Right(Left(list))  => Right(list)
        case Right(Right(list)) => Right(list)
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val addBook:
    ((UserId, String, BookId)) => IO[Either[UserError, List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId]]] =
    (userId, field, bookId) => IO.pure {
      addMedia(userId, field, bookId) match
        case Left(error)        => Left(error)
        case Right(Left(list))  => Right(list)
        case Right(Right(list)) => Right(list)
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val deleteMovie: ((UserId, String, MovieId)) => IO[Either[UserError, Unit]] =
    (userId, field, movieId) => IO.pure {
      deleteMedia(userId, field, movieId)
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val deleteTvShow: ((UserId, String, TvShowId)) => IO[Either[UserError, Unit]] =
    (userId, field, tvShowId) => IO.pure {
      deleteMedia(userId, field, tvShowId)
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val deleteTvSeason: ((UserId, String, TvShowId, TvSeasonNumber)) => IO[Either[UserError, Unit]] =
    (userId, field, tvShowId, seasonNumber) => IO.pure {
      deleteMedia(userId, field, (tvShowId, seasonNumber))
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val deleteTvEpisode: ((UserId, String, TvShowId, TvSeasonNumber, TvEpisodeNumber)) => IO[Either[UserError, Unit]] =
    (userId, field, tvShowId, seasonNumber, episodeNumber) => IO.pure {
      deleteMedia(userId, field, (tvShowId, seasonNumber, episodeNumber))
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val deleteVideogame: ((UserId, String, VideogameId)) => IO[Either[UserError, Unit]] =
    (userId, field, videogameId) => IO.pure {
      deleteMedia(userId, field, videogameId)
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val deleteBook: ((UserId, String, BookId)) => IO[Either[UserError, Unit]] =
    (userId, field, bookId) => IO.pure {
      deleteMedia(userId, field, bookId)
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }


  private def getAllMedia(userId: UserId, field: String, categoryOption:Option[List[String]]):
  Either[
    UserError,
    Either[
      List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId],
      List[TvShowId | (TvShowId, TvSeasonNumber) | VideogameId | BookId]
    ]
  ] =
    CommonFunctions.getUser(userId) match
      case Left(error) => Left(error)
      case Right(user) =>
        val fieldAccessed = field match
          case "completed"  => user.completedMediaIds.distinct
          case "dropped"    => user.droppedMediaIds
          case "in_progress" => user.inProgressMediaIds
          case "on_hold"     => user.onHoldMediaIds
          case "pending"    => user.pendingMediaIds

        val filteredField = categoryOption match
          case Some(categories) =>
            fieldAccessed.filter {
              case _: MovieId                                           => categories.contains("movie")
              case _: TvShowId                                          => categories.contains("tv_show")
              case (_: TvShowId, _: TvSeasonNumber)                     => categories.contains("season")
              case (_: TvShowId, _: TvSeasonNumber, _: TvEpisodeNumber) => categories.contains("episode")
              case videogameId: VideogameId                             => categories.contains("videogame")
              case bookId: BookId                                       => categories.contains("book")
            }
          case None => fieldAccessed

        (field, filteredField) match
          case ("completed" | "dropped" | "pending", list: List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId]) =>
            Right(Left(list))
          case ("in_progress" | "on_hold", list: List[TvShowId | (TvShowId, TvSeasonNumber) | VideogameId | BookId]) =>
            Right(Right(list))
          case _ => throw Exception("Internal server error")

  private def addMedia(userId: UserId, field: String, mediaId: MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId):
  Either[
    UserError,
    Either[
      List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId],
      List[TvShowId | (TvShowId, TvSeasonNumber) | VideogameId | BookId]
    ]
  ] =
    (field, mediaId) match
      case ("in_progress", movieId: MovieId) =>
        Left(BadRequest("'In Progress' does not support movies"))
      case ("in_progress", episodeNumber: (TvShowId, TvSeasonNumber, TvEpisodeNumber)) =>
        Left(BadRequest("'In Progress' does not support episodes"))
      case ("on_hold", movieId: MovieId) =>
        Left(BadRequest("'On Hold' does not support movies"))
      case ("on_hold", episodeNumber: (TvShowId, TvSeasonNumber, TvEpisodeNumber)) =>
        Left(BadRequest("'On Hold' does not support episodes"))
      case _ =>
        CommonFunctions.getUser(userId) match
          case Left(error) => Left(error)
          case Right(user) =>
            val (fieldAccessed, otherFields) = field match
              case "completed"  => (user.completedMediaIds, List(user.droppedMediaIds, user.inProgressMediaIds, user.onHoldMediaIds, user.pendingMediaIds))
              case "dropped"    => (user.droppedMediaIds, List(user.inProgressMediaIds, user.onHoldMediaIds, user.pendingMediaIds))
              case "in_progress" => (user.inProgressMediaIds, List(user.droppedMediaIds, user.onHoldMediaIds, user.pendingMediaIds))
              case "on_hold"     => (user.onHoldMediaIds, List(user.droppedMediaIds, user.inProgressMediaIds, user.pendingMediaIds))
              case "pending"    => (user.pendingMediaIds, List())

            if fieldAccessed.contains(mediaId) then Left(Conflict(s"Media already '${field}''"))
            else
              val updatedMediaField = mediaId match
                case movieId: MovieId =>
                  if movieId.value <= 0 then Left(BadRequest("Invalid movie ID"))
                  else Right(movieId :: fieldAccessed)

                case tvShowId: TvShowId =>
                  if tvShowId.value <= 0 then Left(BadRequest("Invalid TV show ID"))
                  else Right(tvShowId :: fieldAccessed)

                case (tvShowId: TvShowId, seasonNumber: TvSeasonNumber) =>
                  if tvShowId.value <= 0 then Left(BadRequest("Invalid TV show ID"))
                  else if seasonNumber.value <= 0 then Left(BadRequest("Invalid season number"))
                  else Right((tvShowId, seasonNumber) :: fieldAccessed)

                case (tvShowId: TvShowId, seasonNumber: TvSeasonNumber, episodeNumber: TvEpisodeNumber) =>
                  if tvShowId.value <= 0 then Left(BadRequest("Invalid TV show ID"))
                  else if seasonNumber.value <= 0 then Left(BadRequest("Invalid season number"))
                  else if episodeNumber.value <= 0 then Left(BadRequest("Invalid episode number"))
                  else Right((tvShowId, seasonNumber, episodeNumber) :: fieldAccessed)

                case videogameId: VideogameId =>
                  if videogameId.value <= 0 then Left(BadRequest("Invalid videogame ID"))
                  else Right(videogameId :: fieldAccessed)

                case bookId: BookId =>
                  if bookId.value == "" then Left(BadRequest("Invalid book ID"))
                  else Right(bookId :: fieldAccessed)


              updatedMediaField match
                case Left(error) => Left(error)
                case Right(fieldList) =>
                  val (updatedUser, returnedList) = (field, fieldList) match
                    case ("completed", list: List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId]) =>
                      (user.copy(
                        completedMediaIds  = list,
                        droppedMediaIds    = user.droppedMediaIds.filterNot(_ == mediaId),
                        inProgressMediaIds = user.inProgressMediaIds.filterNot(_ == mediaId),
                        onHoldMediaIds     = user.onHoldMediaIds.filterNot(_ == mediaId),
                        pendingMediaIds    = user.droppedMediaIds.filterNot(_ == mediaId)
                      ), Right(Left(list)))
                    case ("dropped", list: List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId]) =>
                      (user.copy(
                        droppedMediaIds    = list,
                        inProgressMediaIds = user.inProgressMediaIds.filterNot(_ == mediaId),
                        onHoldMediaIds     = user.onHoldMediaIds.filterNot(_ == mediaId),
                        pendingMediaIds    = user.droppedMediaIds.filterNot(_ == mediaId)
                      ), Right(Left(list)))
                    case ("in_progress", list: List[TvShowId | (TvShowId, TvSeasonNumber) | VideogameId | BookId]) =>
                      (user.copy(
                        inProgressMediaIds = list,
                        droppedMediaIds    = user.droppedMediaIds.filterNot(_ == mediaId),
                        onHoldMediaIds     = user.onHoldMediaIds.filterNot(_ == mediaId),
                        pendingMediaIds    = user.droppedMediaIds.filterNot(_ == mediaId)
                      ), Right(Right(list)))
                    case ("on_hold", list: List[TvShowId | (TvShowId, TvSeasonNumber) | VideogameId | BookId]) =>
                      (user.copy(
                        onHoldMediaIds     = list,
                        droppedMediaIds    = user.droppedMediaIds.filterNot(_ == mediaId),
                        inProgressMediaIds = user.inProgressMediaIds.filterNot(_ == mediaId),
                        pendingMediaIds    = user.droppedMediaIds.filterNot(_ == mediaId)
                      ), Right(Right(list)))
                    case ("pending", list: List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId]) =>
                      (user.copy(
                        pendingMediaIds = list
                      ), Right(Left(list)))
                    case _ => throw Exception("Internal server error")

                  UserRepository.put(userId, updatedUser)
                  returnedList


  private def deleteMedia(userId: UserId, field: String, mediaId: MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId):
  Either[UserError, Unit] =
    (field, mediaId) match
      case ("in_progress", movieId: MovieId) =>
        Left(BadRequest("'In Progress' does not support movies"))
      case ("in_progress", episodeNumber: (TvShowId, TvSeasonNumber, TvEpisodeNumber)) =>
        Left(BadRequest("'In Progress' does not support episodes"))
      case ("on_hold", movieId: MovieId) =>
        Left(BadRequest("'On Hold' does not support movies"))
      case ("on_hold", episodeNumber: (TvShowId, TvSeasonNumber, TvEpisodeNumber)) =>
        Left(BadRequest("'On Hold' does not support episodes"))
      case _ =>
        CommonFunctions.getUser(userId) match
          case Left(error) => Left(error)
          case Right(user) =>
            val fieldAccessed = field match
              case "completed"  => user.completedMediaIds
              case "dropped"    => user.droppedMediaIds
              case "in_progress" => user.inProgressMediaIds
              case "on_hold"     => user.onHoldMediaIds
              case "pending"    => user.pendingMediaIds

            if !fieldAccessed.contains(mediaId) then Left(BadRequest(s"Media not '${field}' yet"))
            else
              val updatedMediaField = mediaId match
                case movieId: MovieId =>
                  if movieId.value <= 0 then Left(BadRequest("Invalid movie ID"))
                  else Right(fieldAccessed.filterNot(_ == movieId))

                case tvShowId: TvShowId =>
                  if tvShowId.value <= 0 then Left(BadRequest("Invalid TV show ID"))
                  else Right(fieldAccessed.filterNot(_ == tvShowId))

                case (tvShowId: TvShowId, seasonNumber: TvSeasonNumber) =>
                  if tvShowId.value <= 0 then Left(BadRequest("Invalid TV show ID"))
                  else if seasonNumber.value <= 0 then Left(BadRequest("Invalid season number"))
                  else Right(fieldAccessed.filterNot(_ == (tvShowId, seasonNumber)))

                case (tvShowId: TvShowId, seasonNumber: TvSeasonNumber, episodeNumber: TvEpisodeNumber) =>
                  if tvShowId.value <= 0 then Left(BadRequest("Invalid TV show ID"))
                  else if seasonNumber.value <= 0 then Left(BadRequest("Invalid season number"))
                  else if episodeNumber.value <= 0 then Left(BadRequest("Invalid episode number"))
                  else Right(fieldAccessed.filterNot(_ == (tvShowId, seasonNumber, episodeNumber)))

                case videogameId: VideogameId =>
                  if videogameId.value <= 0 then Left(BadRequest("Invalid videogame ID"))
                  else Right(fieldAccessed.filterNot(_ == videogameId))

                case bookId: BookId =>
                  if bookId.value == "" then Left(BadRequest("Invalid book ID"))
                  else Right(fieldAccessed.filterNot(_ == bookId))

              updatedMediaField match
                case Left(error) => Left(error)
                case Right(fieldList) =>
                  val updatedUser = (field, fieldList) match
                    case ("completed", list: List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId]) =>
                      user.copy(completedMediaIds = list)
                    case ("dropped", list: List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId]) =>
                      user.copy(droppedMediaIds = list)
                    case ("in_progress", list: List[TvShowId | (TvShowId, TvSeasonNumber) | VideogameId | BookId]) =>
                      user.copy(inProgressMediaIds = list)
                    case ("on_hold", list: List[TvShowId | (TvShowId, TvSeasonNumber) | VideogameId | BookId]) =>
                      user.copy(onHoldMediaIds = list)
                    case ("pending", list: List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId]) =>
                      user.copy(pendingMediaIds = list)
                    case _ => throw Exception("Internal server error")

                  UserRepository.put(userId, updatedUser)
                  Right(())

}
