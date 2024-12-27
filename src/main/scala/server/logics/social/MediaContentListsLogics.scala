package server.logics.social

import cats.effect.IO
import dummies.repositories.MediaContentListRepository

import modelClasses.app.social.MediaContentList
import modelClasses.errors.UserError.*
import modelClasses.ids.Social.MediaContentListId

object MediaContentListsLogics {

  val getAllMediaContentListsLogic: Option[String] => IO[Either[UserError, List[MediaContentList]]] = {
    sortByOption =>
      IO {
        val mediaContentLists = MediaContentListRepository.getAll

        val sortedMediaContentLists = sortByOption match {
          case Some("earliest_created") =>
            Right(mediaContentLists.sortBy(_.creationDate))

          case Some("newest_created") =>
            Right(mediaContentLists.sortBy(_.creationDate).reverse)
            
          case Some("earliest_updated") =>
            Right(mediaContentLists.sortBy(_.updateDate))

          case Some("newest_updated") =>
            Right(mediaContentLists.sortBy(_.updateDate).reverse)

          case Some("least_liked") =>
            Right(mediaContentLists.sortBy(_.likes.size))
          
          case Some("most_liked") =>
            Right(mediaContentLists.sortBy(_.likes.size).reverse)

          case Some("least_replied") =>
            Right(mediaContentLists.sortBy(_.replies.size))
          
          case Some("most_replied") =>
            Right(mediaContentLists.sortBy(_.replies.size).reverse)

          case Some(unknown) =>
            Left(BadRequest(s"Invalid sorting parameter: $unknown"))

          case None =>
            Right(mediaContentLists)
        }
        sortedMediaContentLists
      }.handleError {
        case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
      }
  }

  val getMediaContentListLogic: MediaContentListId => IO[Either[UserError, MediaContentList]] =
    mediaContentListId => IO {
      MediaContentListRepository.get(mediaContentListId) match {
        case Some(mediaContentList) =>
          Right(mediaContentList)

        case None if mediaContentListId.value <= 0 =>
          Left(BadRequest("Invalid media content list ID"))

        case None =>
          Left(NotFound(s"Media content list with ID $mediaContentListId not found"))
      }
    }.handleError {
      case ex: Exception =>
        Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val createMediaContentListLogic: MediaContentList => IO[Either[UserError, MediaContentList]] =
    newMediaContentList => IO {
      MediaContentListRepository.put(newMediaContentList.id, newMediaContentList)
      Right(newMediaContentList)
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val editMediaContentListLogic: ((MediaContentListId, MediaContentList)) => IO[Either[UserError, MediaContentList]] =
    (mediaContentListId, updatedMediaContentListData) => IO {
      MediaContentListRepository.get(mediaContentListId) match {
        case Some(existingMediaContentList) =>
          val updatedMediaContentList = existingMediaContentList.copy(
            id = updatedMediaContentListData.id,
            userId = updatedMediaContentListData.userId,
            title = updatedMediaContentListData.title,
            description = updatedMediaContentListData.description,
            mediaContentsIds = updatedMediaContentListData.mediaContentsIds,
            visibility = updatedMediaContentListData.visibility,
            allowReplies = updatedMediaContentListData.allowReplies,
            ranked = updatedMediaContentListData.ranked,
            creationDate = updatedMediaContentListData.creationDate,
            updateDate = updatedMediaContentListData.updateDate,
            likes = updatedMediaContentListData.likes,
            replies = updatedMediaContentListData.replies
          )
          MediaContentListRepository.put(mediaContentListId, updatedMediaContentList)
          Right(updatedMediaContentList)
        case None =>
          Left(NotFound(s"Media content list with ID ${mediaContentListId.value} not found"))
      }
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  val deleteMediaContentListLogic: MediaContentListId => IO[Either[UserError, Unit]] =
    mediaContentListId => IO {
      MediaContentListRepository.delete(mediaContentListId) match {
        case "Object deleted successfully!" =>
          Right(())
        case otherMessage =>
          Left(Conflict(s"Media content list with ID ${mediaContentListId.value} could not be deleted: $otherMessage"))
      }
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

}
