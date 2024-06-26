package modelClasses.tmdb

import io.circe.generic.auto.*

object TVShowRequests {

  case class RequestedTVShow(
                              // created_by: List[Map[String, String | Long]],
                              // episode_run_time: List[Long],
                              first_air_date: String,
                              // genres: List[Map[String, String | Long]],
                              id: Long,
                              last_air_date: String,
                              name: String,
                              number_of_episodes: Long,
                              number_of_seasons: Long,
                              overview: String,
                              // production_companies: List[Map[String, String | Long]],
                              // production_countries: List[Map[String, String]],
                              // seasons: List[Map[String, String | Long | Double]],
                              status: String,
                            )

  case class RequestedSimilarTVShows(
                                      page: Long,          // Prueba, quitar cuando se vaya a implementar cliente definitivo
                                      total_pages: Long,   // Prueba, quitar cuando se vaya a implementar cliente definitivo
                                      total_results: Long, // Prueba, quitar cuando se vaya a implementar cliente definitivo
                                      // results: List[Map[String, String | Long | Double | Boolean | List[String] | List[Long]]] Quitado temporalmente
                                    )


  case class RequestedRecommendedTVShows(
                                          page: Long,          // Prueba, quitar cuando se vaya a implementar cliente definitivo
                                          total_pages: Long,   // Prueba, quitar cuando se vaya a implementar cliente definitivo
                                          total_results: Long, // Prueba, quitar cuando se vaya a implementar cliente definitivo
                                          // results: List[Map[String, String | Long | Double | Boolean | List[String] | List[Long]]] Quitado temporalmente
                                        )


  case class RequestedCreditsForTVShow(
                                        id: Long, // Prueba, quitar cuando se vaya a implementar cliente definitivo
                                        // cast: List[Map[String, String | Long | Double | Boolean]], Quitado temporalmente
                                        // crew: List[Map[String, String | Long | Double | Boolean]]  Quitado temporalmente
                                      )

  case class RequestedAggregateCreditsForTVShow(
                                                 id: Long, // Prueba, quitar cuando se vaya a implementar cliente definitivo
                                                 // cast: List[Map[String, String | Long | Double | Boolean | List[Map[String, String | Long]]]], Quitado temporalmente
                                                 // crew: List[Map[String, String | Long | Double | Boolean]]                                     Quitado temporalmente
                                               )

}