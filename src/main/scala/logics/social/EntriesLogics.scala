package logics.social

import cats.effect.IO
import memory.repositories.{EntryRepository, RatingRepository, UserRepository}
import domain.app.social.Entry
import domain.app.user.User
import domain.errors.UserError.*
import domain.ids.Media.{BookId, MovieId, TvEpisodeNumber, TvSeasonNumber, TvShowId, VideogameId}
import domain.ids.Social.EntryId
import domain.ids.User.UserId
import logics.functions.{CommonFunctions, EntriesAuxFunctions}


object EntriesLogics {

  val getAllEntries: ((Option[String], Option[List[String]])) => IO[Either[UserError, List[Entry]]] = {
    (sortByOption, categoryOption) =>
      IO.pure {
        val entries = EntryRepository.getAll
        
        val filteredEntries = categoryOption match
          case Some(categories) =>
            entries.filter(
              entry => entry.mediaId match
                case _: MovieId                                           => categories.contains("movie")
                case _: TvShowId                                          => categories.contains("tv_show")
                case (_: TvShowId, _: TvSeasonNumber)                     => categories.contains("season")
                case (_: TvShowId, _: TvSeasonNumber, _: TvEpisodeNumber) => categories.contains("episode")
                case videogameId: VideogameId                             => categories.contains("videogame")
                case bookId: BookId                                       => categories.contains("book") 
            )
          
          case None => entries
        
        val ratings   = RatingRepository.getAll
        val ratingMap = ratings.map(rating => rating.id -> rating).toMap

        val sortedEntries = sortByOption match
          case Some("earliest") => Right(filteredEntries.sortBy(_.creationDate))
          case Some("newest")   => Right(filteredEntries.sortBy(_.creationDate).reverse)

          case Some(s"${order}_rating") =>
            var ordered_entries = filteredEntries.sortBy { 
              entry => entry.ratingId.flatMap(ratingMap.get)
            }(Ordering.Option(Ordering.by(_.rating)))
            
            order match {
              case "highest" => ordered_entries = ordered_entries.reverse
            }
            Right(ordered_entries)

          case Some(unknown) => Left(BadRequest(s"Invalid sorting parameter: $unknown"))
          case None          => Right(filteredEntries)

        sortedEntries
        
      }.handleError {
        case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }
  }

  val getEntry: EntryId => IO[Either[UserError, Entry]] =
    entryId => IO.pure {
      CommonFunctions.getEntry(entryId) match
        case Left(error)  => Left(error)
        case Right(entry) => Right(entry)
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val createEntry: Entry => IO[Either[UserError, Entry]] =
    newEntry => IO.pure {
      EntryRepository.get(newEntry.id) match
        case Some(_)                        => Left(Conflict(s"Entry with ID ${newEntry.id.value} already exists"))
        case None if newEntry.id.value <= 0 => Left(BadRequest("Invalid entry ID"))
        case None                           =>
          CommonFunctions.getUserAndApply(newEntry.userId)(newEntry, EntriesAuxFunctions.addNewEntryToUser(_, _, false)) match
            case Left(error) => Left(error)
            case Right(_)    =>
              EntryRepository.put(newEntry.id, newEntry)
              Right(newEntry)

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val editEntry: ((EntryId, Entry)) => IO[Either[UserError, Entry]] =
    (entryId, updatedEntry) => IO.pure {
      if (entryId.value != updatedEntry.id.value)
        Left(BadRequest("Entry ID in path and updated entry ID did not match"))
      else
        CommonFunctions.getEntry(entryId) match
          case Left(error)          => Left(error)
          case Right(existingEntry) =>
            CommonFunctions.getUserAndApply(existingEntry.userId)(existingEntry, EntriesAuxFunctions.removeEntryFromUser) match
              case Left(error) => Left(error)
              case Right(_)    =>
                CommonFunctions.getUserAndApply(updatedEntry.userId)(updatedEntry, EntriesAuxFunctions.addNewEntryToUser(_, _, true)) match
                  case Left(error) => Left(error)
                  case Right(_)    =>
                    EntryRepository.put(updatedEntry.id, updatedEntry)
                    Right(updatedEntry)

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val deleteEntry: EntryId => IO[Either[UserError, Unit]] =
    entryId => IO.pure {
      CommonFunctions.getEntry(entryId) match
        case Left(error)  => Left (error)
        case Right(entry) =>
          CommonFunctions.getUserAndApply(entry.userId)(entry, EntriesAuxFunctions.removeEntryFromUser) match
            case Left(error) => Left(error)
            case Right(_)    =>
              EntryRepository.delete(entry.id)
              Right(())
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }
}
