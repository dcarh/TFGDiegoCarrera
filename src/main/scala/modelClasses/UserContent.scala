package modelClasses

import sttp.tapir.generic.auto.*

sealed trait UserContent {
  type Id <: Long
}

object UserContent {
  type Id = Long
}

case class MediaContentList(
                           id           : MediaContentList.Id, 
                           user         : User.Id, 
                           mediaContents: List[MediaContent.Id], 
                           likes        : List[Like.Id],
                           replies      : List[Comment.Id]
                           ) extends UserContent

object MediaContentList {
  type Id = Long
}

case class Review(
                 id            : Review.Id,
                 user          : User.Id,
                 objectReviewed: MediaContent.Id,
                 review        : String,
                 likes         : List[Like.Id],
                 replies       : List[Reply.Id]
                 ) extends UserContent

object Review {
  type Id = Long
}


case class Reply(
                id           : Reply.Id,
                user         : User.Id,
                objectReplied: Either[MediaContentList.Id, UserContent.Id],
                comment      : String,
                likes        : List[Like.Id]
                ) extends UserContent

object Reply {
  type Id = Long
}