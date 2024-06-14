package modelClasses.tmdb

object MovieRequests {

  case class RequestedMovie(
                             budget: Long,
                             genres: List[Map[String, String | Int]],
                             id: Int,
                             overview: String,
                             production_companies: List[Map[String, String | Int]],
                             production_countries: List[Map[String, String]],
                             release_date: String,
                             revenue: Long,
                             runtime: Int,
                             status: String,
                             title: String
                           )

  case class RequestedSimilarMovies(results: List[Map[String, String | Int | Double | Boolean | List[Int]]])

  case class RequestedRecommendedMovies(results: List[Map[String, String | Int | Double | Boolean | List[Int]]])

  case class RequestedCreditsForMovie(
                                       id: Int,
                                       cast: List[Map[String, String | Int | Double | Boolean]],
                                       crew: List[Map[String, String | Int | Double | Boolean]]
                                     )
}