package unionTypes.encoders

import io.circe.generic.auto.*
import io.circe.syntax.*
import io.circe.Encoder

import modelClasses.app.media.*

object MediaEncodersAll {

  implicit val allMediaUnionEncoder: Encoder[Movie | TvShow | Season | Episode | Videogame | Book] = Encoder.instance {
    case movie: Movie => movie.asJson
    case tvShow: TvShow => tvShow.asJson
    case season: Season => season.asJson
    case episode: Episode => episode.asJson
    case videogame: Videogame => videogame.asJson
    case book: Book => book.asJson
  }
}
