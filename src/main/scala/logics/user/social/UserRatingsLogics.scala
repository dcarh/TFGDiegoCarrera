package logics.user.social

import cats.effect.IO
import domain.errors.UserError.*
import domain.ids.Social.RatingId
import domain.ids.User.UserId
import logics.functions.CommonFunctions

object UserRatingsLogics {

  val getUserRatings: UserId => IO[Either[UserError, List[RatingId]]] =
    userId => IO.pure {
      CommonFunctions.getUser(userId) match
        case Left(error) => Left(error)
        case Right(user) => Right(user.ratingsIds)
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

}
