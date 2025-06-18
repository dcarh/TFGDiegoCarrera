package logics.user.social

import cats.effect.IO
import domain.app.social.Review
import domain.errors.UserError.*
import domain.ids.User.UserId
import logics.functions.CommonFunctions
import memory.repositories.ReviewRepository

object UserReviewsLogics {

  val getUserReviews: UserId => IO[Either[UserError, List[Review]]] =
    userId => IO.pure {
      CommonFunctions.getUser(userId) match
        case Left(error) => Left(error)
        case Right(user) => Right(ReviewRepository.getMany(user.reviewsIds))
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

}
