package modelClasses.tmdb

import io.circe.generic.auto.*
import modelClasses.ids.Media.MovieId
import modelClasses.tmdb.Common.*

object MovieRequests {

  case class RequestedMovie(
                             adult: Boolean,
                             backdrop_path: Option[String],
                             belongs_to_collection: Option[Collection],
                             budget: Long,
                             genres: List[Genre],
                             homepage: String,
                             id: Long,
                             imdb_id: String,
                             origin_country: Option[List[String]],
                             original_language: String,
                             original_title: String,
                             overview: String,
                             popularity: Double,
                             poster_path: Option[String],
                             production_companies: List[ProductionCompany],
                             production_countries: List[ProductionCountry],
                             release_date: Option[String],
                             revenue: Long,
                             runtime: Option[Long],
                             spoken_languages: List[SpokenLanguage],
                             status: String,
                             tagline: String,
                             title: String,
                             video: Boolean,
                             vote_average: Double,
                             vote_count: Int
                           )

  case class Collection(
                          id: Long,
                          name: String,
                          poster_path: Option[String],
                          backdrop_path: Option[String]
                       )
}