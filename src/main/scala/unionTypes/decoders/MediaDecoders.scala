package unionTypes.decoders

import io.circe.generic.auto.*
import io.circe.Decoder

import cats.syntax.functor.*

import modelClasses.app.media.*

object MediaDecoders {

  implicit val favouritesMediaUnionDecoder: Decoder[Movie | TVShow | Videogame | Book] = Decoder.instance { cursor =>
    List[Decoder[Movie | TVShow | Videogame | Book]](
      Decoder[Movie].widen,
      Decoder[TVShow].widen,
      Decoder[Videogame].widen,
      Decoder[Book].widen
    ).reduceLeft(_ or _).apply(cursor)
  }

  implicit val progressMediaUnionDecoder: Decoder[TVShow | Season | Videogame | Book] = Decoder.instance { cursor =>
    List[Decoder[TVShow | Season | Videogame | Book]](
      Decoder[TVShow].widen,
      Decoder[Season].widen,
      Decoder[Videogame].widen,
      Decoder[Book].widen
    ).reduceLeft(_ or _).apply(cursor)
  }

  implicit val pendingMediaUnionDecoder: Decoder[Movie | TVShow | Season | Videogame | Book] = Decoder.instance { cursor =>
    List[Decoder[Movie | TVShow | Season | Videogame | Book]](
      Decoder[Movie].widen,
      Decoder[TVShow].widen,
      Decoder[Season].widen,
      Decoder[Videogame].widen,
      Decoder[Book].widen
    ).reduceLeft(_ or _).apply(cursor)
  }

}
