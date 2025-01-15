package unionTypes.encoders

import io.circe.syntax.*
import io.circe.{Encoder, Json}

import modelClasses.ids.Media.*

object MediaEncodersForIDs {

  implicit val listMediaUnionEncoder: Encoder[MovieId | TvShowId | VideogameId | BookId] = Encoder.instance {
    case movieId: MovieId => Json.obj("type" -> "MovieId".asJson, "value" -> movieId.value.asJson)
    case tvShowId: TvShowId => Json.obj("type" -> "TvShowId".asJson, "value" -> tvShowId.value.asJson)
    case videogameId: VideogameId => Json.obj("type" -> "VideogameId".asJson, "value" -> videogameId.value.asJson)
    case bookId: BookId => Json.obj("type" -> "BookId".asJson, "value" -> bookId.value.asJson)
  }

  implicit val listMediaUnionEncoder2: Encoder[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId] = Encoder.instance {
    case movieId: MovieId => Json.obj("type" -> "MovieId".asJson, "value" -> movieId.value.asJson)
    case tvShowId: TvShowId => Json.obj("type" -> "TvShowId".asJson, "value" -> tvShowId.value.asJson)
    case (tvShowId: TvShowId, seasonNumber: SeasonNumber) =>
      Json.obj(
        "type" -> "SeasonNumber".asJson,
        "value" -> Json.obj(
          "tvShowId" -> tvShowId.value.asJson,
          "seasonNumber" -> seasonNumber.value.asJson
        )
      )
    case (tvShowId: TvShowId, seasonNumber: SeasonNumber, episodeNumber: EpisodeNumber) =>
      Json.obj(
        "type" -> "EpisodeNumber".asJson,
        "value" -> Json.obj(
          "tvShowId" -> tvShowId.value.asJson,
          "seasonNumber" -> seasonNumber.value.asJson,
          "episodeNumber" -> episodeNumber.value.asJson
        )
      )
    case videogameId: VideogameId => Json.obj("type" -> "VideogameId".asJson, "value" -> videogameId.value.asJson)
    case bookId: BookId => Json.obj("type" -> "BookId".asJson, "value" -> bookId.value.asJson)
  }

//  implicit val listMediaUnionEncoder2: Encoder[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId] = Encoder.instance {
//    case movieId: MovieId => Json.obj("type" -> "MovieId".asJson, "value" -> movieId.value.asJson)
//    case tvShowId: TvShowId => Json.obj("type" -> "TvShowId".asJson, "value" -> tvShowId.value.asJson)
//    case seasonNumber: (TvShowId, SeasonNumber) => Json.obj("type" -> "SeasonNumber".asJson, "value" -> seasonNumber.asJson)
//    case episodeNumber: (TvShowId, SeasonNumber, EpisodeNumber) => Json.obj("type" -> "EpisodeNumber".asJson, "value" -> episodeNumber.asJson)
//    case videogameId: VideogameId => Json.obj("type" -> "VideogameId".asJson, "value" -> videogameId.value.asJson)
//    case bookId: BookId => Json.obj("type" -> "BookId".asJson, "value" -> bookId.value.asJson)
//  }

  implicit val listMediaUnionEncoder3: Encoder[TvShowId | (TvShowId, SeasonNumber) | VideogameId | BookId] = Encoder.instance {
    case tvShowId: TvShowId => Json.obj("type" -> "TvShowId".asJson, "value" -> tvShowId.value.asJson)
    case (tvShowId: TvShowId, seasonNumber: SeasonNumber) =>
      Json.obj(
        "type" -> "SeasonNumber".asJson,
        "value" -> Json.obj(
          "tvShowId" -> tvShowId.value.asJson,
          "seasonNumber" -> seasonNumber.value.asJson
        )
      )
    case videogameId: VideogameId => Json.obj("type" -> "VideogameId".asJson, "value" -> videogameId.value.asJson)
    case bookId: BookId => Json.obj("type" -> "BookId".asJson, "value" -> bookId.value.asJson)
  }
}
