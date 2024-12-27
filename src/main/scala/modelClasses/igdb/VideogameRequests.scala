package modelClasses.igdb

import io.circe.generic.auto.*

object VideogameRequests {

  case class RequestedVideogame(
                               id: Long,
                               category: Long,
                               name: String,
                               url: String
                               )

  case class VideogameAllFields(
                               id: Long,
                               age_ratings: List[Long],
                               aggregated_rating: Option[Double],             // Opcional y me da igual
                               aggregated_rating_count: Option[Long],         // Opcional y me da igual
                               alternative_names: List[Long],                 // Me da igual
                               artworks: List[Long],                          // Me da igual
                               bundles: List[Long],                           // Me da igual
                               category: Long,
                               collection: Option[Long],                      // Opcional
                               cover: Long,                                   // Me da igual
                               created_at: Long,                              // Me da igual
                               dlcs: Option[List[Long]],                      // Opcional
                               external_games: List[Long],
                               first_release_date: Long,
                               follows: Long,                                 // Me da igual
                               franchises: List[Long],
                               game_engines: List[Long],
                               game_modes: List[Long],
                               genres: List[Long],
                               hypes: Long,                                   // Me da igual
                               involved_companies: List[Long],
                               keywords: List[Long],                          // Me da igual
                               name: String,
                               parent_game: Option[Long],                     // Opcional
                               platforms: List[Long],
                               player_perspectives: List[Long],
                               rating: List[Double],                          // Me da igual 
                               rating_count: Long,                            // Me da igual
                               release_dates: List[Long],                     // Me da igual 
                               screenshots: List[Long],                       // Me da igual
                               similar_games: List[Long], 
                               slug: String,                                  // Me da igual (¿?)
                               standalone_expansions: Option[List[Long]],      // Opcional
                               storyline: String,
                               summary: String,
                               tags: List[Long],                              // Me da igual
                               themes: List[Long],
                               total_rating: Double,                          // Me da igual
                               total_rating_count: Long,                      // Me da igual
                               updated_at: Long,                              // Me da igual
                               url: String,                                   // Me da igual
                               videos: List[Long],                            // Me da igual
                               websites: List[Long],                          // Me da igual
                               checksum: String,                              // Me da igual
                               remakes: List[Long], 
                               language_supports: List[Long],                 // Me da igual
                               game_localizations: List[Long],                // Me da igual
                               collections: List[Long]                        // Me da igual
                               )

}
