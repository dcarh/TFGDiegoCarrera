package unionTypes.decoders


import io.circe.generic.auto.*
import io.circe.Decoder

import cats.syntax.functor.*

import modelClasses.app.social.{MediaList, Reply, Review}

import unionTypes.decoders.MediaDecodersForIDs.*
import unionTypes.decoders.SocialDecodersForIDs.*

object SocialDecoders {

  implicit val likeableUnionDecoder: Decoder[MediaList | Review | Reply] = Decoder.instance { cursor =>
    List[Decoder[MediaList | Review | Reply]](
      Decoder[MediaList].widen,
      Decoder[Review].widen,
      Decoder[Reply].widen
    ).reduceLeft(_ or _).apply(cursor)
  }

}
