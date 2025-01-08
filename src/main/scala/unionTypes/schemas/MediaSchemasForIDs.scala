package unionTypes.schemas

import sttp.tapir.*
import sttp.tapir.generic.auto.*

import modelClasses.ids.Media.*

object MediaSchemasForIDs {
  
  implicit val listMediaUnionSchema: Schema[MovieId | TvShowId  | VideogameId | BookId] = Schema.derivedUnion
  
  implicit val listMediaUnionSchema2: Schema[MovieId | TvShowId | (TvShowId, SeasonNumber) | (TvShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId] = Schema.derivedUnion
  
  implicit val listMediaUnionSchema3: Schema[TvShowId | (TvShowId, SeasonNumber) | VideogameId | BookId] = Schema.derivedUnion
}
