package unionTypes.decoders

import io.circe.generic.auto.*
import io.circe.Decoder

import cats.syntax.functor.*

import modelClasses.app.media.*

object MediaDecodersAll {

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
