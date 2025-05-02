package codecs.schemas

import sttp.tapir.*
import sttp.tapir.generic.auto.*

import modelClasses.app.media.*

object AllMediaUnionType {

  implicit val allMediaUnionSchema: Schema[Movie | TvShow | TvSeason | TvEpisode | Videogame | Book] = Schema.derivedUnion

}

