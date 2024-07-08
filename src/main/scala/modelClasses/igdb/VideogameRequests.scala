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
                               aggregated_rating: Option[Double],             // Opcional
                               aggregated_rating_count: Option[Long],         // Opcional
                               alternative_names: List[Long],
                               artworks: List[Long],
                               bundles: List[Long],
                               category: Long,
                               collection: Option[Long],                      // Opcional
                               cover: Long,
                               created_at: Long,
                               dlcs: Option[List[Long]],                      // Opcional
                               external_games: List[Long],
                               first_release_date: Long,
                               follows: Long,
                               franchises: List[Long],
                               game_engines: List[Long],
                               game_modes: List[Long],
                               genres: List[Long],
                               hypes: Long,
                               involved_companies: List[Long],
                               keywords: List[Long],
                               name: String,
                               parent_game: Option[Long],                      // Opcional
                               platforms: List[Long],
                               player_perspectives: List[Long],
                               rating: List[Double],
                               rating_count: Long,
                               release_dates: List[Long],
                               screenshots: List[Long],
                               similar_games: List[Long],
                               slug: String,
                               standalone_expansions: Option[List[Long]],      // Opcional
                               storyline: String,
                               summary: String,
                               tags: List[Long],
                               themes: List[Long],
                               total_rating: Double,
                               total_rating_count: Long,
                               updated_at: Long,
                               url: String,
                               videos: List[Long],
                               websites: List[Long],
                               checksum: String,
                               remakes: List[Long],
                               language_supports: List[Long],
                               game_localizations: List[Long],
                               collections: List[Long]
                               )

}
