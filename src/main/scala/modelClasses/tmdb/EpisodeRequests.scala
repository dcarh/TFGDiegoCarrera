package modelClasses.tmdb

object EpisodeRequests {

  case class RequestedEpisode(
                               air_date: String,
                               crew: List[Map[String, String | Int | Double | Boolean]],
                               guest_stars: List[Map[String, String | Int | Double | Boolean]],
                               episode_number: Int,
                               id: Int,
                               name: String,
                               overview: String,
                               runtime: Int,
                               season_number: Int
                             )

  case class RequestedCreditsForEpisode(
                                         id: Int,
                                         cast: List[Map[String, String | Int | Double | Boolean]],
                                         crew: List[Map[String, String | Int | Double | Boolean]]
                                       )
}