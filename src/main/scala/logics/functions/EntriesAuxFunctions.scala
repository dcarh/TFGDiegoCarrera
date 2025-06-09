package logics.functions

import domain.app.social.Entry
import domain.app.user.User
import domain.errors.UserError.{BadRequest, UserError}
import domain.ids.Media.{BookId, TvSeasonNumber, TvShowId, VideogameId}
import memory.repositories.UserRepository

object EntriesAuxFunctions {

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

  val addNewEntryToUser: (User, Entry, Boolean) => Either[UserError, User] =
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


  val removeEntryFromUser: (User, Entry) => Either[UserError, User] =
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

}
