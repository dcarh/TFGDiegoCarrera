package unionTypes.schemas

import sttp.tapir.*
import sttp.tapir.generic.auto.*

import modelClasses.app.media.*

object MediaSchemas {

  implicit val favouritesMediaUnionSchema: Schema[Movie | TVShow | Videogame | Book] = Schema.derivedUnion

  implicit val progressMediaUnionSchema: Schema[TVShow | Season | Videogame | Book] = Schema.derivedUnion

  implicit val pendingMediaUnionSchema: Schema[Movie | TVShow | Season | Videogame | Book] = Schema.derivedUnion

}

