package server.logics.commonFunctions

import cats.effect.IO
import dummies.repositories.*
import modelClasses.app.chatting.{Chat, Message}
import modelClasses.app.social.*
import modelClasses.app.user.User
import modelClasses.errors.UserError.{BadRequest, Conflict, NotFound, Unknown, UserError}
import modelClasses.ids.Chatting.*
import modelClasses.ids.Media.{BookId, EpisodeNumber, MovieId, SeasonNumber, TvShowId, VideogameId}
import modelClasses.ids.Social.*
import modelClasses.ids.User.UserId
import utility.ClassFields.{getFieldAsList, updateField}


object CommonFunctions {

  def getUser(userId: UserId): Either[UserError, User] =
    UserRepository.get(userId) match
      case Some(user) => Right(user)
      case None if userId.value <= 0 => Left(BadRequest("Invalid user ID"))
      case None => Left(NotFound(s"User with ID ${userId.value} not found"))


  def getUserAndApply[Id](userId: UserId)(id: Id, f: (User, Id) => Either[UserError, User]): Either[UserError, User] =
    UserRepository.get(userId) match
      case Some(user) => f(user, id)
      case None if userId.value <= 0 => Left(BadRequest("Invalid user ID"))
      case None => Left(NotFound(s"User with ID ${userId.value} not found"))
        

  def getBothUsers(userId1: UserId, userId2: UserId): Either[UserError, (User, User)] =
    getUser(userId1) match
      case Left(error) => Left(error)
      case Right(user1) => getUser(userId2) match
        case Left(error) => Left(error)
        case Right(user2) => Right(user1, user2)
  

  def getChat(chatId: ChatId): Either[UserError, Chat] =
    ChatRepository.get(chatId) match
      case Some(chat) => Right(chat)
      case None if chatId.value <= 0 => Left(BadRequest("Invalid chat ID"))
      case None => Left(NotFound(s"Chat with ID ${chatId.value} not found"))


  def getMessage(messageId: MessageId): Either[UserError, Message] =
    MessageRepository.get(messageId) match
      case Some(message) => Right(message)
      case None if messageId.value <= 0 => Left(BadRequest("Invalid chat ID"))
      case None => Left(NotFound(s"Chat with ID ${messageId.value} not found"))


  def getEntry(entryId: EntryId): Either[UserError, Entry] =
    EntryRepository.get(entryId) match
      case Some(entry) => Right(entry)
      case None if entryId.value <= 0 => Left(BadRequest("Invalid entry ID"))
      case None => Left(NotFound(s"Entry with ID ${entryId.value} not found"))


  def getLike(likeId: LikeId): Either[UserError, Like] =
    LikeRepository.get(likeId) match
      case Some(like) => Right(like)
      case None if likeId.value <= 0 => Left(BadRequest("Invalid like ID"))
      case None => Left(NotFound(s"Like with ID ${likeId.value} not found"))
  

  def getMediaList(mediaListId: MediaListId): Either[UserError, MediaList] =
    MediaListRepository.get(mediaListId) match
      case Some(mediaList) => Right(mediaList)
      case None if mediaListId.value <= 0 => Left(BadRequest("Invalid media list ID"))
      case None => Left(NotFound(s"Media list with ID ${mediaListId.value} not found"))
  

  def getRating(ratingId: RatingId): Either[UserError, Rating] =
    RatingRepository.get(ratingId) match
      case Some(rating) => Right(rating)
      case None if ratingId.value <= 0 => Left(BadRequest("Invalid rating ID"))
      case None => Left(NotFound(s"Rating with ID ${ratingId.value} not found"))
  

  def getReply(replyId: ReplyId): Either[UserError, Reply] =
    ReplyRepository.get(replyId) match
      case Some(reply) => Right(reply)
      case None if replyId.value <= 0 => Left(BadRequest("Invalid reply ID"))
      case None => Left(NotFound(s"Reply with ID ${replyId.value} not found"))
  

  def getReview(reviewId: ReviewId): Either[UserError, Review] =
    ReviewRepository.get(reviewId) match
      case Some(review) => Right(review)
      case None if reviewId.value <= 0 => Left(BadRequest("Invalid review ID"))
      case None => Left(NotFound(s"Review with ID ${reviewId.value} not found"))

  def getAllMedia(userId: UserId, field: String, sortByOption: Option[String], categoryOption:Option[List[String]]):
    Either[
      UserError,
      Either[
        List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId],
        List[TvShowId | (TvShowId, SeasonNumber) | VideogameId | BookId]
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
                case (_: TvShowId, _: SeasonNumber) => categories.contains("season")
                case (_: TvShowId, _: SeasonNumber, _: EpisodeNumber) => categories.contains("episode")
                case videogameId: VideogameId => categories.contains("videogame")
                case bookId: BookId => categories.contains("book")
              }
            case None => fieldAccessed

          (field, filteredField) match
            case ("completed" | "dropped" | "pending", list: List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]) =>
              Right(Left(list))
            case ("inProgress" | "onHold", list: List[TvShowId | (TvShowId, SeasonNumber) | VideogameId | BookId]) =>
              Right(Right(list))
            case _ => throw Exception("Internal server error")

  def addMedia(userId: UserId, field: String, mediaId: MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId):
    Either[
      UserError,
      Either[
        List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId],
        List[TvShowId | (TvShowId, SeasonNumber) | VideogameId | BookId]
      ]
    ] =
      (field, mediaId) match
        case ("inProgress", movieId: MovieId) =>
          Left(BadRequest("'In Progress' does not support movies"))
        case ("inProgress", episodeNumber: (TvShowId, SeasonNumber, EpisodeNumber)) =>
          Left(BadRequest("'In Progress' does not support episodes"))
        case ("onHold", movieId: MovieId) =>
          Left(BadRequest("'On Hold' does not support movies"))
        case ("onHold", episodeNumber: (TvShowId, SeasonNumber, EpisodeNumber)) =>
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

              if fieldAccessed.contains(mediaId) then Left(Conflict(s"Media already '${field}''"))
              else
                val updatedMediaField = mediaId match
                  case movieId: MovieId =>
                    if movieId.value <= 0 then Left(BadRequest("Invalid movie ID"))
                    else Right(movieId :: fieldAccessed)

                  case tvShowId: TvShowId =>
                    if tvShowId.value <= 0 then Left(BadRequest("Invalid TV show ID"))
                    else Right(tvShowId :: fieldAccessed)

                  case (tvShowId: TvShowId, seasonNumber: SeasonNumber) =>
                    if tvShowId.value <= 0 then Left(BadRequest("Invalid TV show ID"))
                    else if seasonNumber.value <= 0 then Left(BadRequest("Invalid season number"))
                    else Right((tvShowId, seasonNumber) :: fieldAccessed)

                  case (tvShowId: TvShowId, seasonNumber: SeasonNumber, episodeNumber: EpisodeNumber) =>
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
                      case ("completed", list: List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]) =>
                        (user.copy(completed = list), Right(Left(list)))
                      case ("dropped", list: List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]) =>
                        (user.copy(dropped = list), Right(Left(list)))
                      case ("inProgress", list: List[TvShowId | (TvShowId, SeasonNumber) | VideogameId | BookId]) =>
                        (user.copy(inProgress = list), Right(Right(list)))
                      case ("onHold", list: List[TvShowId | (TvShowId, SeasonNumber) | VideogameId | BookId]) =>
                        (user.copy(onHold = list), Right(Right(list)))
                      case ("pending", list: List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]) =>
                        (user.copy(pending = list), Right(Left(list)))
                      case _ => throw Exception("Internal server error")

                    UserRepository.put(userId, updatedUser)
                    returnedList



  def deleteMedia(userId: UserId, field: String, mediaId: MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId):
    Either[UserError, Unit] =
      (field, mediaId) match
        case ("inProgress", movieId: MovieId) =>
          Left(BadRequest("'In Progress' does not support movies"))
        case ("inProgress", episodeNumber: (TvShowId, SeasonNumber, EpisodeNumber)) =>
          Left(BadRequest("'In Progress' does not support episodes"))
        case ("onHold", movieId: MovieId) =>
          Left(BadRequest("'On Hold' does not support movies"))
        case ("onHold", episodeNumber: (TvShowId, SeasonNumber, EpisodeNumber)) =>
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

                  case (tvShowId: TvShowId, seasonNumber: SeasonNumber) =>
                    if tvShowId.value <= 0 then Left(BadRequest("Invalid TV show ID"))
                    else if seasonNumber.value <= 0 then Left(BadRequest("Invalid season number"))
                    else Right(fieldAccessed.filterNot(_ == (tvShowId, seasonNumber)))

                  case (tvShowId: TvShowId, seasonNumber: SeasonNumber, episodeNumber: EpisodeNumber) =>
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
                      case ("completed", list: List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]) =>
                        user.copy(completed = list)
                      case ("dropped", list: List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]) =>
                        user.copy(dropped = list)
                      case ("inProgress", list: List[TvShowId | (TvShowId, SeasonNumber) | VideogameId | BookId]) =>
                        user.copy(inProgress = list)
                      case ("onHold", list: List[TvShowId | (TvShowId, SeasonNumber) | VideogameId | BookId]) =>
                        user.copy(onHold = list)
                      case ("pending", list: List[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId]) =>
                        user.copy(pending = list)
                      case _ => throw Exception("Internal server error")

                    UserRepository.put(userId, updatedUser)
                    Right(())

}
