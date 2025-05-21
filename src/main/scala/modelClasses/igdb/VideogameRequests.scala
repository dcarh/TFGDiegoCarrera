package modelClasses.igdb

import io.circe.generic.auto.*
import modelClasses.ids.Media.VideogameId

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
                               aggregated_rating: Option[Double],             // Me da igual
                               aggregated_rating_count: Option[Long],         // Me da igual
                               alternative_names: Option[List[Long]],
                               artworks: Option[List[Long]],                  // Me da igual
                               bundles: Option[List[Long]],
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
                               keywords: Option[List[Long]],
                               name: String,
                               parent_game: Option[Long],
                               platforms: Option[List[Long]],
                               player_perspectives: Option[List[Long]],
                               rating: Option[Double],                        // Me da igual
                               rating_count: Option[Long],                    // Me da igual
                               release_dates: Option[List[Long]],
                               screenshots: Option[List[Long]],               // Me da igual
                               similar_games: Option[List[Long]],
                               slug: Option[String],                          // Me da igual
                               standalone_expansions: Option[List[Long]],     
                               storyline: Option[String],
                               summary: Option[String],
                               tags: Option[List[Long]],
                               themes: Option[List[Long]],
                               total_rating: Option[Double],                  // Me da igual
                               total_rating_count: Option[Long],              // Me da igual
                               updated_at: Option[Long],                      // Me da igual
                               url: Option[String],                           // Me da igual
                               videos: Option[List[Long]],                    // Me da igual
                               websites: Option[List[Long]],                  // Me da igual
                               checksum: Option[String],                      // Me da igual
                               remakes: Option[List[Long]],
                               language_supports: Option[List[Long]],
                               game_localizations: Option[List[Long]],
                               collections: Option[List[Long]]
                               )

//  case class VideogameComplete(
//                                 id: VideogameId,
//                                 age_ratings: Option[List[AgeRatings]],
//                                 aggregated_rating: Option[Double],               // Me da igual
//                                 aggregated_rating_count: Option[Long],           // Me da igual
//                                 alternative_names: Option[List[AlternativeName]],
//                                 artworks: Option[List[Long]],                    // Me da igual
//                                 bundles: Option[List[GameLite]],
//                                 collection: Option[Long],
//                                 cover: Option[Long],                             // Me da igual
//                                 created_at: Option[Long],                        // Me da igual
//                                 dlcs: Option[List[GameLite]],
//                                 expanded_games: Option[List[GameLite]],
//                                 external_games: Option[List[Long]],              // Me da igual
//                                 first_release_date: Option[Long],
//                                 follows: Option[Long],                           // Me da igual
//                                 franchises: Option[List[Long]],
//                                 game_engines: Option[List[GameEngine]],
//                                 game_modes: Option[List[GenericField]],
//                                 game_type: Option[GameType],
//                                 genres: Option[List[GenericField]],
//                                 hypes: Option[Long],                             // Me da igual
//                                 involved_companies: Option[List[InvolvedCompany]],
//                                 keywords: Option[List[GenericField]],
//                                 name: String,
//                                 parent_game: Option[GenericField],
//                                 platforms: Option[List[Platform]],
//                                 player_perspectives: Option[List[GenericField]],
//                                 rating: Option[Double],                          // Me da igual
//                                 rating_count: Option[Long],                      // Me da igual
//                                 release_dates: Option[List[ReleaseDate]],
//                                 screenshots: Option[List[Long]],                 // Me da igual
//                                 similar_games: Option[List[GenericField]],
//                                 slug: Option[String],                            // Me da igual
//                                 standalone_expansions: Option[List[GameLite]],
//                                 storyline: Option[String],
//                                 summary: Option[String],
//                                 tags: Option[List[Long]],
//                                 themes: Option[List[GenericField]],
//                                 total_rating: Option[Double],                    // Me da igual
//                                 total_rating_count: Option[Long],                // Me da igual
//                                 updated_at: Option[Long],                        // Me da igual
//                                 url: Option[String],                             // Me da igual
//                                 videos: Option[List[Long]],                      // Me da igual
//                                 websites: Option[List[Long]],                    // Me da igual
//                                 checksum: Option[String],                        // Me da igual
//                                 remakes: Option[List[GameLite]],
//                                 remasters: Option[List[GameLite]],
//                                 language_supports: Option[List[LanguageSupport]],
//                                 game_localizations: Option[List[GameLocalization]],
//                                 collections: Option[List[Collection]]
//                               )
//
//  case class GenericField(
//                           id: Long,
//                           name: Option[String]
//                         )
//
//  case class AgeRatings(
//                       id: Long,
//                       rating_category: Option[GenericField],
//                       rating_content_descriptions: Option[RatingContentDescriptions]
//                       )
//
//  case class AlternativeName(
//                            id: Long,
//                            comment: Option[String],
//                            name: Option[String]
//                            )
//
//  case class Collection(
//                       id: Long,
//                       name: Option[String],
//                       `type`: Option[Type]
//                       )
//
//  case class GameEngine(
//                       id: Long,
//                       companies: Option[List[GenericField]],
//                       name: Option[String],
//                       platforms: Option[List[Platform]]
//                       )
//
//  case class GameType(
//                     id: Long,
//                     `type`: Option[String]
//                     )
//
//  case class GameLite(
//                     id: Long,
//                     name: Option[String],
//                     platform: Option[List[Platform]]
//                     )
//
//  case class Platform(
//                     id: Long,
//                     abbreviation: Option[String],
//                     alternative_name: Option[String],
//                     generation: Option[Int],
//                     name: Option[String],
//                     platform_family: Option[GenericField],
//                     platform_type: Option[GenericField],
//                     summary: Option[String]
//                     )
//
//  case class RatingContentDescriptions(
//                                        id: Long,
//                                        description: Option[String],
//                                        organization: Option[GenericField]
//                                      )
//
//  case class ReleaseDate(
//                        id: Long,
//                        human: Option[String],
//                        m: Option[String],
//                        y: Option[String],
//                        release_region: Option[ReleaseRegion],
//                        status: Option[GenericField]
//                        )
//
//  case class ReleaseRegion(
//                          id: Long,
//                          region: Option[String]
//                          )
//
//  case class Type(
//                   id: Long,
//                   name: Option[String],
//                   description: Option[String]
//                 )


}
