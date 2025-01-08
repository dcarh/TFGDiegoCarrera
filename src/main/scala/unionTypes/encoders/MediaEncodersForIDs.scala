package unionTypes.encoders

import io.circe.generic.auto.*
import io.circe.syntax.*
import io.circe.Encoder

import modelClasses.ids.Media.*

object MediaEncodersForIDs {

  implicit val listMediaUnionEncoder: Encoder[MovieId | TvShowId | VideogameId | BookId] = Encoder.instance {
    case movieId: MovieId => movieId.asJson
    case tvShowId: TvShowId => tvShowId.asJson
    case videogameId: VideogameId => videogameId.asJson
    case bookId: BookId => bookId.asJson
  }
  
  implicit val listMediaUnionEncoder2: Encoder[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId] = Encoder.instance {
    case movieId: MovieId => movieId.asJson
    case tvShowId: TvShowId => tvShowId.asJson
    case seasonNumber: (TvShowId, SeasonNumber) => seasonNumber.asJson
    case episodeNumber: (TvShowId, SeasonNumber, EpisodeNumber) => episodeNumber.asJson
    case videogameId: VideogameId => videogameId.asJson
    case bookId: BookId => bookId.asJson
  }
  
  implicit val listMediaUnionEncoder3: Encoder[TvShowId | (TvShowId, SeasonNumber) | VideogameId | BookId] = Encoder.instance {
    case tvShowId: TvShowId => tvShowId.asJson
    case seasonNumber: (TvShowId, SeasonNumber) => seasonNumber.asJson
    case videogameId: VideogameId => videogameId.asJson
    case bookId: BookId => bookId.asJson
  }
}
