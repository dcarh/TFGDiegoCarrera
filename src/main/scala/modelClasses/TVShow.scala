package modelClasses

// case class TVShow(
//                  id: TVShow.Id,
//                  title: String,
//                  creator: String,
//                  status: String,
//                  firstAirDate: String,
//                  lastAirDate: String,
//                  totalRuntime: Int,
//                  episodeRuntime: Int,
//                  overview: String,
//                  cast: List[(String, String)],
//                  genres: List[(Int, String)],
//                  productionCompanies: List[(Int, String)],
//                  productionCountries: List[(String, String)],
//                  numberOfSeasons: Int,
//                  numberOfEpisodes: Int,
//                  seasonsIds: List[Season.Id],
//                  episodesIds: List[Episode.Id],
//                  recommendations: List[Movie.Id],
//                  similar: List[Movie.Id],
// 
//                  likes: List[Like.Id],
//                  reviews: List[Review.Id],
//                  averageRating: Double,
//                  ratings: Long,
//                  lists: List[MediaContentList.Id],
//                  completed: Long,
//                  inProgress: Long,
//                  paused: Long,
//                  pending: Long,
//                  abandoned: Long
//                  ) extends MediaContent
// 
// object TVShow {
//   type Id = Long
// }