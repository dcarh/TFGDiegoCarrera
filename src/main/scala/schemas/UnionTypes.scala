package schemas

import sttp.tapir.*
import sttp.tapir.generic.auto.*
import io.circe.generic.auto.*
import io.circe.syntax.*
import io.circe.Encoder
import io.circe.Decoder
import cats.syntax.functor.*
import modelClasses.app.media._

object UnionTypes {

  implicit val mediaUnionEncoder: Encoder[Movie | TVShow | Season | Episode | Videogame | Book] = Encoder.instance {
    case movie: Movie => movie.asJson
    case tvShow: TVShow => tvShow.asJson
    case season: Season => season.asJson
    case episode: Episode => episode.asJson
    case videogame: Videogame => videogame.asJson
    case book: Book => book.asJson
  }
  
  implicit val mediaUnionDecoder: Decoder[Movie | TVShow | Season | Episode | Videogame | Book] = Decoder.instance { cursor =>
    List[Decoder[Movie | TVShow | Season | Episode | Videogame | Book]](
      Decoder[Movie].widen,
      Decoder[TVShow].widen,
      Decoder[Season].widen,
      Decoder[Episode].widen,
      Decoder[Videogame].widen,
      Decoder[Book].widen
    ).reduceLeft(_ or _).apply(cursor)
  }

  implicit val mediaUnionSchema: Schema[Movie | TVShow | Season | Episode | Videogame | Book] = Schema.derivedUnion

  implicit val mediaUnionEncoder2: Encoder[Movie | TVShow | Videogame | Book] = Encoder.instance {
    case movie: Movie => movie.asJson
    case tvShow: TVShow => tvShow.asJson
    case videogame: Videogame => videogame.asJson
    case book: Book => book.asJson
  }

  implicit val mediaUnionDecoder2: Decoder[Movie | TVShow | Videogame | Book] = Decoder.instance { cursor =>
    List[Decoder[Movie | TVShow | Videogame | Book]](
      Decoder[Movie].widen,
      Decoder[TVShow].widen,
      Decoder[Videogame].widen,
      Decoder[Book].widen
    ).reduceLeft(_ or _).apply(cursor)
  }
  
  implicit val mediaUnionSchema2: Schema[Movie | TVShow | Videogame | Book] = Schema.derivedUnion

  implicit val mediaUnionEncoder3: Encoder[Movie.Id | TVShow.Id | (TVShow.Id, Season.Number) | (TVShow.Id, Season.Number, Episode.Number) | Videogame.Id | Book.Id] = Encoder.instance {
    case movieId: Movie.Id => movieId.asJson
    case tvShowId: TVShow.Id => tvShowId.asJson
    case seasonNumber: (TVShow.Id, Season.Number) => seasonNumber.asJson
    case episodeNumber: (TVShow.Id, Season.Number, Episode.Number) => episodeNumber.asJson
    case videogameId: Videogame.Id => videogameId.asJson
    case bookId: Book.Id => bookId.asJson
  }

  implicit val mediaUnionDecoder3: Decoder[Movie.Id | TVShow.Id | (TVShow.Id, Season.Number) | (TVShow.Id, Season.Number, Episode.Number) | Videogame.Id | Book.Id] = Decoder.instance { cursor =>
    List[Decoder[Movie.Id | TVShow.Id | (TVShow.Id, Season.Number) | (TVShow.Id, Season.Number, Episode.Number) | Videogame.Id | Book.Id]](
      Decoder[Movie.Id].widen,
      Decoder[TVShow.Id].widen,
      Decoder[(TVShow.Id, Season.Number)].widen,
      Decoder[(TVShow.Id, Season.Number, Episode.Number)].widen,
      Decoder[Videogame.Id].widen,
      Decoder[Book.Id].widen
    ).reduceLeft(_ or _).apply(cursor)
  }

  implicit val mediaUnionSchema3: Schema[Movie.Id | TVShow.Id | (TVShow.Id, Season.Number) | (TVShow.Id, Season.Number, Episode.Number) | Videogame.Id | Book.Id] = Schema.derivedUnion

  implicit val unionEncoder1: Encoder[String | Int] = Encoder.instance {
    case string: String => string.asJson
    case int: Int => int.asJson
  }

  implicit val unionDecoder1: Decoder[String | Int] = Decoder.instance { cursor =>
    List[Decoder[String | Int]](
      Decoder[String].widen,
      Decoder[Int].widen
    ).reduceLeft(_ or _).apply(cursor)
  }

  implicit val unionSchema1: Schema[String | Int] = Schema.derivedUnion

  implicit val unionEncoder2: Encoder[String | Int | Double] = Encoder.instance {
    case string: String => string.asJson
    case int: Int => int.asJson
    case double: Double => double.asJson
  }

  implicit val unionDecoder2: Decoder[String | Int | Double] = Decoder.instance { cursor =>
    List[Decoder[String | Int | Double]](
      Decoder[String].widen,
      Decoder[Int].widen,
      Decoder[Double].widen
    ).reduceLeft(_ or _).apply(cursor)
  }

  implicit val unionSchema2: Schema[String | Int | Double] = Schema.derivedUnion

  implicit val unionEncoder3: Encoder[String | Int | Double | Boolean] = Encoder.instance {
    case string: String => string.asJson
    case int: Int => int.asJson
    case double: Double => double.asJson
    case boolean: Boolean => boolean.asJson
  }

  implicit val unionDecoder3: Decoder[String | Int | Double | Boolean] = Decoder.instance { cursor =>
    List[Decoder[String | Int | Double | Boolean]](
      Decoder[String].widen,
      Decoder[Int].widen,
      Decoder[Double].widen,
      Decoder[Boolean].widen
    ).reduceLeft(_ or _).apply(cursor)
  }

  implicit val unionSchema3: Schema[String | Int | Double | Boolean] = Schema.derivedUnion

  implicit val unionEncoder4: Encoder[String | Int | Double | Boolean | List[Int]] = Encoder.instance {
    case string: String => string.asJson
    case int: Int => int.asJson
    case double: Double => double.asJson
    case boolean: Boolean => boolean.asJson
    case listInt: List[Int] => listInt.asJson
  }

  implicit val unionDecoder4: Decoder[String | Int | Double | Boolean | List[Int]] = Decoder.instance { cursor =>
    List[Decoder[String | Int | Double | Boolean | List[Int]]](
      Decoder[String].widen,
      Decoder[Int].widen,
      Decoder[Double].widen,
      Decoder[Boolean].widen,
      Decoder[List[Int]].widen
    ).reduceLeft(_ or _).apply(cursor)
  }

  implicit val unionSchema4: Schema[String | Int | Double | Boolean | List[Int]] = Schema.derivedUnion

  implicit val unionEncoder5: Encoder[String | Int | Double | Boolean | List[Map[String, String | Int]]] = Encoder.instance {
    case string: String => string.asJson
    case int: Int => int.asJson
    case double: Double => double.asJson
    case boolean: Boolean => boolean.asJson
    case listMapStringInt: List[Map[String, String | Int]] => listMapStringInt.asJson
  }

  implicit val unionDecoder5: Decoder[String | Int | Double | Boolean | List[Map[String, String | Int]]] = Decoder.instance { cursor =>
    List[Decoder[String | Int | Double | Boolean | List[Map[String, String | Int]]]](
      Decoder[String].widen,
      Decoder[Int].widen,
      Decoder[Double].widen,
      Decoder[Boolean].widen,
      Decoder[List[Map[String, String | Int]]].widen
    ).reduceLeft(_ or _).apply(cursor)
  }

  implicit val unionSchema5: Schema[String | Int | Double | Boolean | List[Map[String, String | Int]]] = Schema.derivedUnion

  implicit val unionEncoder6: Encoder[String | Int | Double | List[Map[String, String | Int | Double | Boolean]]] = Encoder.instance {
    case string: String => string.asJson
    case int: Int => int.asJson
    case double: Double => double.asJson
    case listMapStringIntDoubleBoolean: List[Map[String, String | Int | Double | Boolean]] => listMapStringIntDoubleBoolean.asJson
  }

  implicit val unionDecoder6: Decoder[String | Int | Double | List[Map[String, String | Int | Double | Boolean]]] = Decoder.instance { cursor =>
    List[Decoder[String | Int | Double | List[Map[String, String | Int | Double | Boolean]]]](
      Decoder[String].widen,
      Decoder[Int].widen,
      Decoder[Double].widen,
      Decoder[List[Map[String, String | Int | Double | Boolean]]].widen
    ).reduceLeft(_ or _).apply(cursor)
  }

  implicit val unionSchema6: Schema[String | Int | Double | List[Map[String, String | Int | Double | Boolean]]] = Schema.derivedUnion

}
