package modelClasses.tmdb

import io.circe.generic.auto.*
import modelClasses.tmdb.Common.Member

object EpisodeRequests {

  case class RequestedEpisode(
                               air_date: Option[String],
                               crew: List[Member],
                               episode_number: Long,
                               guest_stars: List[Member],
                               name: String,
                               overview: String,
                               id: Option[Long],
                               production_code: Option[String],
                               runtime: Option[Long],
                               season_number: Long,
                               still_path: Option[String],
                               vote_average: Double,
                               vote_count: Int
                             )

  case class RequestedCreditsForEpisode(
                                         id: Long,
                                         cast: List[Member],
                                         crew: List[Member]
                                       )
}