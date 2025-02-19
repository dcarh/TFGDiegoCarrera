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
                               age_ratings: Option[List[Long]],
                               aggregated_rating: Option[Double],             // Me da igual
                               aggregated_rating_count: Option[Long],         // Me da igual
                               alternative_names: Option[List[Long]],         // Me da igual
                               artworks: Option[List[Long]],                  // Me da igual
                               bundles: Option[List[Long]],                   // Me da igual
                               category: Option[Long],
                               collection: Option[Long],                     
                               cover: Option[Long],                           // Me da igual
                               created_at: Option[Long],                      // Me da igual
                               dlcs: Option[List[Long]],                     
                               external_games: Option[List[Long]],
                               first_release_date: Option[Long],
                               follows: Option[Long],                         // Me da igual
                               franchises: Option[List[Long]],
                               game_engines: Option[List[Long]],
                               game_modes: Option[List[Long]],
                               genres: Option[List[Long]],
                               hypes: Option[Long],                           // Me da igual
                               involved_companies: Option[List[Long]],
                               keywords: Option[List[Long]],                  // Me da igual
                               name: String,
                               parent_game: Option[Long],
                               platforms: Option[List[Long]],
                               player_perspectives: Option[List[Long]],
                               rating: Option[Double],                        // Me da igual
                               rating_count: Option[Long],                    // Me da igual
                               release_dates: Option[List[Long]],             // Me da igual
                               screenshots: Option[List[Long]],               // Me da igual
                               similar_games: Option[List[Long]],
                               slug: Option[String],                          // Me da igual (¿?)
                               standalone_expansions: Option[List[Long]],     
                               storyline: Option[String],
                               summary: Option[String],
                               tags: Option[List[Long]],                      // Me da igual
                               themes: Option[List[Long]],
                               total_rating: Option[Double],                  // Me da igual
                               total_rating_count: Option[Long],              // Me da igual
                               updated_at: Option[Long],                      // Me da igual
                               url: Option[String],                           // Me da igual
                               videos: Option[List[Long]],                    // Me da igual
                               websites: Option[List[Long]],                  // Me da igual
                               checksum: Option[String],                      // Me da igual
                               remakes: Option[List[Long]],
                               language_supports: Option[List[Long]],         // Me da igual
                               game_localizations: Option[List[Long]],        // Me da igual
                               collections: Option[List[Long]]                // Me da igual
                               )

}
