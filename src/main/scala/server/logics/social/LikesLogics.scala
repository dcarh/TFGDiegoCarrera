package server.logics.social

import cats.effect.IO
import dummies.repositories.LikeRepository

import modelClasses.app.social.Like
import modelClasses.errors.UserError.*
import modelClasses.ids.Social.LikeId

object LikesLogics {

  val getLikeLogic: LikeId => IO[Either[UserError, Like]] =
    likeId => IO {
      LikeRepository.get(likeId) match {
        case Some(like) =>
          Right(like)

        case None if likeId.value <= 0 =>
          Left(BadRequest("Invalid like ID"))

        case None =>
          Left(NotFound(s"Like with ID ${likeId.value} not found"))
      }
    }.handleError {
      case ex: Exception =>
        Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val createLikeLogic: Like => IO[Either[UserError, Like]] =
    newLike => IO {
      LikeRepository.put(newLike.id, newLike)
      Right(newLike)
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val deleteLikeLogic: LikeId => IO[Either[UserError, Unit]] =
    likeId => IO {
      LikeRepository.delete(likeId) match {
        case "Object deleted successfully!" =>
          Right(())
        case otherMessage =>
          Left(Conflict(s"Like with ID ${likeId.value} could not be deleted: $otherMessage"))
      }
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }
}
