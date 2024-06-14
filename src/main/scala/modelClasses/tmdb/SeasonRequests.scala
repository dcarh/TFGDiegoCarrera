package modelClasses.tmdb

object SeasonRequests {

  case class RequestedSeason(
                              air_date: String,
                              episodes: List[Map[String, String | Int | Double | List[Map[String, String | Int | Double | Boolean]]]],
                              id: Int,
                              name: String,
                              overview: String,
                              runtime: Int,
                              season_number: Int
                            )

  case class RequestedCreditsForSeason(
                                        id: Int,
                                        cast: List[Map[String, String | Int | Double | Boolean]],
                                        crew: List[Map[String, String | Int | Double | Boolean]]
                                      )

  case class RequestedAggregateCreditsForSeason(
                                                 id: Int,
                                                 cast: List[Map[String, String | Int | Double | Boolean | List[Map[String, String | Int]]]],
                                                 crew: List[Map[String, String | Int | Double | Boolean]]
                                               )
}