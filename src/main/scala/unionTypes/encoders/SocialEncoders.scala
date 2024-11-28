package unionTypes.encoders

import io.circe.generic.auto.*
import io.circe.syntax.*
import io.circe.Encoder

import modelClasses.app.social.{MediaContentList, Reply, Review}

import unionTypes.encoders.MediaEncodersForIDs.*
import unionTypes.encoders.SocialEncodersForIDs.*

object SocialEncoders {

  implicit val likeableUnionEncoder: Encoder[MediaContentList | Review | Reply] = Encoder.instance {
    case mediaContentList: MediaContentList => mediaContentList.asJson
    case review: Review => review.asJson
    case reply: Reply => reply.asJson
  }

}
