package server.logics.commonFunctions

import dummies.repositories.*
import modelClasses.app.chatting.{Chat, Message}
import modelClasses.app.social.*
import modelClasses.app.user.User
import modelClasses.errors.UserError.{BadRequest, Conflict, NotFound, UserError}
import modelClasses.ids.Chatting.*
import modelClasses.ids.Media.{BookId, TvEpisodeNumber, MovieId, TvSeasonNumber, TvShowId, VideogameId}
import modelClasses.ids.Social.*
import modelClasses.ids.User.UserId


object CommonFunctions {

  def getUser(userId: UserId): Either[UserError, User] =
    UserRepository.get(userId) match
      case Some(user) => Right(user)
      case None if userId.value <= 0 => Left(BadRequest("Invalid user ID"))
      case None => Left(NotFound(s"User with ID ${userId.value} not found"))


  def getUserAndApply[A](userId: UserId)(elem: A, f: (User, A) => Either[UserError, User]): Either[UserError, User] =
    UserRepository.get(userId) match
      case Some(user) => f(user, elem)
      case None if userId.value <= 0 => Left(BadRequest("Invalid user ID"))
      case None => Left(NotFound(s"User with ID ${userId.value} not found"))


  def getMediaListAndApply[A](mediaListId: MediaListId)(elem: A, f: (MediaList, A) => Either[UserError, MediaList]): Either[UserError, MediaList] =
    MediaListRepository.get(mediaListId) match
      case Some(mediaList) => f(mediaList, elem)
      case None if mediaListId.value <= 0 => Left(BadRequest("Invalid media list ID"))
      case None => Left(NotFound(s"Media list with ID ${mediaListId.value} not found"))


  def getReviewAndApply[A](reviewId: ReviewId)(elem: A, f: (Review, A) => Either[UserError, Review]): Either[UserError, Review] =
    ReviewRepository.get(reviewId) match
      case Some(review) => f(review, elem)
      case None if reviewId.value <= 0 => Left(BadRequest("Invalid review ID"))
      case None => Left(NotFound(s"Review with ID ${reviewId.value} not found"))


  def getReplyAndApply[A](replyId: ReplyId)(elem: A, f: (Reply, A) => Either[UserError, Reply]): Either[UserError, Reply] =
    ReplyRepository.get(replyId) match
      case Some(reply) => f(reply, elem)
      case None if replyId.value <= 0 => Left(BadRequest("Invalid reply ID"))
      case None => Left(NotFound(s"Reply with ID ${replyId.value} not found"))
        

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

}
