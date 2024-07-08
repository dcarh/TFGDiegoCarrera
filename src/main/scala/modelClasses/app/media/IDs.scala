package modelClasses.app.media

import io.circe.generic.auto.*

object IDs {

  case class MovieId(value: Long)

  case class TVShowId(value: Long)
  
  case class SeasonNumber(value: Long)

  case class EpisodeNumber(value: Long)

  case class BookId(value: String)
  
  case class VideogameId(value: Long)

}
