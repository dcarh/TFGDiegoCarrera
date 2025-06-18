package logics.user.social

import cats.effect.IO
import domain.app.social.MediaList
import domain.errors.UserError.*
import domain.ids.User.UserId
import logics.functions.CommonFunctions
import memory.repositories.MediaListRepository

object UserMediaListsLogics {
  
  val getUserMediaLists: UserId => IO[Either[UserError, List[MediaList]]] =
    userId => IO.pure {
      CommonFunctions.getUser(userId) match
        case Left(error) => Left(error)
        case Right(user) => Right(MediaListRepository.getMany(user.mediaListsIds))
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

}
