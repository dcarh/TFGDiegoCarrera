package codecs.decoders

import io.circe.generic.auto.*
import io.circe.Decoder

import cats.syntax.functor.*

import modelClasses.app.media.*

object MediaUnionTypes {

  implicit val favouritesMediaUnionDecoder: Decoder[Movie | TvShow | Videogame | Book] = Decoder.instance { cursor =>
    List[Decoder[Movie | TvShow | Videogame | Book]](
      Decoder[Movie].widen,
      Decoder[TvShow].widen,
      Decoder[Videogame].widen,
      Decoder[Book].widen
    ).reduceLeft(_ or _).apply(cursor)
  }

  implicit val progressMediaUnionDecoder: Decoder[TvShow | TvSeason | Videogame | Book] = Decoder.instance { cursor =>
    List[Decoder[TvShow | TvSeason | Videogame | Book]](
      Decoder[TvShow].widen,
      Decoder[TvSeason].widen,
      Decoder[Videogame].widen,
      Decoder[Book].widen
    ).reduceLeft(_ or _).apply(cursor)
  }

  implicit val pendingMediaUnionDecoder: Decoder[Movie | TvShow | TvSeason | Videogame | Book] = Decoder.instance { cursor =>
    List[Decoder[Movie | TvShow | TvSeason | Videogame | Book]](
      Decoder[Movie].widen,
      Decoder[TvShow].widen,
      Decoder[TvSeason].widen,
      Decoder[Videogame].widen,
      Decoder[Book].widen
    ).reduceLeft(_ or _).apply(cursor)
  }

}
