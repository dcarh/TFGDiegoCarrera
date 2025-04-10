package server.logics.social

import cats.effect.IO
import dummies.repositories.{EntryRepository, RatingRepository, UserRepository}
import modelClasses.app.social.Entry
import modelClasses.app.user.User
import modelClasses.errors.UserError.*
import modelClasses.ids.Media.{BookId, TvEpisodeNumber, MovieId, TvSeasonNumber, TvShowId, VideogameId}
import modelClasses.ids.Social.EntryId
import modelClasses.ids.User.UserId
import server.logics.commonFunctions.CommonFunctions


object EntriesLogics {

  private def userMediaUpdated(user: User, entry: Entry): User =
    if entry.completed then
      user.copy(
        completed = entry.mediaId :: user.completed.filterNot(_ == entry.mediaId),
        dropped = user.dropped.filterNot(_ == entry.mediaId),
        inProgress = user.inProgress.filterNot(_ == entry.mediaId),
        onHold = user.onHold.filterNot(_ == entry.mediaId),
        pending = user.pending.filterNot(_ == entry.mediaId),
      )
    else if entry.dropped then
      user.copy(
        dropped = entry.mediaId :: user.dropped.filterNot(_ == entry.mediaId),
        inProgress = user.inProgress.filterNot(_ == entry.mediaId),
        onHold = user.onHold.filterNot(_ == entry.mediaId),
        pending = user.pending.filterNot(_ == entry.mediaId),
      )
    else
      entry.onHold match
        case Some(onHold) if onHold =>
          entry.mediaId match
            case id: (TvShowId | (TvShowId, TvSeasonNumber) | VideogameId | BookId) =>
              user.copy(
                onHold = id :: user.onHold.filterNot(_ == id),
                dropped = user.dropped.filterNot(_ == id),
                inProgress = user.inProgress.filterNot(_ == id),
                pending = user.pending.filterNot(_ == id)
              )
            case _ => throw Exception("'On Hold' does not support movies nor episodes")
        case _ =>
          entry.inProgress match
            case Some(inProgress) if inProgress =>
              entry.mediaId match
                case id: (TvShowId | (TvShowId, TvSeasonNumber) | VideogameId | BookId) =>
                  user.copy(
                    inProgress = id :: user.inProgress.filterNot(_ == id),
                    dropped = user.dropped.filterNot(_ == id),
                    onHold = user.onHold.filterNot(_ == id),
                    pending = user.pending.filterNot(_ == id)
                  )
                case _ => throw Exception("'In Progress' does not support movies nor episodes")
            case _ => user

  private val addNewEntryToUser: (User, Entry) => Either[UserError, User] =
    (user, entry) =>
      if !user.entries.contains(entry.id) then
        val mediaUpdate = userMediaUpdated(user, entry)
        val updatedUser = mediaUpdate.copy(
          entries = entry.id :: user.entries
        )
        UserRepository.put(updatedUser.id, updatedUser)
        Right(updatedUser)

      else
        Left(BadRequest("The user with the ID stored in the entry already has an entry with the same ID"))
        

  private val updateUserFromEntry: (User, Entry) => Either[UserError, User] =
    (user, entry) =>
      if user.entries.contains(entry.id) then
        val updatedUser = userMediaUpdated(user, entry)
        UserRepository.put(updatedUser.id, updatedUser)
        Right(updatedUser)
      else 
        Left(BadRequest("The user with the ID stored in the entry doesn't own the entry"))
      

  private val removeEntryFromUser: (User, Entry) => Either[UserError, User] =
    (user, entry) =>
      if user.entries.contains(entry.id) then
        // TODO: Habría que revisar otras Entries del usuario, porque si no existe ninguna otra Entry con el mismo 
        //  status, habría que eliminar el Media de la lista de Media del usuario para un determinado status
        val updatedUser = user.copy(
          entries = user.entries.filterNot(_ == entry.id)
        )
        UserRepository.put(updatedUser.id, updatedUser)
        Right(user)

      else
        Left(BadRequest("The user ID stored in the entry doesn't own the entry"))


  val getAllEntries: ((Option[String], Option[List[String]])) => IO[Either[UserError, List[Entry]]] = {
    (sortByOption, categoryOption) =>
      IO.pure {
        val entries = EntryRepository.getAll
        
        val filteredEntries = categoryOption match
          case Some(categories) =>
            entries.filter(
              entry => entry.mediaId match
                case _: MovieId => categories.contains("movie")
                case _: TvShowId => categories.contains("tv_show")
                case (_: TvShowId, _: TvSeasonNumber) => categories.contains("season")
                case (_: TvShowId, _: TvSeasonNumber, _: TvEpisodeNumber) => categories.contains("episode")
                case videogameId: VideogameId => categories.contains("videogame")
                case bookId: BookId => categories.contains("book") 
            )
          
          case None => entries

        val ratings = RatingRepository.getAll
        val ratingMap = ratings.map(rating => rating.id -> rating).toMap

        val sortedEntries = sortByOption match
          case Some("earliest") => Right(filteredEntries.sortBy(_.creationDate))
          case Some("newest") => Right(filteredEntries.sortBy(_.creationDate).reverse)

          case Some(s"${order}_rating") =>
            var ordered_entries = filteredEntries.sortBy { 
              entry => entry.rating.flatMap(ratingMap.get)
            }(Ordering.Option(Ordering.by(_.rating)))
            
            order match {
              case "highest" => ordered_entries = ordered_entries.reverse
            }
            Right(ordered_entries)

          case Some(unknown) => Left(BadRequest(s"Invalid sorting parameter: $unknown"))
          case None => Right(filteredEntries)

        sortedEntries
        
      }.handleError {
        case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }
  }

  val getEntry: EntryId => IO[Either[UserError, Entry]] =
    entryId => IO.pure {
      CommonFunctions.getEntry(entryId) match
        case Left(error) => Left(error)
        case Right(entry) => Right(entry)
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val createEntry: Entry => IO[Either[UserError, Entry]] =
    newEntry => IO.pure {
      EntryRepository.get(newEntry.id) match
        case Some(_) => Left(Conflict(s"Entry with ID ${newEntry.id.value} already exists"))
        case None if newEntry.id.value <= 0 => Left(BadRequest("Invalid entry ID"))
        case None =>
          CommonFunctions.getUserAndApply(newEntry.userId)(newEntry, addNewEntryToUser) match
            case Left(error) => Left(error)
            case Right(_) =>
              EntryRepository.put(newEntry.id, newEntry)
              Right(newEntry)

    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val editEntry: ((EntryId, Entry)) => IO[Either[UserError, Entry]] =
    (entryId, updatedEntryData) => IO.pure {
      CommonFunctions.getEntry(entryId) match
        case Left(error) => Left(error)
        case Right(existingEntry) =>
          CommonFunctions.getUserAndApply(existingEntry.userId)(existingEntry, updateUserFromEntry) match
            case Left(error) => Left(error)
            case Right(_) =>
              val updatedEntry = existingEntry.copy(
                id = updatedEntryData.id,
                userId = updatedEntryData.userId,
                mediaId = updatedEntryData.mediaId,
                rating = updatedEntryData.rating,
                review = updatedEntryData.review,
                completed = updatedEntryData.completed,
                inProgress = updatedEntryData.inProgress,
                onHold = updatedEntryData.onHold,
                dropped = updatedEntryData.dropped,
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
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val deleteEntry: EntryId => IO[Either[UserError, Unit]] =
    entryId => IO.pure {
      CommonFunctions.getEntry(entryId) match
        case Left(error) => Left (error)
        case Right(entry) =>
          CommonFunctions.getUserAndApply(entry.userId)(entry, removeEntryFromUser) match
            case Left(error) => Left(error)
            case Right(_) =>
              EntryRepository.delete(entry.id)
              Right(())
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

}
