package server.logics.user.social

import cats.effect.IO

import domain.errors.UserError.*
import domain.ids.User.UserId
import domain.ids.Social.MediaListId

import server.logics.commonFunctions.CommonFunctions

object UserMediaListsLogics {
  
  val getUserMediaLists: UserId => IO[Either[UserError, List[MediaListId]]] =
    userId => IO.pure {
      CommonFunctions.getUser(userId) match
        case Left(error) => Left(error)
        case Right(user) => Right(user.mediaListsIds)
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

}
