package server.logics.user.social

import cats.effect.IO
import dummies.repositories.UserRepository

import modelClasses.errors.UserError.*
import modelClasses.ids.User.UserId
import modelClasses.ids.Social.ReplyId

object UserRepliesLogics {

  // TODO: sortByOption, filterByOption

  val getUserReplies: UserId => IO[Either[UserError, List[ReplyId]]] =
    userId => IO {
      UserRepository.get(userId) match {
        case Some(user) =>
          Right(user.replies)

        case None if userId.value <= 0 =>
          Left(BadRequest("Invalid user ID"))

        case None =>
          Left(NotFound(s"User with ID ${userId.value} not found"))
      }
    }.handleError {
      case ex: Exception =>
        Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

}
