package server.logics.social

import cats.effect.IO
import dummies.repositories.{MediaListRepository, UserRepository}
import modelClasses.app.social.MediaList
import modelClasses.app.user.User
import modelClasses.errors.UserError.*
import modelClasses.ids.Social.MediaListId
import modelClasses.ids.User.UserId

import server.logics.commonFunctions.CommonFunctions

object MediaListsLogics {

  private val addNewMediaListToUser: (User, MediaList) => Either[UserError, User] =
    (user, mediaList) =>
      if !user.mediaLists.contains(mediaList.id) then
        val updatedUser = user.copy(
          mediaLists = mediaList.id :: user.mediaLists
        )
        UserRepository.put(updatedUser.id, updatedUser)
        Right(user)

      else
        Left(BadRequest("The user with the ID stored in the media list already has an media list with the same ID"))

  private val updateUserFromMediaList: (User, MediaList) => Either[UserError, User] =
    (user, mediaListId) =>
      if user.mediaLists.contains(mediaListId) then
        Right(user)

      else
        Left(BadRequest("The user with the ID stored in the mediaList doesn't own the media list"))

  private val removeMediaListFromUser: (User, MediaList) => Either[UserError, User] =
    (user, mediaList) =>
      if user.mediaLists.contains(mediaList.id) then
        val updatedUser = user.copy(
          mediaLists = user.mediaLists.filterNot(_ == mediaList.id)
        )
        UserRepository.put(updatedUser.id, updatedUser)
        Right(user)

      else
        Left(BadRequest("The user ID stored in the mediaList doesn't own the mediaList"))


  val getAllMediaLists: Option[String] => IO[Either[UserError, List[MediaList]]] = {
    sortByOption => IO.pure {
      val mediaLists = MediaListRepository.getAll

      val sortedMediaLists = sortByOption match 
        case Some("earliest_created") => Right(mediaLists.sortBy(_.creationDate))
        case Some("newest_created") => Right(mediaLists.sortBy(_.creationDate).reverse)
        case Some("earliest_updated") => Right(mediaLists.sortBy(_.updateDate))
        case Some("newest_updated") => Right(mediaLists.sortBy(_.updateDate).reverse)
        case Some("least_liked") => Right(mediaLists.sortBy(_.likes.size))
        case Some("most_liked") => Right(mediaLists.sortBy(_.likes.size).reverse)
        case Some("least_replied") => Right(mediaLists.sortBy(_.replies.size))
        case Some("most_replied") => Right(mediaLists.sortBy(_.replies.size).reverse)
        case Some(unknown) => Left(BadRequest(s"Invalid sorting parameter: $unknown"))
        case None => Right(mediaLists)
      
      sortedMediaLists
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }
  }


  val getMediaList: MediaListId => IO[Either[UserError, MediaList]] =
    mediaListId => IO.pure {
      CommonFunctions.getMediaList(mediaListId) match 
        case Left(error) => Left(error)
        case Right(mediaList) => Right(mediaList)
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val createMediaList: MediaList => IO[Either[UserError, MediaList]] =
    newMediaList => IO.pure {
      MediaListRepository.get(newMediaList.id) match
        case Some(_) => Left(Conflict(s"Media list with ID ${newMediaList.id.value} already exists"))
        case None if newMediaList.id.value <= 0 => Left(BadRequest("Invalid media list ID"))
        case None =>
          CommonFunctions.getUserAndApply(newMediaList.userId)(newMediaList, addNewMediaListToUser) match
            case Left(error) => Left(error)
            case Right(_) =>
              MediaListRepository.put(newMediaList.id, newMediaList)
              Right(newMediaList)
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }


  val editMediaList: ((MediaListId, MediaList)) => IO[Either[UserError, MediaList]] =
    (mediaListId, updatedMediaListData) => IO.pure {
      CommonFunctions.getMediaList(mediaListId) match
        case Left(error) => Left(error)
        case Right(existingMediaList) =>
          CommonFunctions.getUserAndApply(existingMediaList.userId)(existingMediaList, updateUserFromMediaList) match
            case Left(error) => Left(error)
            case Right(_) =>
              val updatedMediaList = existingMediaList.copy(
                id = updatedMediaListData.id,
                userId = updatedMediaListData.userId,
                title = updatedMediaListData.title,
                description = updatedMediaListData.description,
                mediaContentsIds = updatedMediaListData.mediaContentsIds,
                visibility = updatedMediaListData.visibility,
                allowReplies = updatedMediaListData.allowReplies,
                ranked = updatedMediaListData.ranked,
                creationDate = updatedMediaListData.creationDate,
                updateDate = updatedMediaListData.updateDate,
                likes = updatedMediaListData.likes,
                replies = updatedMediaListData.replies
              )
              MediaListRepository.put(mediaListId, updatedMediaList)
              Right(updatedMediaList)
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  
  val deleteMediaList: MediaListId => IO[Either[UserError, Unit]] =
    mediaListId => IO.pure {
      CommonFunctions.getMediaList(mediaListId) match
        case Left(error) => Left(error)
        case Right(mediaList) =>
          CommonFunctions.getUserAndApply(mediaList.userId)(mediaList, removeMediaListFromUser) match
            case Left(error) => Left(error)
            case Right(_) => 
              MediaListRepository.delete(mediaList.id)
              Right(())
        
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

}
