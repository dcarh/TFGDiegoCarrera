package codecs.decoders

import io.circe.generic.auto.*
import io.circe.Decoder

import cats.syntax.functor.*

import domain.app.media.*

object AllMediaUnionType {

  implicit val allMediaUnionDecoder: Decoder[Movie | TvShow | TvSeason | TvEpisode | Videogame | Book] = Decoder.instance { cursor =>
    List[Decoder[Movie | TvShow | TvSeason | TvEpisode | Videogame | Book]](
      Decoder[Movie].widen,
      Decoder[TvShow].widen,
      Decoder[TvSeason].widen,
      Decoder[TvEpisode].widen,
      Decoder[Videogame].widen,
      Decoder[Book].widen
    ).reduceLeft(_ or _).apply(cursor)
  }
}
