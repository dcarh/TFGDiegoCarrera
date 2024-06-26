package modelClasses.tmdb

import io.circe.generic.auto.*

object EpisodeRequests {

  case class RequestedEpisode(
                               air_date: String,
                               //crew: List[Map[String, String | Long | Double | Boolean]],        Quitado temporalmente
                               //guest_stars: List[Map[String, String | Long | Double | Boolean]], Quitado temporalmente
                               episode_number: Long,
                               id: Long,
                               name: String,
                               overview: String,
                               runtime: Long,
                               season_number: Long
                             )

  case class RequestedCreditsForEpisode(
                                         id: Long, // Prueba, quitar cuando se vaya a implementar cliente definitivo
                                         // cast: List[Map[String, String | Long | Double | Boolean]], Quitado temporalmente
                                         // crew: List[Map[String, String | Long | Double | Boolean]]  Quitado temporalmente
                                       )
}