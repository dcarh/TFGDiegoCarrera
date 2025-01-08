package unionTypes.encoders

import io.circe.generic.auto.*
import io.circe.syntax.*
import io.circe.Encoder

import modelClasses.app.media.*

object MediaEncoders {
  
  implicit val favouritesMediaUnionEncoder: Encoder[Movie | TvShow | Videogame | Book] = Encoder.instance {
    case movie: Movie => movie.asJson
    case tvShow: TvShow => tvShow.asJson
    case videogame: Videogame => videogame.asJson
    case book: Book => book.asJson
  }

  implicit val progressMediaUnionEncoder: Encoder[TvShow | Season | Videogame | Book] = Encoder.instance {
    case tvShow: TvShow => tvShow.asJson
    case season: Season => season.asJson
    case videogame: Videogame => videogame.asJson
    case book: Book => book.asJson
  }

  implicit val pendingMediaUnionEncoder: Encoder[Movie | TvShow | Season | Videogame | Book] = Encoder.instance {
    case movie: Movie => movie.asJson
    case tvShow: TvShow => tvShow.asJson
    case season: Season => season.asJson
    case videogame: Videogame => videogame.asJson
    case book: Book => book.asJson
  }
}
