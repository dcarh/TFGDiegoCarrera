package modelClasses.tmdb

import io.circe.generic.auto.*
import modelClasses.ids.Media.TvShowId
import modelClasses.tmdb.Common.*

object TvShowRequests {

  case class RequestedTvShow(
                              adult: Boolean,
                              backdrop_path: Option[String],
                              created_by: List[Creator],
                              episode_run_time: Option[List[Int]],
                              first_air_date: Option[String],
                              genres: List[Genre],
                              homepage: String,
                              id: Long,
                              in_production: Boolean,
                              languages: List[String],
                              last_air_date: Option[String],
                              last_episode_to_air: Option[TvEpisodeInTvShow],
                              name: String,
                              next_episode_to_air: Option[TvEpisodeInTvShow],
                              networks: List[Network],
                              number_of_episodes: Int,
                              number_of_seasons: Int,
                              origin_country: Option[List[String]],
                              original_language: String,
                              original_name: String,
                              overview: String,
                              popularity: Double,
                              poster_path: Option[String],
                              production_companies: List[ProductionCompany],
                              production_countries: List[ProductionCountry],
                              seasons: List[TvSeasonInTvShow],
                              spoken_languages: Option[List[SpokenLanguage]],
                              status: String,
                              tagline: String,
                              `type`: String,
                              vote_average: Double,
                              vote_count: Int
                            )

  case class TvEpisodeInTvShow(
                              id: Long,
                              name: String,
                              overview: String,
                              vote_average: Double,
                              vote_count: Long,
                              air_date: Option[String],
                              episode_number: Option[Long],
                              episode_type: Option[String],
                              production_code: Option[String],
                              runtime: Option[Int],
                              season_number: Option[Int],
                              show_id: Option[Long],
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
                            season_number: Int,
                            vote_average: Option[Double]
                           )

  case class RequestedSimilarTvShows(
                                      page: Long,
                                      total_pages: Long,
                                      total_results: Long,
                                      results: List[Results]
                                    )


  case class RequestedRecommendedTvShows(
                                          page: Long,
                                          total_pages: Long,
                                          total_results: Long,
                                          results: List[Results]
                                        )


  case class RequestedCreditsForTvShow(
                                        id: Long,
                                        cast: List[Member],
                                        crew: List[Member]
                                      )

  case class RequestedAggregateCreditsForTvShow(
                                                 id: Long,
                                                 cast: List[Member],
                                                 crew: List[Member]
                                               )

}