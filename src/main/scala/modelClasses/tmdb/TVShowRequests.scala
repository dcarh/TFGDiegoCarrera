package modelClasses.tmdb



object TVShowRequests {

  case class RequestedTVShow(
                              created_by: List[Map[String, String | Int]],
                              episode_run_time: List[Int],
                              first_air_date: String,
                              genres: List[Map[String, String | Int]],
                              id: Long,
                              last_air_date: String,
                              name: String,
                              number_of_episodes: Int,
                              number_of_seasons: Int,
                              overview: String,
                              production_companies: List[Map[String, String | Int]],
                              production_countries: List[Map[String, String]],
                              runtime: List[Int],
                              seasons: List[Map[String, String | Int | Double]],
                              status: String,
                            )

  case class RequestedSimilarTVShows(results: List[Map[String, String | Int | Double | Boolean | List[Int]]])

  case class RequestedRecommendedTVShows(results: List[Map[String, String | Int | Double | Boolean | List[Int]]])

  case class RequestedCreditsForTVShow(
                                        id: Int,
                                        cast: List[Map[String, String | Int | Double | Boolean]],
                                        crew: List[Map[String, String | Int | Double | Boolean]]
                                      )

  case class RequestedAggregateCreditsForTVShow(
                                                 id: Int,
                                                 cast: List[Map[String, String | Int | Double | Boolean | List[Map[String, String | Int]]]],
                                                 crew: List[Map[String, String | Int | Double | Boolean]]
                                               )
}