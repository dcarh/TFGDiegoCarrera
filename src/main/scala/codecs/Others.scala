package codecs

import sttp.tapir.{Codec, CodecFormat, DecodeResult}
import sttp.tapir.CodecFormat.TextPlain

object Others {

  implicit val categories2Codec: Codec[String, Option[List[String]], TextPlain] =
    Codec.string.mapDecode(s => DecodeResult.Value {
      if (s.isEmpty) None else Some(s.split(",").toList)
    })(
      opt => opt.map(_.mkString(",")).getOrElse("")
    )

}
