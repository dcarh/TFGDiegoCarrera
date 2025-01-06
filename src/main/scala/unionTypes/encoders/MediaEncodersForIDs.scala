package unionTypes.encoders

import io.circe.generic.auto.*
import io.circe.syntax.*
import io.circe.Encoder

import modelClasses.ids.Media.*

object MediaEncodersForIDs {

  implicit val listMediaUnionEncoder: Encoder[MovieId | TVShowId | VideogameId | BookId] = Encoder.instance {
    case movieId: MovieId => movieId.asJson
    case tvShowId: TVShowId => tvShowId.asJson
    case videogameId: VideogameId => videogameId.asJson
    case bookId: BookId => bookId.asJson
  }
  
  implicit val listMediaUnionEncoder2: Encoder[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId] = Encoder.instance {
    case movieId: MovieId => movieId.asJson
    case tvShowId: TVShowId => tvShowId.asJson
    case seasonNumber: (TVShowId, SeasonNumber) => seasonNumber.asJson
    case episodeNumber: (TVShowId, SeasonNumber, EpisodeNumber) => episodeNumber.asJson
    case videogameId: VideogameId => videogameId.asJson
    case bookId: BookId => bookId.asJson
  }
  
  implicit val listMediaUnionEncoder3: Encoder[TVShowId | (TVShowId, SeasonNumber) | VideogameId | BookId] = Encoder.instance {
    case tvShowId: TVShowId => tvShowId.asJson
    case seasonNumber: (TVShowId, SeasonNumber) => seasonNumber.asJson
    case videogameId: VideogameId => videogameId.asJson
    case bookId: BookId => bookId.asJson
  }
}
