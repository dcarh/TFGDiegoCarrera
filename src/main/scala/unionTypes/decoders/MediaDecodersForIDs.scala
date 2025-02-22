package unionTypes.decoders

import io.circe.{Decoder, DecodingFailure}

import modelClasses.ids.Media.*

object MediaDecodersForIDs {

  implicit val listMediaUnionDecoder: Decoder[MovieId | TvShowId | VideogameId | BookId] = Decoder.instance { cursor =>
    cursor.downField("type").as[String].flatMap {
      case "MovieId" => cursor.downField("value").as[Long].map(MovieId.apply)
      case "TvShowId" => cursor.downField("value").as[Long].map(TvShowId.apply)
      case "VideogameId" => cursor.downField("value").as[Long].map(VideogameId.apply)
      case "BookId" => cursor.downField("value").as[String].map(BookId.apply)
      case other => Left(DecodingFailure(s"Unknown type: $other", cursor.history))
    }
  }

  implicit val listMediaUnionDecoder2: Decoder[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId] = Decoder.instance { cursor =>
    cursor.downField("type").as[String].flatMap {
      case "MovieId" => cursor.downField("value").as[Long].map(MovieId.apply)
      case "TvShowId" => cursor.downField("value").as[Long].map(TvShowId.apply)
      case "SeasonNumber" =>
        cursor.downField("value").as[Map[String, Long]].map { values =>
          (TvShowId(values("tvShowId")), TvSeasonNumber(values("seasonNumber")))
        }

      case "EpisodeNumber" =>
        cursor.downField("value").as[Map[String, Long]].map { values =>
          (
            TvShowId(values("tvShowId")),
            TvSeasonNumber(values("seasonNumber")),
            TvEpisodeNumber(values("episodeNumber"))
          )
        }
      case "VideogameId" => cursor.downField("value").as[Long].map(VideogameId.apply)
      case "BookId" => cursor.downField("value").as[String].map(BookId.apply)
      case other => Left(DecodingFailure(s"Unknown type: $other", cursor.history))
    }
  }

  implicit val listMediaUnionDecoder3: Decoder[TvShowId | (TvShowId, TvSeasonNumber) | VideogameId | BookId] = Decoder.instance { cursor =>
    cursor.downField("type").as[String].flatMap {
      case "TvShowId" => cursor.downField("value").as[Long].map(TvShowId.apply)
      case "SeasonNumber" => cursor.downField("value").as[List[Map[String, Long]]].map(
        value =>
          (
            TvShowId(value.head.apply("tvShowId")),
            TvSeasonNumber(value(1).apply("seasonNumber"))
          )
      )
      case "VideogameId" => cursor.downField("value").as[Long].map(VideogameId.apply)
      case "BookId" => cursor.downField("value").as[String].map(BookId.apply)
      case other => Left(DecodingFailure(s"Unknown type: $other", cursor.history))
    }
  }
}
