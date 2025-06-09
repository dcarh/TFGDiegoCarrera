package logics.social

import cats.effect.IO
import memory.repositories.{MediaListRepository, UserRepository}
import domain.app.social.MediaList
import domain.app.user.User
import domain.errors.UserError.*
import domain.ids.Social.MediaListId
import domain.ids.User.UserId
import logics.functions.{CommonFunctions, MediaListsAuxFunctions}

object MediaListsLogics {


  val getAllMediaLists: Option[String] => IO[Either[UserError, List[MediaList]]] = {
    sortByOption => IO.pure {
      val mediaLists = MediaListRepository.getAll

      val sortedMediaLists = sortByOption match 
        case Some("earliest_created") => Right(mediaLists.sortBy(_.creationDate))
        case Some("newest_created")   => Right(mediaLists.sortBy(_.creationDate).reverse)
        case Some("earliest_updated") => Right(mediaLists.sortBy(_.updateDate))
        case Some("newest_updated")   => Right(mediaLists.sortBy(_.updateDate).reverse)
        case Some("least_liked")      => Right(mediaLists.sortBy(_.likesIds.size))
        case Some("most_liked")       => Right(mediaLists.sortBy(_.likesIds.size).reverse)
        case Some("least_replied")    => Right(mediaLists.sortBy(_.repliesIds.size))
        case Some("most_replied")     => Right(mediaLists.sortBy(_.repliesIds.size).reverse)
        case Some(unknown)            => Left(BadRequest(s"Invalid sorting parameter: $unknown"))
        case None                     => Right(mediaLists)
      
      sortedMediaLists
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }
  }


  val getMediaList: MediaListId => IO[Either[UserError, MediaList]] =
    mediaListId => IO.pure {
      CommonFunctions.getMediaList(mediaListId) match 
        case Left(error)      => Left(error)
        case Right(mediaList) => Right(mediaList)
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"An unexpected error occurred: ${ex.getMessage}"))
    }

  val createMediaList: MediaList => IO[Either[UserError, MediaList]] =
    newMediaList => IO.pure {
      MediaListRepository.get(newMediaList.id) match
        case Some(_)                            => Left(Conflict(s"Media list with ID ${newMediaList.id.value} already exists"))
        case None if newMediaList.id.value <= 0 => Left(BadRequest("Invalid media list ID"))
        case None                               =>
          CommonFunctions.getUserAndApply(newMediaList.userId)(newMediaList, MediaListsAuxFunctions.addNewMediaListToUser) match
            case Left(error) => Left(error)
            case Right(_)    =>
              MediaListRepository.put(newMediaList.id, newMediaList)
              Right(newMediaList)
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }


  val editMediaList: ((MediaListId, MediaList)) => IO[Either[UserError, MediaList]] =
    (mediaListId, updatedMediaList) => IO.pure {
      if (mediaListId.value != updatedMediaList.id.value) 
        Left(BadRequest("Media list ID in path and updated media list ID did not match"))
      else
        CommonFunctions.getMediaList(mediaListId) match
          case Left(error)              => Left(error)
          case Right(existingMediaList) =>
            CommonFunctions.getUserAndApply(existingMediaList.userId)(existingMediaList, MediaListsAuxFunctions.updateUserFromMediaList) match
              case Left(error) => Left(error)
              case Right(_)    =>
                MediaListRepository.put(mediaListId, updatedMediaList)
                Right(updatedMediaList)
      
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

  
  val deleteMediaList: MediaListId => IO[Either[UserError, Unit]] =
    mediaListId => IO.pure {
      CommonFunctions.getMediaList(mediaListId) match
        case Left(error)      => Left(error)
        case Right(mediaList) =>
          CommonFunctions.getUserAndApply(mediaList.userId)(mediaList, MediaListsAuxFunctions.removeMediaListFromUser) match
            case Left(error) => Left(error)
            case Right(_)    => 
              MediaListRepository.delete(mediaList.id)
              Right(())
        
    }.handleError {
      case ex: Exception => Left(Unknown(500, s"Unexpected error: ${ex.getMessage}"))
    }

}
