package domain.apis.tmdb

import domain.apis.tmdb.Common.Member
import io.circe.generic.auto.*
import domain.ids.Media.{TvEpisodeNumber, TvSeasonNumber}
import Common.*

object TvSeasonRequests {

  case class TvSeasonFromTMDB(
                           _id: String,
                           air_date: Option[String],
                           episodes: List[TvEpisodeInTvSeason],
                           name: String,
                           overview: String,
                           id: Long,
                           poster_path: Option[String],
                           runtime: Option[Long],
                           season_number: TvSeasonNumber,
                           vote_average: Double
                            )

  case class TvEpisodeInTvSeason(
                                   air_date:Option[String],
                                   episode_number: TvEpisodeNumber,
                                   episode_type: String,
                                   id: Long,
                                   name: String,
                                   overview: String,
                                   production_code: String,
                                   runtime: Option[Int],
                                   season_number: TvSeasonNumber,
                                   show_id: Long,
                                   still_path: Option[String],
                                   vote_average: Double,
                                   vote_count: Int,
                                   crew: List[Member],
                                   guest_stars: List[Member]
                                 )
}