package model

case class Movie(
                title: String,
                id: Int,
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
