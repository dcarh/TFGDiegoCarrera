package logics.user.social

import cats.effect.IO
import domain.app.social.Reply
import domain.errors.UserError.*
import domain.ids.User.UserId
import logics.functions.CommonFunctions
import memory.repositories.ReplyRepository

object UserRepliesLogics {

  val getUserReplies: UserId => IO[Either[UserError, List[Reply]]] =
    userId => IO.pure {
      CommonFunctions.getUser(userId) match
        case Left(error) => Left(error)
        case Right(user) => Right(ReplyRepository.getMany(user.repliesIds))
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

}
