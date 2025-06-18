package logics.user.social

import cats.effect.IO
import domain.app.social.Like
import domain.errors.UserError.*
import domain.ids.User.UserId
import logics.functions.CommonFunctions
import memory.repositories.LikeRepository

object UserLikesLogics {

  val getUserLikes: UserId => IO[Either[UserError, List[Like]]] =
    userId => IO.pure {
      CommonFunctions.getUser(userId) match
        case Left(error) => Left(error)
        case Right(user) => Right(LikeRepository.getMany(user.likesIds))
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

}
