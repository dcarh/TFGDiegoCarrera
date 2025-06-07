package codecs.schemas

import sttp.tapir.*
import sttp.tapir.generic.auto.*

import domain.app.media.*

object MediaUnionTypes {

  implicit val movieSchema: Schema[Movie] = Schema.derived[Movie]
  implicit val tvShowSchema: Schema[TvShow] = Schema.derived[TvShow]
  implicit val videogameSchema: Schema[Videogame] = Schema.derived[Videogame]
  implicit val bookSchema: Schema[Book] = Schema.derived[Book]

  implicit val favouritesMediaUnionSchema: Schema[Movie | TvShow | Videogame | Book] = Schema.derivedUnion

  implicit val progressMediaUnionSchema: Schema[TvShow | TvSeason | Videogame | Book] = Schema.derivedUnion

  implicit val pendingMediaUnionSchema: Schema[Movie | TvShow | TvSeason | Videogame | Book] = Schema.derivedUnion

}

