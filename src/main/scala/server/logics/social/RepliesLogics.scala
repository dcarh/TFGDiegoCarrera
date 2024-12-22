package server.logics.social

import cats.effect.IO

import modelClasses.app.social.Reply
import modelClasses.errors.UserError.*
import modelClasses.ids.Social.ReplyId
import modelClasses.dummies.repositories.ReplyRepository

object RepliesLogics {

  val getReplyLogic: ReplyId => IO[Either[UserError, Reply]] =
    replyId => IO {
      ReplyRepository.get(replyId) match {
        case Some(reply) =>
          Right(reply)

        case None if replyId.value <= 0 =>
          Left(BadRequest("Invalid reply ID"))

        case None =>
          Left(NotFound(s"Reply with ID $replyId not found"))
      }
    }.handleError {
      case ex: Exception =>
        Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val createReplyLogic: Reply => IO[Either[UserError, Reply]] =
    newReply => IO {
      ReplyRepository.put(newReply.id, newReply)
      Right(newReply)
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val editReplyLogic: ((ReplyId, Reply)) => IO[Either[UserError, Reply]] =
    (replyId, updatedReplyData) => IO {
      ReplyRepository.get(replyId) match {
        case Some(existingReply) =>
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
        case None =>
          Left(NotFound(s"Reply with ID ${replyId.value} not found"))
      }
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val deleteReplyLogic: ReplyId => IO[Either[UserError, Unit]] =
    replyId => IO {
      ReplyRepository.delete(replyId) match {
        case "Object deleted successfully!" =>
          Right(())
        case otherMessage =>
          Left(Conflict(s"Reply with ID ${replyId.value} could not be deleted: $otherMessage"))
      }
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

}
