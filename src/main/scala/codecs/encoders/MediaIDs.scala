package codecs.encoders

import io.circe.Encoder

import modelClasses.ids.Media.*

object MediaIDs {
  
  implicit val movieIdEncoder: Encoder[MovieId] =
    Encoder.encodeLong.contramap(_.value)

  implicit val tvShowIdDecoder: Encoder[TvShowId] =
    Encoder.encodeLong.contramap(_.value)

  implicit val tvSeasonNumberDecoder: Encoder[TvSeasonNumber] =
    Encoder.encodeLong.contramap(_.value)

  implicit val tvEpisodeNumberDecoder: Encoder[TvEpisodeNumber] =
    Encoder.encodeLong.contramap(_.value)

  implicit val videogameIdDecoder: Encoder[VideogameId] =
    Encoder.encodeLong.contramap(_.value)

  implicit val bookIdDecoder: Encoder[BookId] =
    Encoder.encodeString.contramap(_.value)
}
