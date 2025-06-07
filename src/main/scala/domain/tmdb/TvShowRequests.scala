package domain.tmdb

import domain.ids.Media.{TvEpisodeNumber, TvSeasonNumber, TvShowId}
import domain.tmdb.Common.*

object TvShowRequests {

  case class TvShowFromTMDB(
                             adult: Boolean,
                             backdrop_path: Option[String],
                             created_by: List[Creator],
                             episode_run_time: Option[List[Int]],
                             first_air_date: Option[String],
                             genres: List[Genre],
                             homepage: String,
                             id: TvShowId,
                             in_production: Boolean,
                             languages: Option[List[String]],
                             last_air_date: Option[String],
                             last_episode_to_air: Option[TvEpisodeInTvShow],
                             name: String,
                             next_episode_to_air: Option[TvEpisodeInTvShow],
                             networks: Option[List[Network]],
                             number_of_episodes: Option[Int],
                             number_of_seasons: Option[Int],
                             origin_country: Option[List[String]],
                             original_language: Option[String],
                             original_name: String,
                             overview: Option[String],
                             popularity: Option[Double],
                             poster_path: Option[String],
                             production_companies: List[ProductionCompany],
                             production_countries: List[ProductionCountry],
                             seasons: List[TvSeasonInTvShow],
                             spoken_languages: Option[List[SpokenLanguage]],
                             status: String,
                             tagline: String,
                             `type`: String,
                             vote_average: Option[Double],
                             vote_count: Option[Long]
                            )

  case class TvEpisodeInTvShow(
                                id: Long,
                                name: String,
                                overview: String,
                                vote_average: Option[Double],
                                vote_count: Option[Long],
                                air_date: Option[String],
                                episode_number: Option[TvEpisodeNumber],
                                episode_type: Option[String],
                                production_code: Option[String],
                                runtime: Option[Int],
                                season_number: Option[TvSeasonNumber],
                                show_id: Option[TvShowId],
                                still_path: Option[String]
                            )

  case class Network(
                      id: Long,
                      logo_path: Option[String],
                      name: String,
                      origin_country: String
                    )

  case class TvSeasonInTvShow(
                            air_date: Option[String],
                            episode_count: Int,
                            id: Long,
                            name: String,
                            overview: String,
                            poster_path: Option[String],
                            season_number: TvSeasonNumber,
                            vote_average: Option[Double]
                           )

}