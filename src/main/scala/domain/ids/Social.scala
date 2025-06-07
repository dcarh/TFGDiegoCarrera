package domain.ids

import io.circe.generic.auto.*

object Social {

  case class EntryId(value: Long)
  case class LikeId(value: Long)
  case class MediaListId(value: Long)
  case class RatingId(value: Long)
  case class ReplyId(value: Long)
  case class ReviewId(value: Long)
}
