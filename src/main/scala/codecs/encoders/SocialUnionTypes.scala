package codecs.encoders

import io.circe.generic.auto.*
import io.circe.syntax.*
import io.circe.Encoder

import modelClasses.app.social.{MediaList, Reply, Review}

import MediaIDsUnionTypes.*
import SocialIDsUnionTypes.*

object SocialUnionTypes {

  implicit val likeableUnionEncoder: Encoder[MediaList | Review | Reply] = Encoder.instance {
    case mediaList: MediaList => mediaList.asJson
    case review: Review => review.asJson
    case reply: Reply => reply.asJson
  }
}
