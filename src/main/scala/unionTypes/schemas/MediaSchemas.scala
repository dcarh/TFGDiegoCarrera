package unionTypes.schemas

import sttp.tapir.*
import sttp.tapir.generic.auto.*

import modelClasses.app.media.*

object MediaSchemas {

  implicit val favouritesMediaUnionSchema: Schema[Movie | TvShow | Videogame | Book] = Schema.derivedUnion

  implicit val progressMediaUnionSchema: Schema[TvShow | TvSeason | Videogame | Book] = Schema.derivedUnion

  implicit val pendingMediaUnionSchema: Schema[Movie | TvShow | TvSeason | Videogame | Book] = Schema.derivedUnion

}

