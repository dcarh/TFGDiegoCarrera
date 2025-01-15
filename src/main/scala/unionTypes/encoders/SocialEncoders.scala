package unionTypes.encoders

import io.circe.generic.auto.*
import io.circe.syntax.*
import io.circe.Encoder

import modelClasses.app.social.{MediaList, Reply, Review}

import unionTypes.encoders.MediaEncodersForIDs.*
import unionTypes.encoders.SocialEncodersForIDs.*

object SocialEncoders {

  implicit val likeableUnionEncoder: Encoder[MediaList | Review | Reply] = Encoder.instance {
    case mediaList: MediaList => mediaList.asJson
    case review: Review => review.asJson
    case reply: Reply => reply.asJson
  }
}
