package codecs

import modelClasses.app.media.{MovieId, TVShowId}
import sttp.tapir.{Codec, CodecFormat, DecodeResult}
import sttp.tapir.CodecFormat.TextPlain

object ModelClasses {
  
  // Definir Codec para MovieId
  implicit val movieIdCodec: Codec[String, MovieId, TextPlain] = 
    Codec.string.mapDecode(s => DecodeResult.Value(MovieId(s.toLong)))(_.value.toString)

  // Definir Codec para TVShowId
  implicit val tvShowIdCodec: Codec[String, TVShowId, TextPlain] = 
    Codec.string.mapDecode(s => DecodeResult.Value(TVShowId(s.toLong)))(_.value.toString)

}
