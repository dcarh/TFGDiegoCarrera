package modelClasses.tmdb

import modelClasses.ids.Media.MovieId
import modelClasses.tmdb.Common.*

object MovieRequests {

  case class RequestedMovie(
                             adult: Boolean,
                             backdrop_path: Option[String],
                             belongs_to_collection: Option[Collection],
                             budget: Option[Long],
                             genres: List[Genre],
                             homepage: String,
                             id: MovieId,
                             imdb_id: String,
                             origin_country: Option[List[String]],
                             original_language: Option[String],
                             original_title: String,
                             overview: Option[String],
                             popularity: Option[Double],
                             poster_path: Option[String],
                             production_companies: List[ProductionCompany],
                             production_countries: List[ProductionCountry],
                             release_date: Option[String],
                             revenue: Option[Long],
                             runtime: Option[Long],
                             spoken_languages: List[SpokenLanguage],
                             status: String,
                             tagline: String,
                             title: String,
                             video: Option[Boolean],
                             vote_average: Option[Double],
                             vote_count: Option[Long]
                           )

  case class Collection(
                          id: Long,
                          name: String,
                          poster_path: Option[String],
                          backdrop_path: Option[String]
                       )
}