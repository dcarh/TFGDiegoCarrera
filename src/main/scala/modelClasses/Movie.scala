package modelClasses

case class Movie(
                id: Movie.Id,
                title: String,
                director: String,
                year: String,
                runtime: Int,
                overview: String,
                cast: Seq[(String, String)],
                genres: Seq[String],
                productionCompanies: Seq[String],
                productionCountries: Seq[String],
                budget: Long,
                revenue: Long,
                recommendations: Seq[Movie.Id],
                similar: Seq[Movie.Id],

                likes: Seq[Like.Id],
                reviews: Seq[Review.Id],
                averageRating: Double,
                ratings: Long,
                lists: Seq[ElementList.Id],
                completed: Long,
                inProgress: Long,
                pending: Long,
                abandoned: Long
                ) extends Element

object Movie {
  type Id = Long
}