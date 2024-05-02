package modelClasses.social

import io.circe.generic.auto._

import modelClasses.user.User
import modelClasses.media.MediaMainContent

sealed trait Visibility
object Visibility {
  case object Public extends Visibility
  case object Followers extends Visibility
  case object Private extends Visibility
}

sealed trait UserGeneratedContent {
  type Id <: Long
}

object UserGeneratedContent {
  type Id = Long
}

case class MediaContentList(
                           id           : MediaContentList.Id, 
                           user         : User.Id, 
                           mediaContents: List[MediaMainContent.Id], 
                           likes        : List[Like.Id],
                           replies      : List[Reply.Id],
                           visibility   : Visibility
                           ) extends UserGeneratedContent

object MediaContentList {
  type Id = Long
}

case class Review(
                 id            : Review.Id,
                 user          : User.Id,
                 objectReviewed: MediaMainContent.Id,
                 review        : String,
                 likes         : List[Like.Id],
                 replies       : List[Reply.Id],
                 visibility    : Visibility
                 ) extends UserGeneratedContent

object Review {
  type Id = Long
}


case class Reply(
                id           : Reply.Id,
                user         : User.Id,
                objectReplied: Either[MediaContentList.Id, UserGeneratedContent.Id],
                likes        : List[Like.Id],
                replies      : List[Reply.Id],
                visibility   : Visibility
                ) extends UserGeneratedContent

object Reply {
  type Id = Long
}