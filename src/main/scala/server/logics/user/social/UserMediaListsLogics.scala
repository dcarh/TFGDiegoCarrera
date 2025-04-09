package server.logics.user.social

import cats.effect.IO

import modelClasses.errors.UserError.*
import modelClasses.ids.User.UserId
import modelClasses.ids.Social.MediaListId

import server.logics.commonFunctions.CommonFunctions

object UserMediaListsLogics {
  
  val getUserMediaLists: ((UserId, Option[String])) => IO[Either[UserError, List[MediaListId]]] =
    (userId, sortByOption) => IO.pure {
      CommonFunctions.getUser(userId) match
        case Left(error) => Left(error)
        case Right(user) => Right(user.lists)
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

}
