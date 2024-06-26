package modelClasses.tmdb

import io.circe.generic.auto.*

object SeasonRequests {

  case class RequestedSeason(
                              // air_date: String,
                              //episodes:
                              //  List[Map[String, String | Long | Double | List[Map[String, String | Long | Double | Boolean]]]], Quitado temporalmente
                              id: Long,
                              name: String,
                              overview: String,
                              // runtime: Long,
                              // season_number: Long
                            )

  case class RequestedCreditsForSeason(
                                        id: Long, // Prueba, quitar cuando se vaya a implementar cliente definitivo
                                        // cast: List[Map[String, String | Long | Double | Boolean]], Quitado temporalmente
                                        // crew: List[Map[String, String | Long | Double | Boolean]] Quitado temporalmente
                                      )

  case class RequestedAggregateCreditsForSeason(
                                                 id: Long, // Prueba, quitar cuando se vaya a implementar cliente definitivo
                                                 // cast: List[Map[String, String | Long | Double | Boolean | List[Map[String, String | Long]]]],  Quitado temporalmente
                                                 // crew: List[Map[String, String | Long | Double | Boolean]]  Quitado temporalmente
                                               )
}