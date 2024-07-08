package codecs

import modelClasses.app.media.IDs._
import sttp.tapir.{Codec, CodecFormat, DecodeResult}
import sttp.tapir.CodecFormat.TextPlain

object ModelClasses {
  
  implicit val movieIdCodec: Codec[String, MovieId, TextPlain] = 
    Codec.string.mapDecode(s => DecodeResult.Value(MovieId(s.toLong)))(_.value.toString)

  implicit val tvShowIdCodec: Codec[String, TVShowId, TextPlain] = 
    Codec.string.mapDecode(s => DecodeResult.Value(TVShowId(s.toLong)))(_.value.toString)
  
  implicit val seasonNumberCodec: Codec[String, SeasonNumber, TextPlain] = 
    Codec.string.mapDecode(s => DecodeResult.Value(SeasonNumber(s.toLong)))(_.value.toString)
  
  implicit val episodeNumberCodec: Codec[String, EpisodeNumber, TextPlain] = 
    Codec.string.mapDecode(s => DecodeResult.Value(EpisodeNumber(s.toLong)))(_.value.toString)

  implicit val bookIdCodec: Codec[String, BookId, TextPlain] =
    Codec.string.mapDecode(s => DecodeResult.Value(BookId(s)))(_.value)

  implicit val videogameIdCodec: Codec[String, VideogameId, TextPlain] =
    Codec.string.mapDecode(s => DecodeResult.Value(VideogameId(s.toLong)))(_.value.toString)
}
