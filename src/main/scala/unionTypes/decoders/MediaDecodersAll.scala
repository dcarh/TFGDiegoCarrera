package unionTypes.decoders

import io.circe.generic.auto.*
import io.circe.Decoder

import cats.syntax.functor.*

import modelClasses.app.media.*

object MediaDecodersAll {

  implicit val allMediaUnionDecoder: Decoder[Movie | TvShow | Season | Episode | Videogame | Book] = Decoder.instance { cursor =>
    List[Decoder[Movie | TvShow | Season | Episode | Videogame | Book]](
      Decoder[Movie].widen,
      Decoder[TvShow].widen,
      Decoder[Season].widen,
      Decoder[Episode].widen,
      Decoder[Videogame].widen,
      Decoder[Book].widen
    ).reduceLeft(_ or _).apply(cursor)
  }
}
