package model

case class Movie(
                  id: Int,
                  title: String,
                  year: String,
                  releaseDates: List[String],
                  countries: List[String],
                  originalLanguages: List[String],
                  crew: List[String],
                  overview: String,
                  genres: List[Int],
                  productionCompanies: List[Int],
                  budget: Int
                )
