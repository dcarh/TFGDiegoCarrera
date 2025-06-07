package codecs.decoders

import io.circe.Decoder

import domain.ids.Media.*

object MediaIDs {
  
  implicit val movieIdDecoder: Decoder[MovieId] =
    Decoder.decodeLong.map(MovieId.apply)
  
  implicit val tvShowIdDecoder: Decoder[TvShowId] =
    Decoder.decodeLong.map(TvShowId.apply)
  
  implicit val tvSeasonNumberDecoder: Decoder[TvSeasonNumber] =
    Decoder.decodeLong.map(TvSeasonNumber.apply)
  
  implicit val tvEpisodeNumberDecoder: Decoder[TvEpisodeNumber] =
    Decoder.decodeLong.map(TvEpisodeNumber.apply)
  
  implicit val videogameIdDecoder: Decoder[VideogameId] =
    Decoder.decodeLong.map(VideogameId.apply)
  
  implicit val bookIdDecoder: Decoder[BookId] =
    Decoder.decodeString.map(BookId.apply)
}
