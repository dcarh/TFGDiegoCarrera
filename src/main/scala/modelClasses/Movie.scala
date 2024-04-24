package modelClasses

// case class Movie(
//                 id: Movie.Id,
//                 title: String,
//                 director: String,
//                 year: String,
//                 runtime: Int,
//                 overview: String,
//                 cast: List[(String, String)],
//                 genres: List[(Int, String)],
//                 productionCompanies: List[(Int, String)],
//                 productionCountries: List[(String, String)],
//                 budget: Long,
//                 revenue: Long,
//                 recommendations: List[Movie.Id],
//                 similar: List[Movie.Id],
//                 status: String,
// 
//                 likes: List[Like.Id],
//                 reviews: List[Review.Id],
//                 averageRating: Double,
//                 ratings: Long,
//                 lists: List[MediaContentList.Id],
//                 completed: Long,
//                 inProgress: Long,
//                 pending: Long,
//                 abandoned: Long
//                 ) extends MediaContent
// 
// object Movie {
//   type Id = Long
// }