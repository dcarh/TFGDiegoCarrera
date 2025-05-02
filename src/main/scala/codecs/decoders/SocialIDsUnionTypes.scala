package codecs.decoders

import io.circe.{Decoder, DecodingFailure}
import modelClasses.ids.Social.{MediaListId, ReplyId, ReviewId}

object SocialIDsUnionTypes {

  implicit val socialIdsDecoder: Decoder[MediaListId | ReviewId | ReplyId] = Decoder.instance { cursor =>
    cursor.downField("type").as[String].flatMap {
      case "MediaListId" => cursor.downField("value").as[Long].map(MediaListId.apply)
      case "ReviewId" => cursor.downField("value").as[Long].map(ReviewId.apply)
      case "ReplyId" => cursor.downField("value").as[Long].map(ReplyId.apply)
      case other => Left(DecodingFailure(s"Unknown type: $other", cursor.history))
    }
  }
}
