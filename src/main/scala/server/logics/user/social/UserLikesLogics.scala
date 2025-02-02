package server.logics.user.social

import cats.effect.IO
import dummies.repositories.UserRepository

import modelClasses.errors.UserError.*
import modelClasses.ids.User.UserId
import modelClasses.ids.Social.LikeId

object UserLikesLogics {

  val getUserLikes: ((UserId, Option[List[String]], Option[String])) => IO[Either[UserError, List[LikeId]]] =
    (userId, sortByOption, filterByOption) => IO {
      UserRepository.get(userId) match {
        case Some(user) =>
          Right(user.likes)

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
