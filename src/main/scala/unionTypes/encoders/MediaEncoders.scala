package unionTypes.encoders

import io.circe.generic.auto.*
import io.circe.syntax.*
import io.circe.Encoder

import modelClasses.app.media.*

object MediaEncoders {
  
  implicit val favouritesMediaUnionEncoder: Encoder[Movie | TVShow | Videogame | Book] = Encoder.instance {
    case movie: Movie => movie.asJson
    case tvShow: TVShow => tvShow.asJson
    case videogame: Videogame => videogame.asJson
    case book: Book => book.asJson
  }

  implicit val progressMediaUnionEncoder: Encoder[TVShow | Season | Videogame | Book] = Encoder.instance {
    case tvShow: TVShow => tvShow.asJson
    case season: Season => season.asJson
    case videogame: Videogame => videogame.asJson
    case book: Book => book.asJson
  }

  implicit val pendingMediaUnionEncoder: Encoder[Movie | TVShow | Season | Videogame | Book] = Encoder.instance {
    case movie: Movie => movie.asJson
    case tvShow: TVShow => tvShow.asJson
    case season: Season => season.asJson
    case videogame: Videogame => videogame.asJson
    case book: Book => book.asJson
  }
}
