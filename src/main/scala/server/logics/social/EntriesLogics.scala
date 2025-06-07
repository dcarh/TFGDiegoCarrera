package server.logics.social

import cats.effect.IO
import memory.repositories.{EntryRepository, RatingRepository, UserRepository}
import domain.app.social.Entry
import domain.app.user.User
import domain.errors.UserError.*
import domain.ids.Media.{BookId, TvEpisodeNumber, MovieId, TvSeasonNumber, TvShowId, VideogameId}
import domain.ids.Social.EntryId
import domain.ids.User.UserId
import server.logics.commonFunctions.CommonFunctions


object EntriesLogics {

  private def userMediaUpdated(user: User, entry: Entry, add: Boolean): User =
    if entry.completed then
      if add then
        user.copy(
          completedMediaIds  = entry.mediaId :: user.completedMediaIds,
          droppedMediaIds    = user.droppedMediaIds.filterNot(_ == entry.mediaId),
          inProgressMediaIds = user.inProgressMediaIds.filterNot(_ == entry.mediaId),
          onHoldMediaIds     = user.onHoldMediaIds.filterNot(_ == entry.mediaId),
          pendingMediaIds    = user.pendingMediaIds.filterNot(_ == entry.mediaId),
        )
      else
        val index = user.completedMediaIds.indexOf(entry.mediaId)
        val completedUpdated =
          if index >= 0 then user.completedMediaIds.patch(index, Nil, 1)
          else user.completedMediaIds
        user.copy(completedMediaIds = completedUpdated)
    else if entry.dropped then
      if add then
        user.copy(
          droppedMediaIds    = entry.mediaId :: user.droppedMediaIds.filterNot(_ == entry.mediaId),
          inProgressMediaIds = user.inProgressMediaIds.filterNot(_ == entry.mediaId),
          onHoldMediaIds     = user.onHoldMediaIds.filterNot(_ == entry.mediaId),
          pendingMediaIds    = user.pendingMediaIds.filterNot(_ == entry.mediaId),
        )
      else
        user.copy(droppedMediaIds = user.droppedMediaIds.filterNot(_ == entry.mediaId))
    else
      entry.onHold match
        case Some(onHold) if onHold =>
          entry.mediaId match
            case id: (TvShowId | (TvShowId, TvSeasonNumber) | VideogameId | BookId) =>
              if add then
                user.copy(
                  onHoldMediaIds     = id :: user.onHoldMediaIds.filterNot(_ == id),
                  droppedMediaIds    = user.droppedMediaIds.filterNot(_ == id),
                  inProgressMediaIds = user.inProgressMediaIds.filterNot(_ == id),
                  pendingMediaIds    = user.pendingMediaIds.filterNot(_ == id)
                )
              else
                user.copy(onHoldMediaIds = user.onHoldMediaIds.filterNot(_ == entry.mediaId))
            case _ => throw Exception("'On Hold' does not support movies nor episodes")
        case _ =>
          entry.inProgress match
            case Some(inProgress) if inProgress =>
              entry.mediaId match
                case id: (TvShowId | (TvShowId, TvSeasonNumber) | VideogameId | BookId) =>
                  if add then
                    user.copy(
                      inProgressMediaIds = id :: user.inProgressMediaIds.filterNot(_ == id),
                      droppedMediaIds    = user.droppedMediaIds.filterNot(_ == id),
                      onHoldMediaIds     = user.onHoldMediaIds.filterNot(_ == id),
                      pendingMediaIds    = user.pendingMediaIds.filterNot(_ == id)
                    )
                  else
                    user.copy(inProgressMediaIds = user.inProgressMediaIds.filterNot(_ == entry.mediaId))
                case _ => throw Exception("'In Progress' does not support movies nor episodes")
            case _ => user

  private val addNewEntryToUser: (User, Entry, Boolean) => Either[UserError, User] =
    (user, entry, edit) =>
      if !user.entriesIds.contains(entry.id) || edit then
        val mediaUpdate = userMediaUpdated(user, entry, edit)
        val updatedUser = mediaUpdate.copy(
          entriesIds = entry.id :: user.entriesIds
        )
        UserRepository.put(updatedUser.id, updatedUser)
        Right(updatedUser)

      else
        Left(BadRequest("The user with the ID stored in the entry already has an entry with the same ID"))


  private val removeEntryFromUser: (User, Entry) => Either[UserError, User] =
    (user, entry) =>
      if user.entriesIds.contains(entry.id) then
        val mediaUpdate = userMediaUpdated(user, entry, false)
        val updatedUser = mediaUpdate.copy(
          entriesIds = user.entriesIds.filterNot(_ == entry.id)
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
          CommonFunctions.getUserAndApply(newEntry.userId)(newEntry, addNewEntryToUser(_, _, false)) match
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
            CommonFunctions.getUserAndApply(existingEntry.userId)(existingEntry, removeEntryFromUser) match
              case Left(error) => Left(error)
              case Right(_)    =>
                CommonFunctions.getUserAndApply(updatedEntry.userId)(updatedEntry, addNewEntryToUser(_, _, true)) match
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
          CommonFunctions.getUserAndApply(entry.userId)(entry, removeEntryFromUser) match
            case Left(error) => Left(error)
            case Right(_)    =>
              EntryRepository.delete(entry.id)
              Right(())
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }
}
