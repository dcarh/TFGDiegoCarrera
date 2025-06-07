package domain.tmdb

object Common {
  
  case class Creator(
                      id: Long,
                      credit_id: String,
                      name: String,
                      original_name: String,
                      gender: Int,
                      profile_path: Option[String]
                    )

  case class Genre(
                    id: Int,
                    name: String
                  )

  case class ProductionCompany(
                                id: Long,
                                logo_path: Option[String],
                                name: String,
                                origin_country: String
                              )

  case class ProductionCountry(
                                iso_3166_1: String,
                                name: String
                              )

  case class SpokenLanguage(
                             english_name: String,
                             iso_639_1: String,
                             name: String
                           )

  case class Role(
                    credit_id: String,
                    character: String,
                    episode_count: Int
                  )

  case class Job(
                  credit_id: String,
                  job: String,
                  episode_count: Int
                )

  case class Member(
                     adult: Boolean,
                     gender: Int,
                     id: Long,
                     known_for_department: String,
                     name: String,
                     original_name: String,
                     popularity: Option[Double],
                     profile_path: Option[String],
                     roles: Option[List[Role]],
                     jobs: Option[List[Job]],
                     cast_id: Option[Long],
                     character: Option[String],
                     credit_id: Option[String],
                     department: Option[String],
                     job: Option[String],
                     total_episode_count: Option[Int],
                     order: Option[Long]
                   )

  case class Result(
                     adult: Boolean,
                     backdrop_path: Option[String],
                     first_air_date: Option[String], // TV Show
                     genre_ids: List[Int],
                     id: Long,
                     media_type: Option[String],
                     original_name: Option[String], // TV Show
                     original_language: String,
                     original_title: Option[String], // Movie
                     overview: String,
                     popularity: Double,
                     poster_path: Option[String],
                     release_date: Option[String], // Movie
                     title: Option[String], // Movie
                     name: Option[String], // TV Show
                     video: Option[Boolean],
                     vote_average: Option[Double],
                     vote_count: Option[Long]
                    )

  case class Results(
                     page: Long,
                     results: List[Result],
                     total_pages: Long,
                     total_results: Long
                    )

  
  case class Credits(
                      id: Long,
                      cast: List[Member],
                      crew: List[Member]
                    )

}
