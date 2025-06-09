package codecs.schemas

import sttp.tapir.*
import sttp.tapir.generic.auto.*

import domain.ids.Media.*

object MediaIDsUnionTypes {

  private val extractDiscriminator: (MovieId | TvShowId | VideogameId | BookId) => String = {
    case _: MovieId => "MovieId"
    case _: TvShowId => "TvShowId"
    case _: VideogameId => "VideogameId"
    case _: BookId => "BookId"
  }

  implicit val listMediaUnionSchema: Schema[MovieId | TvShowId | VideogameId | BookId] =
    Schema.oneOfUsingField[MovieId | TvShowId | VideogameId | BookId, String](
      extractDiscriminator,
      identity
    )(
      "MovieId" -> Schema.derived[MovieId],
      "TvShowId" -> Schema.derived[TvShowId],
      "VideogameId" -> Schema.derived[VideogameId],
      "BookId" -> Schema.derived[BookId]
    )
  
  private val extractDiscriminator2: (MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId) => String = {
    case _: MovieId => "MovieId"
    case _: TvShowId => "TvShowId"
    case _: (TvShowId, TvSeasonNumber) => "SeasonNumber"
    case _: (TvShowId, TvSeasonNumber, TvEpisodeNumber) => "EpisodeNumber"
    case _: VideogameId => "VideogameId"
    case _: BookId => "BookId"
  }

  implicit val listMediaUnionSchema2: Schema[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId] =
    Schema.oneOfUsingField[MovieId | TvShowId | (TvShowId, TvSeasonNumber) | (TvShowId, TvSeasonNumber, TvEpisodeNumber) | VideogameId | BookId, String](
      extractDiscriminator2,
      identity
    )(
      "MovieId" -> Schema.derived[MovieId],
      "TvShowId" -> Schema.derived[TvShowId],
      "SeasonNumber" -> Schema.derived[(TvShowId, TvSeasonNumber)],
      "EpisodeNumber" -> Schema.derived[(TvShowId, TvSeasonNumber, TvEpisodeNumber)],
      "VideogameId" -> Schema.derived[VideogameId],
      "BookId" -> Schema.derived[BookId]
    )
  
  private val extractDiscriminator3: (TvShowId | (TvShowId, TvSeasonNumber) | VideogameId | BookId) => String = {
    case _: TvShowId => "TvShowId"
    case _: (TvShowId, TvSeasonNumber) => "SeasonNumber"
    case _: VideogameId => "VideogameId"
    case _: BookId => "BookId"
  }

  implicit val listMediaUnionSchema3: Schema[TvShowId | (TvShowId, TvSeasonNumber) | VideogameId | BookId] =
    Schema.oneOfUsingField[TvShowId | (TvShowId, TvSeasonNumber) | VideogameId | BookId, String](
      extractDiscriminator3,
      identity
    )(
      "TvShowId" -> Schema.derived[TvShowId],
      "SeasonNumber" -> Schema.derived[(TvShowId, TvSeasonNumber)],
      "VideogameId" -> Schema.derived[VideogameId],
      "BookId" -> Schema.derived[BookId]
    )
}
