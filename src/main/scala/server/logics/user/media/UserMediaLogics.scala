package server.logics.user.media

import cats.effect.IO
import dummies.repositories.UserRepository
import modelClasses.errors.UserError.*
import modelClasses.ids.User.UserId
import modelClasses.ids.Media.{BookId, TvEpisodeNumber, MovieId, TvSeasonNumber, TvShowId, VideogameId}
import server.logics.commonFunctions.CommonFunctions

object UserMediaLogics {

  // TODO: Implementar funcionalidad de sortByOption (en caso de seguir adelante con ello)
  
  // TODO: Habría que retocar algo en caso de hacer lo de las Entry's invisibles para estos endpoints, supongo

  val getAllMedia: ((UserId, String, Option[String], Option[List[String]])) => IO[
    Either[
      UserError,
      List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId]
    ]
  ] =
    (userId, field, sortByOption, categoryOption) => IO.pure {
      getAllMedia(userId, field, sortByOption, categoryOption) match
        case Left(error) => Left(error)
        case Right(Left(list)) => Right(list)
        case Right(Right(list)) => Right(list)
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val addMovie:
    ((UserId, String, MovieId)) => IO[Either[UserError, List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId]]] =
    (userId, field, movieId) => IO.pure {
      addMedia(userId, field, movieId) match
        case Left(error) => Left(error)
        case Right(Left(list)) => Right(list)
        case Right(Right(_)) => Left(Unknown(500, "Internal server error"))
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val addTvShow:
    ((UserId, String, TvShowId)) => IO[Either[UserError, List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId]]] =
    (userId, field, tvShowId) => IO.pure {
      addMedia(userId, field, tvShowId) match
        case Left(error) => Left(error)
        case Right(Left(list)) => Right(list)
        case Right(Right(list)) => Right(list)
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val addSeason:
    ((UserId, String, TvShowId, TvSeasonNumber)) => IO[Either[UserError, List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId]]] =
    (userId, field, tvShowId, seasonNumber) => IO.pure {
      addMedia(userId, field, (tvShowId, seasonNumber)) match
        case Left(error) => Left(error)
        case Right(Left(list)) => Right(list)
        case Right(Right(list)) => Right(list)
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val addEpisode:
    ((UserId, String, TvShowId, TvSeasonNumber, TvEpisodeNumber)) => IO[Either[UserError, List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId]]] =
    (userId, field, tvShowId, seasonNumber, episodeNumber) => IO.pure {
      addMedia(userId, field, (tvShowId, seasonNumber, episodeNumber)) match
        case Left(error) => Left(error)
        case Right(Left(list)) => Right(list)
        case Right(Right(_)) => Left(Unknown(500, "Internal server error"))
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val addVideogame:
    ((UserId, String, VideogameId)) => IO[Either[UserError, List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId]]] =
    (userId, field, videogameId) => IO.pure {
      addMedia(userId, field, videogameId) match
        case Left(error) => Left(error)
        case Right(Left(list)) => Right(list)
        case Right(Right(list)) => Right(list)
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val addBook:
    ((UserId, String, BookId)) => IO[Either[UserError, List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId]]] =
    (userId, field, bookId) => IO.pure {
      addMedia(userId, field, bookId) match
        case Left(error) => Left(error)
        case Right(Left(list)) => Right(list)
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

  val deleteSeason: ((UserId, String, TvShowId, TvSeasonNumber)) => IO[Either[UserError, Unit]] =
    (userId, field, tvShowId, seasonNumber) => IO.pure {
      deleteMedia(userId, field, (tvShowId, seasonNumber))
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val deleteEpisode: ((UserId, String, TvShowId, TvSeasonNumber, TvEpisodeNumber)) => IO[Either[UserError, Unit]] =
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



  private def getAllMedia(userId: UserId, field: String, sortByOption: Option[String], categoryOption:Option[List[String]]):
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
          case "completed" => user.completed
          case "dropped" => user.dropped
          case "inProgress" => user.inProgress
          case "onHold" => user.onHold
          case "pending" => user.pending

        val filteredField = categoryOption match
          case Some(categories) =>
            fieldAccessed.filter {
              case _: MovieId => categories.contains("movie")
              case _: TvShowId => categories.contains("tv_show")
              case (_: TvShowId, _: TvSeasonNumber) => categories.contains("season")
              case (_: TvShowId, _: TvSeasonNumber, _: TvEpisodeNumber) => categories.contains("episode")
              case videogameId: VideogameId => categories.contains("videogame")
              case bookId: BookId => categories.contains("book")
            }
          case None => fieldAccessed

        (field, filteredField) match
          case ("completed" | "dropped" | "pending", list: List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId]) =>
            Right(Left(list))
          case ("inProgress" | "onHold", list: List[TvShowId | (TvShowId, TvSeasonNumber) | VideogameId | BookId]) =>
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
      case ("inProgress", movieId: MovieId) =>
        Left(BadRequest("'In Progress' does not support movies"))
      case ("inProgress", episodeNumber: (TvShowId, TvSeasonNumber, TvEpisodeNumber)) =>
        Left(BadRequest("'In Progress' does not support episodes"))
      case ("onHold", movieId: MovieId) =>
        Left(BadRequest("'On Hold' does not support movies"))
      case ("onHold", episodeNumber: (TvShowId, TvSeasonNumber, TvEpisodeNumber)) =>
        Left(BadRequest("'On Hold' does not support episodes"))
      case _ =>
        CommonFunctions.getUser(userId) match
          case Left(error) => Left(error)
          case Right(user) =>
            val (fieldAccessed, otherFields) = field match
              case "completed" => (user.completed, List(user.dropped, user.inProgress, user.onHold, user.pending))
              case "dropped" => (user.dropped, List(user.inProgress, user.onHold, user.pending))
              case "inProgress" => (user.inProgress, List(user.dropped, user.onHold, user.pending))
              case "onHold" => (user.onHold, List(user.dropped, user.inProgress, user.pending))
              case "pending" => (user.pending, List())

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
                        completed = list,
                        dropped = user.dropped.filterNot(_ == mediaId),
                        inProgress = user.inProgress.filterNot(_ == mediaId),
                        onHold = user.onHold.filterNot(_ == mediaId),
                        pending = user.dropped.filterNot(_ == mediaId)
                      ), Right(Left(list)))
                    case ("dropped", list: List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId]) =>
                      (user.copy(
                        dropped = list,
                        inProgress = user.inProgress.filterNot(_ == mediaId),
                        onHold = user.onHold.filterNot(_ == mediaId),
                        pending = user.dropped.filterNot(_ == mediaId)
                      ), Right(Left(list)))
                    case ("inProgress", list: List[TvShowId | (TvShowId, TvSeasonNumber) | VideogameId | BookId]) =>
                      (user.copy(
                        inProgress = list,
                        dropped = user.dropped.filterNot(_ == mediaId),
                        onHold = user.onHold.filterNot(_ == mediaId),
                        pending = user.dropped.filterNot(_ == mediaId)
                      ), Right(Right(list)))
                    case ("onHold", list: List[TvShowId | (TvShowId, TvSeasonNumber) | VideogameId | BookId]) =>
                      (user.copy(
                        onHold = list,
                        dropped = user.dropped.filterNot(_ == mediaId),
                        inProgress = user.inProgress.filterNot(_ == mediaId),
                        pending = user.dropped.filterNot(_ == mediaId)
                      ), Right(Right(list)))
                    case ("pending", list: List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId]) =>
                      (user.copy(
                        pending = list
                      ), Right(Left(list)))
                    case _ => throw Exception("Internal server error")

                  UserRepository.put(userId, updatedUser)
                  returnedList



  private def deleteMedia(userId: UserId, field: String, mediaId: MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId):
  Either[UserError, Unit] =
    (field, mediaId) match
      case ("inProgress", movieId: MovieId) =>
        Left(BadRequest("'In Progress' does not support movies"))
      case ("inProgress", episodeNumber: (TvShowId, TvSeasonNumber, TvEpisodeNumber)) =>
        Left(BadRequest("'In Progress' does not support episodes"))
      case ("onHold", movieId: MovieId) =>
        Left(BadRequest("'On Hold' does not support movies"))
      case ("onHold", episodeNumber: (TvShowId, TvSeasonNumber, TvEpisodeNumber)) =>
        Left(BadRequest("'On Hold' does not support episodes"))
      case _ =>
        CommonFunctions.getUser(userId) match
          case Left(error) => Left(error)
          case Right(user) =>
            val fieldAccessed = field match
              case "completed" => user.completed
              case "dropped" => user.dropped
              case "inProgress" => user.inProgress
              case "onHold" => user.onHold
              case "pending" => user.pending

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
                      user.copy(completed = list)
                    case ("dropped", list: List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId]) =>
                      user.copy(dropped = list)
                    case ("inProgress", list: List[TvShowId | (TvShowId, TvSeasonNumber) | VideogameId | BookId]) =>
                      user.copy(inProgress = list)
                    case ("onHold", list: List[TvShowId | (TvShowId, TvSeasonNumber) | VideogameId | BookId]) =>
                      user.copy(onHold = list)
                    case ("pending", list: List[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId]) =>
                      user.copy(pending = list)
                    case _ => throw Exception("Internal server error")

                  UserRepository.put(userId, updatedUser)
                  Right(())

}
