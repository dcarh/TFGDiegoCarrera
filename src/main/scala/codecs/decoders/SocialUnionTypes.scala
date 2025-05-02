package codecs.decoders

import io.circe.generic.auto.*
import io.circe.Decoder

import cats.syntax.functor.*

import modelClasses.app.social.{MediaList, Reply, Review}

import MediaIDsUnionTypes.*
import SocialIDsUnionTypes.*

object SocialUnionTypes {

  implicit val likeableUnionDecoder: Decoder[MediaList | Review | Reply] = Decoder.instance { cursor =>
    List[Decoder[MediaList | Review | Reply]](
      Decoder[MediaList].widen,
      Decoder[Review].widen,
      Decoder[Reply].widen
    ).reduceLeft(_ or _).apply(cursor)
  }

}
