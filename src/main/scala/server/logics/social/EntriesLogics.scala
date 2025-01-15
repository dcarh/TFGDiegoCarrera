package server.logics.social

import cats.effect.IO
import dummies.repositories.{EntryRepository, RatingRepository}
import modelClasses.app.social.Entry
import modelClasses.errors.UserError.*
import modelClasses.ids.Social.EntryId

object EntriesLogics {

  val getAllEntries: Option[String] => IO[Either[UserError, List[Entry]]] = {
    sortByOption =>
      IO {
        val entries = EntryRepository.getAll
        val ratings = RatingRepository.getAll
        val ratingMap = ratings.map(rating => rating.id -> rating).toMap

        val sortedEntries = sortByOption match {
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
        }
        sortedEntries
      }.handleError {
        case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }
  }

  val getEntry: EntryId => IO[Either[UserError, Entry]] =
    entryId => IO {
      EntryRepository.get(entryId) match {
        case Some(entry) =>
          Right(entry)

        case None if entryId.value <= 0 =>
          Left(BadRequest("Invalid entry ID"))

        case None =>
          Left(NotFound(s"Entry with ID ${entryId.value} not found"))
      }
    }.handleError {
      case ex: Exception =>
        Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val createEntry: Entry => IO[Either[UserError, Entry]] =
    newEntry => IO {
      EntryRepository.put(newEntry.id, newEntry)
      Right(newEntry)
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val editEntry: ((EntryId, Entry)) => IO[Either[UserError, Entry]] =
    (entryId, updatedEntryData) => IO {
      EntryRepository.get(entryId) match {
        case Some(existingEntry) =>
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
        case None =>
          Left(NotFound(s"Entry with ID ${entryId.value} not found"))
      }
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val deleteEntry: EntryId => IO[Either[UserError, Unit]] =
    entryId => IO {
      EntryRepository.delete(entryId) match {
        case "Object deleted successfully!" =>
          Right(())
        case otherMessage =>
          Left(Conflict(s"Entry with ID ${entryId.value} could not be deleted: $otherMessage"))
      }
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

}
