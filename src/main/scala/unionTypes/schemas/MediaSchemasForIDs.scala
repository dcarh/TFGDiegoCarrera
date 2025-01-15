package unionTypes.schemas

import sttp.tapir.*
import sttp.tapir.generic.auto.*

import modelClasses.ids.Media.*

object MediaSchemasForIDs {

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
  
  private val extractDiscriminator2: (MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId) => String = {
    case _: MovieId => "MovieId"
    case _: TvShowId => "TvShowId"
    case _: (TvShowId, SeasonNumber) => "SeasonNumber"
    case _: (TvShowId, SeasonNumber, EpisodeNumber) => "EpisodeNumber"
    case _: VideogameId => "VideogameId"
    case _: BookId => "BookId"
  }

  implicit val listMediaUnionSchema22: Schema[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId] =
    Schema.oneOfUsingField[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId, String](
      extractDiscriminator2,
      identity
    )(
      "MovieId" -> Schema.derived[MovieId],
      "TvShowId" -> Schema.derived[TvShowId],
      "SeasonNumber" -> Schema.derived[(TvShowId, SeasonNumber)],
      "EpisodeNumber" -> Schema.derived[(TvShowId, SeasonNumber, EpisodeNumber)],
      "VideogameId" -> Schema.derived[VideogameId],
      "BookId" -> Schema.derived[BookId]
    )
  
  private val extractDiscriminator3: (TvShowId | (TvShowId, SeasonNumber) | VideogameId | BookId) => String = {
    case _: TvShowId => "TvShowId"
    case _: (TvShowId, SeasonNumber) => "SeasonNumber"
    case _: VideogameId => "VideogameId"
    case _: BookId => "BookId"
  }

  implicit val listMediaUnionSchema3: Schema[TvShowId | (TvShowId, SeasonNumber) | VideogameId | BookId] =
    Schema.oneOfUsingField[TvShowId | (TvShowId, SeasonNumber) | VideogameId | BookId, String](
      extractDiscriminator3,
      identity
    )(
      "TvShowId" -> Schema.derived[TvShowId],
      "SeasonNumber" -> Schema.derived[(TvShowId, SeasonNumber)],
      "VideogameId" -> Schema.derived[VideogameId],
      "BookId" -> Schema.derived[BookId]
    )
}
