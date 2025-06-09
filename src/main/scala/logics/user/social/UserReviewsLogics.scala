package logics.user.social

import cats.effect.IO
import domain.errors.UserError.*
import domain.ids.User.UserId
import domain.ids.Social.ReviewId
import logics.functions.CommonFunctions

object UserReviewsLogics {

  val getUserReviews: UserId => IO[Either[UserError, List[ReviewId]]] =
    userId => IO.pure {
      CommonFunctions.getUser(userId) match
        case Left(error) => Left(error)
        case Right(user) => Right(user.reviewsIds)
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

}
