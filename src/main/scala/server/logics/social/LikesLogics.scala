package server.logics.social

import cats.effect.IO
import memory.repositories.LikeRepository
import domain.app.social.Like
import domain.app.user.User
import domain.errors.UserError.*
import domain.ids.Social.{LikeId, MediaListId, ReplyId, ReviewId}
import server.logics.commonFunctions.CommonFunctions

object LikesLogics {

  val getLike: LikeId => IO[Either[UserError, Like]] =
    likeId => IO.pure {
      CommonFunctions.getLike(likeId) match 
        case Left(error) => Left(error)
        case Right(like) => Right(like)
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val createLike: Like => IO[Either[UserError, Like]] =
    newLike => IO.pure {
      LikeRepository.get(newLike.id) match
        case Some(_)                       => Left(Conflict(s"Like with ID ${newLike.id.value} already exists"))
        case None if newLike.id.value <= 0 => Left(BadRequest("Invalid like ID"))
        case None                          =>
          CommonFunctions.getUserAndApply(newLike.userId)(newLike, LikesAuxFunctions.addNewLikeToUser) match
            case Left(error) => Left(error)
            case Right(_)    =>
              val result = newLike.likedElementId match
                case mediaListId: MediaListId =>
                  CommonFunctions.getMediaListAndApply(mediaListId)(newLike, LikesAuxFunctions.addNewLikeToMediaList) match
                    case Left(error) => Left(error)
                    case Right(_)    => Right(())

                case reviewId: ReviewId =>
                  CommonFunctions.getReviewAndApply(reviewId)(newLike, LikesAuxFunctions.addNewLikeToReview) match
                    case Left(error) => Left(error)
                    case Right(_)    => Right(())

                case replyId: ReplyId =>
                  CommonFunctions.getReplyAndApply(replyId)(newLike, LikesAuxFunctions.addNewLikeToReply) match
                    case Left(error) => Left(error)
                    case Right(_)    => Right(())

              result match
                case Left(error) => Left(error)
                case Right(_)    =>
                  LikeRepository.put(newLike.id, newLike)
                  Right(newLike)

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val deleteLike: LikeId => IO[Either[UserError, Unit]] =
    likeId => IO.pure {
      CommonFunctions.getLike(likeId) match
        case Left(error) => Left(error)
        case Right(like) =>
          CommonFunctions.getUserAndApply(like.userId)(like, LikesAuxFunctions.removeLikeFromUser) match
            case Left(error) => Left(error)
            case Right(_)    =>
              val result = like.likedElementId match
                case mediaListId: MediaListId =>
                  CommonFunctions.getMediaListAndApply(mediaListId)(like, LikesAuxFunctions.removeLikeFromMediaList) match
                    case Left(error) => Left(error)
                    case Right(_)    => Right(())

                case reviewId: ReviewId =>
                  CommonFunctions.getReviewAndApply(reviewId)(like, LikesAuxFunctions.removeLikeFromReview) match
                    case Left(error) => Left(error)
                    case Right(_)    => Right(())

                case replyId: ReplyId =>
                  CommonFunctions.getReplyAndApply(replyId)(like, LikesAuxFunctions.removeLikeFromReply) match
                    case Left(error) => Left(error)
                    case Right(_)    => Right(())

              result match
                case Left(error) => Left(error)
                case Right(_)    =>
                  LikeRepository.delete(like.id)
                  Right(())
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }
}
