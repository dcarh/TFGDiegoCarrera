package server.logics.user.social

import cats.effect.IO

import modelClasses.errors.UserError.*
import modelClasses.ids.User.UserId
import modelClasses.ids.Social.ReviewId

import server.logics.commonFunctions.CommonFunctions

object UserReviewsLogics {

  val getUserReviews: ((UserId, Option[List[String]], Option[String])) => IO[Either[UserError, List[ReviewId]]] =
    (userId, sortByOption, filterByOption) => IO.pure {
      CommonFunctions.getUser(userId) match
        case Left(error) => Left(error)
        case Right(user) => Right(user.reviews)
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

}
