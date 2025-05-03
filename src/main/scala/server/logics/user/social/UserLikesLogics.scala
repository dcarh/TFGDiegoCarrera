package server.logics.user.social

import cats.effect.IO

import modelClasses.errors.UserError.*
import modelClasses.ids.User.UserId
import modelClasses.ids.Social.LikeId

import server.logics.commonFunctions.CommonFunctions

object UserLikesLogics {

  val getUserLikes: UserId => IO[Either[UserError, List[LikeId]]] =
    userId => IO.pure {
      CommonFunctions.getUser(userId) match
        case Left(error) => Left(error)
        case Right(user) => Right(user.likes)
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

}
