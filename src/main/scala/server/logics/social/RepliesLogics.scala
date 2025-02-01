package server.logics.social

import cats.effect.IO
import dummies.repositories.{ReplyRepository, UserRepository}

import modelClasses.app.social.Reply
import modelClasses.app.user.User
import modelClasses.errors.UserError.*
import modelClasses.ids.Social.ReplyId
import modelClasses.ids.User.UserId

object RepliesLogics {

  private def checkIfUserExistsAndApply(userId: UserId)(replyId: ReplyId, f: (User, ReplyId) => Either[UserError, User]): Either[UserError, User] =
    UserRepository.get(userId) match
      case Some(user) =>
        f(user, replyId)

      case None if userId.value <= 0 =>
        Left(BadRequest("Invalid reply ID"))

      case None =>
        Left(NotFound(s"Reply with ID ${userId.value} not found"))

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
      ReplyRepository.get(replyId) match {
        case Some(reply) =>
          Right(reply)

        case None if replyId.value <= 0 =>
          Left(BadRequest("Invalid reply ID"))

        case None =>
          Left(NotFound(s"Reply with ID ${replyId.value} not found"))
      }
    }.handleError {
      case ex: Exception =>
        Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val createReply: Reply => IO[Either[UserError, Reply]] =
    newReply => IO {
      ReplyRepository.get(newReply.id) match
        case Some(_) =>
          Left(Conflict(s"Reply with ID ${newReply.id.value} already exists"))

        case None if newReply.id.value <= 0 =>
          Left(BadRequest("Invalid reply ID"))

        case None =>
          checkIfUserExistsAndApply(newReply.userId)(newReply.id, addNewReplyToUser) match
            case Right(_) =>
              ReplyRepository.put(newReply.id, newReply)
              Right(newReply)

            case Left(error) =>
              Left(error)
              
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val editReply: ((ReplyId, Reply)) => IO[Either[UserError, Reply]] =
    (replyId, updatedReplyData) => IO {
      ReplyRepository.get(replyId) match 
        case Some(existingReply) =>
          checkIfUserExistsAndApply(existingReply.userId)(existingReply.id, updateUserFromReply) match
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
              
            case Left(error) => Left(error)

        case None if replyId.value <= 0 =>
          Left(BadRequest("Invalid reply ID"))
            
        case None =>
          Left(NotFound(s"Reply with ID ${replyId.value} not found"))
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val deleteReply: ReplyId => IO[Either[UserError, Unit]] =
    replyId => IO {
      ReplyRepository.get(replyId) match
        case Some(reply) =>
          checkIfUserExistsAndApply(reply.userId)(reply.id, removeReplyFromUser) match
            case Right(_) =>
              ReplyRepository.delete(reply.id)
              Right(())

            case Left(error) => Left(error)

        case None if replyId.value <= 0 =>
          Left(BadRequest("Invalid reply ID"))

        case None =>
          Left(NotFound(s"Reply with ID ${replyId.value} not found"))
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

}
