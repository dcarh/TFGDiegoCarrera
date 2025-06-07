package domain.app.social

import io.circe.{Decoder, Encoder}
import sttp.tapir.Schema

sealed trait Visibility
object Visibility {
  case object Public extends Visibility
  case object Followers extends Visibility
  case object Private extends Visibility

  implicit val visibilityEncoder: Encoder[Visibility] = Encoder.encodeString.contramap {
    case Public => "Public"
    case Followers => "Followers"
    case Private => "Private"
  }

  implicit val visibilityDecoder: Decoder[Visibility] = Decoder.decodeString.emap {
    case "Public" => Right(Public)
    case "Followers" => Right(Followers)
    case "Private" => Right(Private)
    case other => Left(s"Invalid visibility value: $other")
  }

  // Schema para Visibility (necesario en Tapir para la documentación)
  implicit val visibilitySchema: Schema[Visibility] = Schema.string
    .description("Visibility level for media lists")
    .encodedExample("Public")
}
