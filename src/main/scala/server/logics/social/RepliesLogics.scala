package server.logics.social

import cats.effect.IO
import memory.repositories.ReplyRepository
import domain.app.social.Reply
import domain.app.user.User
import domain.errors.UserError.*
import domain.ids.Social.{MediaListId, ReplyId, ReviewId}
import server.logics.commonFunctions.CommonFunctions

object RepliesLogics {

  val getReply: ReplyId => IO[Either[UserError, Reply]] =
    replyId => IO.pure {
      CommonFunctions.getReply(replyId) match 
        case Left(error)  => Left(error)
        case Right(reply) => Right(reply)
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val createReply: Reply => IO[Either[UserError, Reply]] =
    newReply => IO.pure {
      ReplyRepository.get(newReply.id) match
        case Some(_)                        => Left(Conflict(s"Reply with ID ${newReply.id.value} already exists"))
        case None if newReply.id.value <= 0 => Left(BadRequest("Invalid reply ID"))
        case None                           =>
          CommonFunctions.getUserAndApply(newReply.userId)(newReply, RepliesAuxFunctions.addNewReplyToUser) match
            case Left(error) => Left(error)
            case Right(_)    =>
              val result = newReply.repliedObjectId match
                case mediaListId: MediaListId =>
                  CommonFunctions.getMediaListAndApply(mediaListId)(newReply, RepliesAuxFunctions.addNewReplyToMediaList) match
                    case Left(error) => Left(error)
                    case Right(_)    => Right(())

                case reviewId: ReviewId =>
                  CommonFunctions.getReviewAndApply(reviewId)(newReply, RepliesAuxFunctions.addNewReplyToReview) match
                    case Left(error) => Left(error)
                    case Right(_)    => Right(())

                case replyId: ReplyId =>
                  CommonFunctions.getReplyAndApply(replyId)(newReply, RepliesAuxFunctions.addNewReplyToReply) match
                    case Left(error) => Left(error)
                    case Right(_)    => Right(())

              result match
                case Left(error) => Left(error)
                case Right(_)    =>
                  ReplyRepository.put(newReply.id, newReply)
                  Right(newReply)
              
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val editReply: ((ReplyId, Reply)) => IO[Either[UserError, Reply]] =
    (replyId, updatedReply) => IO.pure {
      if (replyId.value != updatedReply.id.value)
        Left(BadRequest("Reply ID in path and updated reply ID did not match"))
      else
        CommonFunctions.getReply(replyId) match
          case Left(error)          => Left(error)
          case Right(existingReply) =>
            CommonFunctions.getUserAndApply(existingReply.userId)(existingReply, RepliesAuxFunctions.updateUserFromReply) match
              case Left(error) => Left(error)
              case Right(_)    =>
                val result = existingReply.repliedObjectId match
                  case mediaListId: MediaListId =>
                    CommonFunctions.getMediaListAndApply(mediaListId)(existingReply, RepliesAuxFunctions.updateMediaListFromReply) match
                      case Left(error) => Left(error)
                      case Right(_)    => Right(())
  
                  case reviewId: ReviewId =>
                    CommonFunctions.getReviewAndApply(reviewId)(existingReply, RepliesAuxFunctions.updateReviewFromReply) match
                      case Left(error) => Left(error)
                      case Right(_)    => Right(())
  
                  case replyId: ReplyId =>
                    CommonFunctions.getReplyAndApply(replyId)(existingReply, RepliesAuxFunctions.updateReplyFromReply) match
                      case Left(error) => Left(error)
                      case Right(_)    => Right(())
  
                result match
                  case Right(_) =>
                    ReplyRepository.put(replyId, updatedReply)
                    Right(updatedReply)
  
                  case Left(error) => Left(error)

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val deleteReply: ReplyId => IO[Either[UserError, Unit]] =
    replyId => IO.pure {
      CommonFunctions.getReply(replyId) match
        case Left(error)  => Left(error)
        case Right(reply) =>
          CommonFunctions.getUserAndApply(reply.userId)(reply, RepliesAuxFunctions.removeReplyFromUser) match
            case Left(error) => Left(error)
            case Right(_)    =>
              val result = reply.repliedObjectId match
                case mediaListId: MediaListId =>
                  CommonFunctions.getMediaListAndApply(mediaListId)(reply, RepliesAuxFunctions.removeReplyFromMediaList) match
                    case Left(error) => Left(error)
                    case Right(_)    => Right(())

                case reviewId: ReviewId =>
                  CommonFunctions.getReviewAndApply(reviewId)(reply, RepliesAuxFunctions.removeReplyFromReview) match
                    case Left(error) => Left(error)
                    case Right(_)    => Right(())

                case replyId: ReplyId =>
                  CommonFunctions.getReplyAndApply(replyId)(reply, RepliesAuxFunctions.removeReplyFromReply) match
                    case Left(error) => Left(error)
                    case Right(_)    => Right(())

              result match
                case Left(error) => Left(error)
                case Right(_)    =>
                  ReplyRepository.delete(reply.id)
                  Right(())
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

}
