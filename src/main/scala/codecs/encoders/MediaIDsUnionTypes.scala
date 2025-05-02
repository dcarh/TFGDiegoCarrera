package codecs.encoders

import io.circe.syntax.*
import io.circe.{Encoder, Json}

import modelClasses.ids.Media.*

object MediaIDsUnionTypes {

  implicit val listMediaUnionEncoder: Encoder[MovieId | TvShowId | VideogameId | BookId] = Encoder.instance {
    case movieId: MovieId => Json.obj("type" -> "MovieId".asJson, "value" -> movieId.value.asJson)
    case tvShowId: TvShowId => Json.obj("type" -> "TvShowId".asJson, "value" -> tvShowId.value.asJson)
    case videogameId: VideogameId => Json.obj("type" -> "VideogameId".asJson, "value" -> videogameId.value.asJson)
    case bookId: BookId => Json.obj("type" -> "BookId".asJson, "value" -> bookId.value.asJson)
  }

  implicit val listMediaUnionEncoder2: Encoder[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId] = Encoder.instance {
    case movieId: MovieId => Json.obj("type" -> "MovieId".asJson, "value" -> movieId.value.asJson)
    case tvShowId: TvShowId => Json.obj("type" -> "TvShowId".asJson, "value" -> tvShowId.value.asJson)
    case (tvShowId: TvShowId, seasonNumber: TvSeasonNumber) =>
      Json.obj(
        "type" -> "SeasonNumber".asJson,
        "value" -> Json.obj(
          "tvShowId" -> tvShowId.value.asJson,
          "seasonNumber" -> seasonNumber.value.asJson
        )
      )
    case (tvShowId: TvShowId, seasonNumber: TvSeasonNumber, episodeNumber: TvEpisodeNumber) =>
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

  implicit val listMediaUnionEncoder3: Encoder[TvShowId | (TvShowId, TvSeasonNumber) | VideogameId | BookId] = Encoder.instance {
    case tvShowId: TvShowId => Json.obj("type" -> "TvShowId".asJson, "value" -> tvShowId.value.asJson)
    case (tvShowId: TvShowId, seasonNumber: TvSeasonNumber) =>
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
