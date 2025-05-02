package codecs.encoders

import io.circe.{Encoder, Json}
import io.circe.syntax.*

import modelClasses.ids.Social.{MediaListId, ReplyId, ReviewId}

object SocialIDsUnionTypes {

  implicit val socialIdsEncoder: Encoder[MediaListId | ReviewId | ReplyId] = Encoder.instance {
    case mediaListId: MediaListId => Json.obj("type" -> "MediaListId".asJson, "value" -> mediaListId.value.asJson)
    case reviewId: ReviewId => Json.obj("type" -> "ReviewId".asJson, "value" -> reviewId.value.asJson)
    case replyId: ReplyId => Json.obj("type" -> "ReplyId".asJson, "value" -> replyId.value.asJson)
  }
}
