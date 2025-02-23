package modelClasses.tmdb

import io.circe.generic.auto.*
import modelClasses.ids.Media.{TvEpisodeNumber, TvSeasonNumber, TvShowId}
import modelClasses.tmdb.Common.Member

object TvEpisodeRequests {

  case class RequestedTvEpisode(
                                 air_date: Option[String],
                                 crew: List[Member],
                                 episode_number: Int,
                                 guest_stars: List[Member],
                                 name: String,
                                 overview: String,
                                 id: Long,
                                 production_code: Option[String],
                                 runtime: Option[Long],
                                 season_number: Int,
                                 still_path: Option[String],
                                 vote_average: Double,
                                 vote_count: Int
                             )
}