package schemas

import sttp.tapir.*
import sttp.tapir.generic.auto.*

import io.circe.generic.auto.*
import io.circe.syntax.*
import io.circe.Encoder
import io.circe.Decoder

import cats.syntax.functor.*

import modelClasses.app.media._
import modelClasses.ids.Media.*
import modelClasses.ids.Social.*

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

  implicit val unionEncoder1: Encoder[String | Long] = Encoder.instance {
    case string: String => string.asJson
    case long: Long => long.asJson
  }

  implicit val unionDecoder1: Decoder[String | Long] = Decoder.instance { cursor =>
    List[Decoder[String | Long]](
      Decoder[String].widen,
      Decoder[Long].widen
    ).reduceLeft(_ or _).apply(cursor)
  }

  // implicit val unionSchema1: Schema[String | Long] = Schema.derivedUnion
// 
  // implicit val unionEncoder2: Encoder[String | Long | Double] = Encoder.instance {
  //   case string: String => string.asJson
  //   case long: Long => long.asJson
  //   case double: Double => double.asJson
  // }
// 
  // implicit val unionDecoder2: Decoder[String | Long | Double] = Decoder.instance { cursor =>
  //   List[Decoder[String | Long | Double]](
  //     Decoder[String].widen,
  //     Decoder[Long].widen,
  //     Decoder[Double].widen
  //   ).reduceLeft(_ or _).apply(cursor)
  // }
// 
  // implicit val unionSchema2: Schema[String | Long | Double] = Schema.derivedUnion
// 
  // implicit val unionEncoder3: Encoder[String | Long | Double | Boolean] = Encoder.instance {
  //   case string: String => string.asJson
  //   case long: Long => long.asJson
  //   case double: Double => double.asJson
  //   case boolean: Boolean => boolean.asJson
  // }
// 
  // implicit val unionDecoder3: Decoder[String | Long | Double | Boolean] = Decoder.instance { cursor =>
  //   List[Decoder[String | Long | Double | Boolean]](
  //     Decoder[String].widen,
  //     Decoder[Long].widen,
  //     Decoder[Double].widen,
  //     Decoder[Boolean].widen
  //   ).reduceLeft(_ or _).apply(cursor)
  // }
// 
  // implicit val unionSchema3: Schema[String | Long | Double | Boolean] = Schema.derivedUnion
// 
  // implicit val unionEncoder4: Encoder[String | Long | Double | Boolean | List[Long]] = Encoder.instance {
  //   case string: String => string.asJson
  //   case long: Long => long.asJson
  //   case double: Double => double.asJson
  //   case boolean: Boolean => boolean.asJson
  //   case listLong: List[Long] => listLong.asJson
  // }
//// 
  // implicit val unionDecoder4: Decoder[String | Long | Double | Boolean | List[Long]] = Decoder.instance { cursor =>
  //   List[Decoder[String | Long | Double | Boolean | List[Long]]](
  //     Decoder[String].widen,
  //     Decoder[Long].widen,
  //     Decoder[Double].widen,
  //     Decoder[Boolean].widen,
  //     Decoder[List[Long]].widen
  //   ).reduceLeft(_ or _).apply(cursor)
  // }
// 
  // implicit val unionSchema4: Schema[String | Long | Double | Boolean | List[Long]] = Schema.derivedUnion
// 
  // implicit val unionEncoder5: Encoder[String | Long | Double | Boolean | List[Map[String, String | Long]]] = Encoder.instance {
  //   case string: String => string.asJson
  //   case long: Long => long.asJson
  //   case double: Double => double.asJson
  //   case boolean: Boolean => boolean.asJson
  //   case listMapStringLong: List[Map[String, String | Long]] => listMapStringLong.asJson
  // }
// 
  // implicit val unionDecoder5: Decoder[String | Long | Double | Boolean | List[Map[String, String | Long]]] = Decoder.instance { cursor =>
  //   List[Decoder[String | Long | Double | Boolean | List[Map[String, String | Long]]]](
  //     Decoder[String].widen,
  //     Decoder[Long].widen,
  //     Decoder[Double].widen,
  //     Decoder[Boolean].widen,
  //     Decoder[List[Map[String, String | Long]]].widen
  //   ).reduceLeft(_ or _).apply(cursor)
  // }
// 
  // implicit val unionSchema5: Schema[String | Long | Double | Boolean | List[Map[String, String | Long]]] = Schema.derivedUnion
// 
  // implicit val unionEncoder6: Encoder[String | Long | Double | List[Map[String, String | Long | Double | Boolean]]] = Encoder.instance {
  //   case string: String => string.asJson
  //   case long: Long => long.asJson
  //   case double: Double => double.asJson
  //   case listMapStringLongDoubleBoolean: List[Map[String, String | Long | Double | Boolean]] => listMapStringLongDoubleBoolean.asJson
  // }
// 
  // implicit val unionDecoder6: Decoder[String | Long | Double | List[Map[String, String | Long | Double | Boolean]]] = Decoder.instance { cursor =>
  //   List[Decoder[String | Long | Double | List[Map[String, String | Long | Double | Boolean]]]](
  //     Decoder[String].widen,
  //     Decoder[Long].widen,
  //     Decoder[Double].widen,
  //     Decoder[List[Map[String, String | Long | Double | Boolean]]].widen
  //   ).reduceLeft(_ or _).apply(cursor)
  // }
// 
  // implicit val unionSchema6: Schema[String | Long | Double | List[Map[String, String | Long | Double | Boolean]]] = Schema.derivedUnion
// 
  // implicit val unionEncoder7: Encoder[Long | List[Map[String, String | Long | Double | Boolean]]] = Encoder.instance {
  //   case long: Long => long.asJson
  //   case listMapStringLongDoubleBoolean: List[Map[String, String | Long | Double | Boolean]] => listMapStringLongDoubleBoolean.asJson
  // }
// 
  // implicit val unionDecoder7: Decoder[Long | List[Map[String, String | Long | Double | Boolean]]] = Decoder.instance { cursor =>
  //   List[Decoder[Long | List[Map[String, String | Long | Double | Boolean]]]](
  //     Decoder[Long].widen,
  //     Decoder[List[Map[String, String | Long | Double | Boolean]]].widen
  //   ).reduceLeft(_ or _).apply(cursor)
  // }
// 
  // implicit val unionSchema7: Schema[Long | List[Map[String, String | Long | Double | Boolean]]] = Schema.derivedUnion
// 
  // implicit val unionEncoder8: Encoder[Long | List[Map[String, String | Long | Double | Boolean]] | List[Map[String, String | Long | Double | Boolean | List[Map[String, String | Long]]]]] = Encoder.instance {
  //   case long: Long => long.asJson
  //   case listMapStringLongDoubleBoolean: List[Map[String, String | Long | Double | Boolean]] => listMapStringLongDoubleBoolean.asJson
  //   case secondList: List[Map[String, String | Long | Double | Boolean | List[Map[String, String | Long]]]] => secondList.asJson
  // }
// 
  // implicit val unionDecoder8: Decoder[Long | List[Map[String, String | Long | Double | Boolean]] | List[Map[String, String | Long | Double | Boolean | List[Map[String, String | Long]]]]] = Decoder.instance { cursor =>
  //   List[Decoder[Long | List[Map[String, String | Long | Double | Boolean]] | List[Map[String, String | Long | Double | Boolean | List[Map[String, String | Long]]]]]](
  //     Decoder[Long].widen,
  //     Decoder[List[Map[String, String | Long | Double | Boolean]]].widen,
  //     Decoder[List[Map[String, String | Long | Double | Boolean | List[Map[String, String | Long]]]]].widen
  //   ).reduceLeft(_ or _).apply(cursor)
  // }
// 
  // implicit val unionSchema8: Schema[Long | List[Map[String, String | Long | Double | Boolean]] | List[Map[String, String | Long | Double | Boolean | List[Map[String, String | Long]]]]] = Schema.derivedUnion
// 
  // implicit val unionEncoder9: Encoder[String | Long | List[Map[String, String | Long | Double | List[Map[String, String | Long | Double | Boolean]]]]] = Encoder.instance {
  //   case string: String => string.asJson
  //   case long: Long => long.asJson
  //   case list: List[Map[String, String | Long | Double | List[Map[String, String | Long | Double | Boolean]]]] => list.asJson
  // }
// 
  // implicit val unionDecoder9: Decoder[String | Long | List[Map[String, String | Long | Double | List[Map[String, String | Long | Double | Boolean]]]]] = Decoder.instance { cursor =>
  //   List[Decoder[String | Long | List[Map[String, String | Long | Double | List[Map[String, String | Long | Double | Boolean]]]]]](
  //     Decoder[String].widen,
  //     Decoder[Long].widen,
  //     Decoder[List[Map[String, String | Long | Double | List[Map[String, String | Long | Double | Boolean]]]]].widen
  //   ).reduceLeft(_ or _).apply(cursor)
  // }
// 
  // implicit val unionSchema9: Schema[String | Long | List[Map[String, String | Long | Double | List[Map[String, String | Long | Double | Boolean]]]]] = Schema.derivedUnion
// 
  // implicit val unionEncoder10: Encoder[String | Long | Double | Boolean | List[String] | List[Long]] = Encoder.instance {
  //   case string: String => string.asJson
  //   case long: Long => long.asJson
  //   case double: Double => double.asJson
  //   case boolean: Boolean => boolean.asJson
  //   case stringList: List[String] => stringList.asJson
  //   case longList: List[Long] => longList.asJson
  // }
// 
  // implicit val unionDecoder10: Decoder[String | Long | Double | Boolean | List[String] | List[Long]] = Decoder.instance { cursor =>
  //   List[Decoder[String | Long | Double | Boolean | List[String] | List[Long]]](
  //     Decoder[String].widen,
  //     Decoder[Long].widen,
  //     Decoder[Double].widen,
  //     Decoder[Boolean].widen,
  //     Decoder[List[String]].widen,
  //     Decoder[List[Long]].widen,
  //   ).reduceLeft(_ or _).apply(cursor)
  // }
  //  
  // implicit val unionSchema10: Schema[String | Long | Double | Boolean | List[String] | List[Long]] = Schema.derivedUnion

}
