package server.logics.user.social

import cats.effect.IO

import modelClasses.errors.UserError.*
import modelClasses.ids.Social.RatingId
import modelClasses.ids.User.UserId

import server.logics.commonFunctions.CommonFunctions

object UserRatingsLogics {

  val getUserRatings: ((UserId, Option[List[String]], Option[String])) => IO[Either[UserError, List[RatingId]]] =
    (userId, sortByOption, filterByOption) => IO.pure {
      CommonFunctions.getUser(userId) match
        case Left(error) => Left(error)
        case Right(user) => Right(user.ratings)
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

}
