package unionTypes.schemas

import sttp.tapir.*
import sttp.tapir.generic.auto.*

import modelClasses.app.media.*

object MediaSchemasAll {

  implicit val allMediaUnionSchema: Schema[Movie | TvShow | TvSeason | TvEpisode | Videogame | Book] = Schema.derivedUnion

}

