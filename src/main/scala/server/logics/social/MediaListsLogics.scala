package server.logics.social

import cats.effect.IO
import dummies.repositories.MediaListRepository

import modelClasses.app.social.MediaList
import modelClasses.errors.UserError.*
import modelClasses.ids.Social.MediaListId

object MediaListsLogics {

  val getAllMediaLists: Option[String] => IO[Either[UserError, List[MediaList]]] = {
    sortByOption =>
      IO {
        val mediaLists = MediaListRepository.getAll

        val sortedMediaLists = sortByOption match {
          case Some("earliest_created") =>
            Right(mediaLists.sortBy(_.creationDate))

          case Some("newest_created") =>
            Right(mediaLists.sortBy(_.creationDate).reverse)
            
          case Some("earliest_updated") =>
            Right(mediaLists.sortBy(_.updateDate))

          case Some("newest_updated") =>
            Right(mediaLists.sortBy(_.updateDate).reverse)

          case Some("least_liked") =>
            Right(mediaLists.sortBy(_.likes.size))
          
          case Some("most_liked") =>
            Right(mediaLists.sortBy(_.likes.size).reverse)

          case Some("least_replied") =>
            Right(mediaLists.sortBy(_.replies.size))
          
          case Some("most_replied") =>
            Right(mediaLists.sortBy(_.replies.size).reverse)

          case Some(unknown) =>
            Left(BadRequest(s"Invalid sorting parameter: $unknown"))

          case None =>
            Right(mediaLists)
        }
        sortedMediaLists
      }.handleError {
        case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }
  }

  val getMediaList: MediaListId => IO[Either[UserError, MediaList]] =
    mediaListId => IO {
      MediaListRepository.get(mediaListId) match {
        case Some(mediaList) =>
          Right(mediaList)

        case None if mediaListId.value <= 0 =>
          Left(BadRequest("Invalid media content list ID"))

        case None =>
          Left(NotFound(s"Media list with ID ${mediaListId.value} not found"))
      }
    }.handleError {
      case ex: Exception =>
        Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val createMediaList: MediaList => IO[Either[UserError, MediaList]] =
    newMediaList => IO {
      MediaListRepository.put(newMediaList.id, newMediaList)
      Right(newMediaList)
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val editMediaList: ((MediaListId, MediaList)) => IO[Either[UserError, MediaList]] =
    (mediaListId, updatedMediaListData) => IO {
      MediaListRepository.get(mediaListId) match {
        case Some(existingMediaList) =>
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
        case None =>
          Left(NotFound(s"Media list with ID ${mediaListId.value} not found"))
      }
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val deleteMediaList: MediaListId => IO[Either[UserError, Unit]] =
    mediaListId => IO {
      MediaListRepository.delete(mediaListId) match {
        case "Object deleted successfully!" =>
          Right(())
        case otherMessage =>
          Left(Conflict(s"Media list with ID ${mediaListId.value} could not be deleted: $otherMessage"))
      }
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

}
