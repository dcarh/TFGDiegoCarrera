package domain.apis.tmdb

import domain.apis.tmdb.Common.Member
import io.circe.generic.auto.*
import domain.ids.Media.{TvEpisodeNumber, TvSeasonNumber, TvShowId}
import Common.Member

object TvEpisodeRequests {

  case class TvEpisodeFromTMDB(
                                 air_date: Option[String],
                                 crew: List[Member],
                                 episode_number: TvEpisodeNumber,
                                 guest_stars: List[Member],
                                 name: String,
                                 overview: String,
                                 id: Long,
                                 production_code: Option[String],
                                 runtime: Option[Long],
                                 season_number: TvSeasonNumber,
                                 still_path: Option[String],
                                 vote_average: Double,
                                 vote_count: Int
                             )
}