package codecs

import sttp.tapir.{Codec, CodecFormat, DecodeResult}
import sttp.tapir.CodecFormat.TextPlain

object Others {

//  implicit val categoriesCodec: Codec[String, Option[List[String]], TextPlain] =
//    Codec.string.mapDecode {
//      case null | "" => DecodeResult.Value(None) // Ausencia o valor vacío -> None
//      case s         => DecodeResult.Value(Some(s.split(",").toList))
//    }(
//      opt => opt.map(_.mkString(",")).getOrElse("")
//    )

  implicit val categoriesCodec: Codec[String, Option[List[String]], TextPlain] =
    Codec.string.mapDecode(s => DecodeResult.Value {
      if (s.isEmpty) None else Some(s.split(",").toList)
    })(
      opt => opt.map(_.mkString(",")).getOrElse("")
    )

}
