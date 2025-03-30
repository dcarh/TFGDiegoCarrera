package modelClasses.tmdb

import io.circe.generic.auto.*
import modelClasses.ids.Media.{TvEpisodeNumber, TvSeasonNumber}
import modelClasses.tmdb.Common.*

object TvSeasonRequests {

  case class RequestedTvSeason(
                                _id: String,
                                air_date: Option[String],
                                episodes: List[TvEpisodeForTvSeason],
                                name: String,
                                overview: String,
                                id: Long,
                                poster_path: Option[String],
                                runtime: Option[Long],
                                season_number: Int,
                                vote_average: Double
                            )

  case class TvEpisodeForTvSeason(
                                   air_date:Option[String],
                                   episode_number: Int,
                                   episode_type: String,
                                   id: Long,
                                   name: String,
                                   overview: String,
                                   production_code: String,
                                   runtime: Option[Int],
                                   season_number: Int,
                                   show_id: Long,
                                   still_path: Option[String],
                                   vote_average: Double,
                                   vote_count: Int,
                                   crew: List[Member],
                                   guest_stars: List[Member]
                                 )
}