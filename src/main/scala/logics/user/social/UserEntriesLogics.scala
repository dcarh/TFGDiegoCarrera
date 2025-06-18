package logics.user.social

import cats.effect.IO
import domain.app.social.Entry
import domain.errors.UserError.*
import domain.ids.User.UserId
import logics.functions.CommonFunctions
import memory.repositories.EntryRepository

object UserEntriesLogics {

  val getUserEntries: UserId => IO[Either[UserError, List[Entry]]] =
    userId => IO.pure {
      CommonFunctions.getUser(userId) match
        case Left(error) => Left(error)
        case Right(user) => Right(EntryRepository.getMany(user.entriesIds))
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

}
