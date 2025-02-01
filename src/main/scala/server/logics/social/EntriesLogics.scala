package server.logics.social

import cats.effect.IO
import dummies.repositories.{EntryRepository, RatingRepository, UserRepository}
import modelClasses.app.social.Entry
import modelClasses.app.user.User
import modelClasses.errors.UserError.*
import modelClasses.ids.Social.EntryId
import modelClasses.ids.User.UserId


object EntriesLogics {

//  private val checkIfUserExistsAndApply: UserId => (EntryId, (User, EntryId) => Either[UserError, User]) => Either[UserError, User] =
//    userId =>
//      (entryId, function) =>
//      UserRepository.get(userId) match
//        case Some(user) =>
//          function(user, entryId)
//        case None if userId.value <= 0 =>
//          Left(BadRequest("Invalid entry ID"))
//        case None =>
//          Left(NotFound(s"Entry with ID ${userId.value} not found"))

  private def checkIfUserExistsAndApply(userId: UserId)(entryId: EntryId, f: (User, EntryId) => Either[UserError, User]): Either[UserError, User] =
      UserRepository.get(userId) match
        case Some(user) =>
          f(user, entryId)

        case None if userId.value <= 0 =>
          Left(BadRequest("Invalid entry ID"))

        case None =>
          Left(NotFound(s"Entry with ID ${userId.value} not found"))

  private val addNewEntryToUser: (User, EntryId) => Either[UserError, User] =
    (user, entryId) =>
      if !user.entries.contains(entryId) then
        val updatedUser = user.copy(
          entries = entryId :: user.entries
        )
        UserRepository.put(updatedUser.id, updatedUser)
        Right(user)

      else
        Left(BadRequest("The user with the ID stored in the entry already has an entry with the same ID"))

  private val updateUserFromEntry: (User, EntryId) => Either[UserError, User] =
    (user, entryId) =>
      if user.entries.contains(entryId) then
        Right(user)

      else
        Left(BadRequest("The user with the ID stored in the entry doesn't own the entry"))

  private val removeEntryFromUser: (User, EntryId) => Either[UserError, User] =
    (user, entryId) =>
      if user.entries.contains(entryId) then
        val updatedUser = user.copy(
          entries = user.entries.filterNot(_ == entryId)
        )
        UserRepository.put(updatedUser.id, updatedUser)
        Right(user)

      else
        Left(BadRequest("The user ID stored in the entry doesn't own the entry"))


  val getAllEntries: Option[String] => IO[Either[UserError, List[Entry]]] = {
    sortByOption =>
      IO {
        val entries = EntryRepository.getAll
        val ratings = RatingRepository.getAll
        val ratingMap = ratings.map(rating => rating.id -> rating).toMap

        val sortedEntries = sortByOption match
          case Some("earliest") =>
            Right(entries.sortBy(_.creationDate))

          case Some("newest") =>
            Right(entries.sortBy(_.creationDate).reverse)

          case Some(s"${order}_rating") =>
            var ordered_entries = entries.sortBy { entry =>
              entry.rating.flatMap(ratingMap.get)
            }(Ordering.Option(Ordering.by(_.rating)))
            
            order match {
              case "highest" =>
                ordered_entries = ordered_entries.reverse
            }
            Right(ordered_entries)

          case Some(unknown) =>
            Left(BadRequest(s"Invalid sorting parameter: $unknown"))

          case None =>
            Right(entries)

        sortedEntries
      }.handleError {
        case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }
  }

  val getEntry: EntryId => IO[Either[UserError, Entry]] =
    entryId => IO {
      EntryRepository.get(entryId) match
        case Some(entry) =>
          Right(entry)

        case None if entryId.value <= 0 =>
          Left(BadRequest("Invalid entry ID"))

        case None =>
          Left(NotFound(s"Entry with ID ${entryId.value} not found"))

    }.handleError {
      case ex: Exception =>
        Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val createEntry: Entry => IO[Either[UserError, Entry]] =
    newEntry => IO {
      EntryRepository.get(newEntry.id) match
        case Some(_) =>
          Left(Conflict(s"Entry with ID ${newEntry.id.value} already exists"))

        case None if newEntry.id.value <= 0 =>
          Left(BadRequest("Invalid entry ID"))

        case None =>
          checkIfUserExistsAndApply(newEntry.userId)(newEntry.id, addNewEntryToUser) match
            case Right(_) =>
              EntryRepository.put(newEntry.id, newEntry)
              Right(newEntry)

            case Left(error) =>
              Left(error)

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val editEntry: ((EntryId, Entry)) => IO[Either[UserError, Entry]] =
    (entryId, updatedEntryData) => IO {
      EntryRepository.get(entryId) match
        case Some(existingEntry) =>
          checkIfUserExistsAndApply(existingEntry.userId)(existingEntry.id, updateUserFromEntry) match
            case Right(_) =>
              val updatedEntry = existingEntry.copy(
                id = updatedEntryData.id,
                userId = updatedEntryData.userId,
                mediaId = updatedEntryData.mediaId,
                rating = updatedEntryData.rating,
                review = updatedEntryData.review,
                completed = updatedEntryData.completed,
                paused = updatedEntryData.paused,
                abandoned = updatedEntryData.abandoned,
                repeat = updatedEntryData.repeat,
                finishedDate = updatedEntryData.finishedDate,
                startedDate = updatedEntryData.startedDate,
                platform = updatedEntryData.platform,
                timeSpent = updatedEntryData.timeSpent,
                tags = updatedEntryData.tags,
                creationDate = updatedEntryData.creationDate
              )
              EntryRepository.put(entryId, updatedEntry)
              Right(updatedEntry)

            case Left(error) => Left(error)

        case None if entryId.value <= 0 =>
          Left(BadRequest("Invalid entry ID"))

        case None =>
          Left(NotFound(s"Entry with ID ${entryId.value} not found"))

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val deleteEntry: EntryId => IO[Either[UserError, Unit]] =
    entryId => IO {
      EntryRepository.get(entryId) match
        case Some(entry) =>
          checkIfUserExistsAndApply(entry.userId)(entry.id, removeEntryFromUser) match
            case Right(_) =>
              EntryRepository.delete(entry.id)
              Right(())
            
            case Left(error) => Left(error)
          
        case None if entryId.value <= 0 =>
          Left(BadRequest("Invalid entry ID"))

        case None =>
          Left(NotFound(s"Entry with ID ${entryId.value} not found"))

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

}
