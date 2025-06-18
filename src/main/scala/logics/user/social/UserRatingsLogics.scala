package logics.user.social

import cats.effect.IO
import domain.app.social.Rating
import domain.errors.UserError.*
import domain.ids.User.UserId
import logics.functions.CommonFunctions
import memory.repositories.RatingRepository

object UserRatingsLogics {

  val getUserRatings: UserId => IO[Either[UserError, List[Rating]]] =
    userId => IO.pure {
      CommonFunctions.getUser(userId) match
        case Left(error) => Left(error)
        case Right(user) => Right(RatingRepository.getMany(user.ratingsIds))
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

}
