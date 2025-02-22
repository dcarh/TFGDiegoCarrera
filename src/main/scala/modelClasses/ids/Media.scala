package modelClasses.ids

import io.circe.generic.auto.*

object Media {

  case class MovieId(value: Long)
  case class TvShowId(value: Long)
  case class TvSeasonNumber(value: Long)
  case class TvEpisodeNumber(value: Long)
  case class BookId(value: String)
  case class VideogameId(value: Long)
  
}
