package server.logics.user.social

import cats.effect.IO

import domain.errors.UserError.*
import domain.ids.User.UserId
import domain.ids.Social.EntryId

import server.logics.commonFunctions.CommonFunctions

object UserEntriesLogics {

  val getUserEntries: UserId => IO[Either[UserError, List[EntryId]]] =
    userId => IO.pure {
      CommonFunctions.getUser(userId) match
        case Left(error) => Left(error)
        case Right(user) => Right(user.entriesIds)
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

}
