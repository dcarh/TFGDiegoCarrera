package server.logics.social

import cats.effect.IO
import dummies.repositories.{ReplyRepository, UserRepository}

import modelClasses.app.social.Reply
import modelClasses.app.user.User
import modelClasses.errors.UserError.*
import modelClasses.ids.Social.ReplyId
import modelClasses.ids.User.UserId

import server.logics.commonFunctions.CommonFunctions

object RepliesLogics {

  private val addNewReplyToUser: (User, ReplyId) => Either[UserError, User] =
    (user, replyId) =>
      if !user.replies.contains(replyId) then
        val updatedUser = user.copy(
          replies = replyId :: user.replies
        )
        UserRepository.put(updatedUser.id, updatedUser)
        Right(user)

      else
        Left(BadRequest("The user with the ID stored in the reply already has an reply with the same ID"))

  private val updateUserFromReply: (User, ReplyId) => Either[UserError, User] =
    (user, replyId) =>
      if user.replies.contains(replyId) then
        Right(user)

      else
        Left(BadRequest("The user with the ID stored in the reply doesn't own the reply"))

  private val removeReplyFromUser: (User, ReplyId) => Either[UserError, User] =
    (user, replyId) =>
      if user.replies.contains(replyId) then
        val updatedUser = user.copy(
          replies = user.replies.filterNot(_ == replyId)
        )
        UserRepository.put(updatedUser.id, updatedUser)
        Right(user)

      else
        Left(BadRequest("The user ID stored in the reply doesn't own the reply"))


  val getReply: ReplyId => IO[Either[UserError, Reply]] =
    replyId => IO {
      CommonFunctions.getReply(replyId) match 
        case Left(error) => Left(error)
        case Right(reply) => Right(reply)
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val createReply: Reply => IO[Either[UserError, Reply]] =
    newReply => IO {
      ReplyRepository.get(newReply.id) match
        case Some(_) => Left(Conflict(s"Reply with ID ${newReply.id.value} already exists"))
        case None if newReply.id.value <= 0 => Left(BadRequest("Invalid reply ID"))
        case None =>
          CommonFunctions.getUserAndApply(newReply.userId)(newReply.id, addNewReplyToUser) match
            case Left(error) => Left(error)
            case Right(_) =>
              ReplyRepository.put(newReply.id, newReply)
              Right(newReply)
              
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val editReply: ((ReplyId, Reply)) => IO[Either[UserError, Reply]] =
    (replyId, updatedReplyData) => IO {
      CommonFunctions.getReply(replyId) match
        case Left(error) => Left(error)
        case Right(existingReply) =>
          CommonFunctions.getUserAndApply(existingReply.userId)(existingReply.id, updateUserFromReply) match
            case Left(error) => Left(error)
            case Right(_) =>
              val updatedReply = existingReply.copy(
                id = updatedReplyData.id,
                userId = updatedReplyData.userId,
                objectRepliedId = updatedReplyData.objectRepliedId,
                reply = updatedReplyData.reply,
                likes = updatedReplyData.likes,
                replies = updatedReplyData.replies
              )
              ReplyRepository.put(replyId, updatedReply)
              Right(updatedReply)
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val deleteReply: ReplyId => IO[Either[UserError, Unit]] =
    replyId => IO {
      CommonFunctions.getReply(replyId) match
        case Left(error) => Left(error)
        case Right(reply) =>
          CommonFunctions.getUserAndApply(reply.userId)(reply.id, removeReplyFromUser) match
            case Left(error) => Left(error)
            case Right(_) =>
              ReplyRepository.delete(reply.id)
              Right(())
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

}
