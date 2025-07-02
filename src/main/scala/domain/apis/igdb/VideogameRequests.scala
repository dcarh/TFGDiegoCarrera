package domain.apis.igdb

import io.circe.generic.auto.*
import domain.ids.Media.VideogameId

object VideogameRequests {

  case class RequestedVideogame(
                                 id: VideogameId,
                                 category: Long,
                                 name: String,
                                 url: String
                               )

  case class VideogameFromIGDB(
                               id: VideogameId,
                               age_ratings: Option[List[Long]],
                               aggregated_rating: Option[Double],
                               aggregated_rating_count: Option[Long],
                               alternative_names: Option[List[Long]],
                               artworks: Option[List[Long]],
                               bundles: Option[List[Long]],
                               category: Option[Long],
                               collection: Option[Long],                     
                               cover: Option[Long],
                               created_at: Option[Long],
                               dlcs: Option[List[Long]],                     
                               external_games: Option[List[Long]],
                               first_release_date: Option[Long],
                               follows: Option[Long],
                               franchises: Option[List[Long]],
                               game_engines: Option[List[Long]],
                               game_modes: Option[List[Long]],
                               genres: Option[List[Long]],
                               hypes: Option[Long],
                               involved_companies: Option[List[Long]],
                               keywords: Option[List[Long]],
                               name: String,
                               parent_game: Option[Long],
                               platforms: Option[List[Long]],
                               player_perspectives: Option[List[Long]],
                               rating: Option[Double],
                               rating_count: Option[Long],
                               release_dates: Option[List[Long]],
                               screenshots: Option[List[Long]],
                               similar_games: Option[List[Long]],
                               slug: Option[String],
                               standalone_expansions: Option[List[Long]],     
                               storyline: Option[String],
                               summary: Option[String],
                               tags: Option[List[Long]],
                               themes: Option[List[Long]],
                               total_rating: Option[Double],
                               total_rating_count: Option[Long],
                               updated_at: Option[Long],
                               url: Option[String],
                               videos: Option[List[Long]],
                               websites: Option[List[Long]],
                               checksum: Option[String],
                               remakes: Option[List[Long]],
                               language_supports: Option[List[Long]],
                               game_localizations: Option[List[Long]],
                               collections: Option[List[Long]]
                               )
}
