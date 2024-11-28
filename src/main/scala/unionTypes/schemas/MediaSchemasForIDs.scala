package unionTypes.schemas

import sttp.tapir.*
import sttp.tapir.generic.auto.*

import modelClasses.ids.Media.*

object MediaSchemasForIDs {
  
  implicit val listMediaUnionSchema: Schema[MovieId | TVShowId  | VideogameId | BookId] = Schema.derivedUnion


  implicit val mediaUnionSchema3: Schema[MovieId | TVShowId | (TVShowId, SeasonNumber) | (TVShowId, SeasonNumber, EpisodeNumber) | VideogameId | BookId] = Schema.derivedUnion
}
