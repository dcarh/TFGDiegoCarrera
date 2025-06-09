package logics.functions

import domain.app.social.MediaList
import domain.app.user.User
import domain.errors.UserError.{BadRequest, UserError}
import memory.repositories.UserRepository

object MediaListsAuxFunctions {

  val addNewMediaListToUser: (User, MediaList) => Either[UserError, User] =
    (user, mediaList) =>
      if !user.mediaListsIds.contains(mediaList.id) then
        val updatedUser = user.copy(
          mediaListsIds = mediaList.id :: user.mediaListsIds
        )
        UserRepository.put(updatedUser.id, updatedUser)
        Right(user)

      else
        Left(BadRequest("The user with the ID stored in the media list already has an media list with the same ID"))

  val updateUserFromMediaList: (User, MediaList) => Either[UserError, User] =
    (user, mediaList) =>
      if user.mediaListsIds.contains(mediaList.id) then
        Right(user)

      else
        Left(BadRequest("The user with the ID stored in the mediaList doesn't own the media list"))

  val removeMediaListFromUser: (User, MediaList) => Either[UserError, User] =
    (user, mediaList) =>
      if user.mediaListsIds.contains(mediaList.id) then
        val updatedUser = user.copy(
          mediaListsIds = user.mediaListsIds.filterNot(_ == mediaList.id)
        )
        UserRepository.put(updatedUser.id, updatedUser)
        Right(user)

      else
        Left(BadRequest("The user ID stored in the mediaList doesn't own the mediaList"))

}
