package modelClasses.tmdb

import io.circe.generic.auto.*

object MovieRequests {

  case class RequestedMovie(
                             budget: Long,
                             // genres: List[Map[String, String | Long]], Quitado temporalmente
                             id: Long,
                             overview: String,
                             // production_companies: List[Map[String, String | Long]], Quitado temporalmente
                             // production_countries: List[Map[String, String]],  Quitado temporalmente
                             release_date: String,
                             revenue: Long,
                             runtime: Long,
                             status: String,
                             title: String
                           )

  case class RequestedSimilarMovies(
                                    page: Long,          // Prueba, quitar cuando se vaya a implementar cliente definitivo
                                    total_pages: Long,   // Prueba, quitar cuando se vaya a implementar cliente definitivo
                                    total_results: Long, // Prueba, quitar cuando se vaya a implementar cliente definitivo
                                    // results: List[Map[String, String | Long | Double | Boolean | List[Long]]]  Quitado temporalmente
                                   )
  
  case class RequestedRecommendedMovies(
                                        page: Long,          // Prueba, quitar cuando se vaya a implementar cliente definitivo
                                        total_pages: Long,   // Prueba, quitar cuando se vaya a implementar cliente definitivo
                                        total_results: Long, // Prueba, quitar cuando se vaya a implementar cliente definitivo
                                        // results: List[Map[String, String | Long | Double | Boolean | List[Long]]] Quitado temporalmente
                                       )
  
  case class RequestedCreditsForMovie(
                                       id: Long,  // Prueba, quitar cuando se vaya a implementar cliente definitivo                               
                                       // cast: List[Map[String, String | Long | Double | Boolean]], Quitado temporalmente
                                       // crew: List[Map[String, String | Long | Double | Boolean]]  Quitado temporalmente
                                     )
}