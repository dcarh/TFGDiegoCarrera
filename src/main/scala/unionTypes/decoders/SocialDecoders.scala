package unionTypes.decoders


import io.circe.generic.auto.*
import io.circe.Decoder

import cats.syntax.functor.*

import modelClasses.app.social.{MediaContentList, Reply, Review}

import unionTypes.decoders.MediaDecodersForIDs.*
import unionTypes.decoders.SocialDecodersForIDs.*

object SocialDecoders {

  implicit val likeableUnionDecoder: Decoder[MediaContentList | Review | Reply] = Decoder.instance { cursor =>
    List[Decoder[MediaContentList | Review | Reply]](
      Decoder[MediaContentList].widen,
      Decoder[Review].widen,
      Decoder[Reply].widen
    ).reduceLeft(_ or _).apply(cursor)
  }

}
