package modelClasses

case class Movie(
                id: Movie.Id,
                title: String,
                director: String,
                year: String,
                runtime: Int,
                overview: String,
                cast: List[(String, String)],
                genres: List[String],
                productionCompanies: List[String],
                productionCountries: List[String],
                budget: Long,
                revenue: Long,
                recommendations: List[Movie.Id],
                similar: List[Movie.Id],

                likes: List[Like.Id],
                reviews: List[Review.Id],
                averageRating: Double,
                ratings: Long,
                lists: List[ElementList.Id],
                completed: Long,
                inProgress: Long,
                pending: Long,
                abandoned: Long
                ) extends Element

object Movie {
  type Id = Long
}