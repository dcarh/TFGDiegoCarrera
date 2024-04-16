package modelClasses

case class TVShow(
                 id: TVShow.Id,
                 title: String,
                 creator: String,
                 status: String,
                 firstAirDate: String,
                 lastAirDate: String,
                 totalRuntime: Int,
                 episodeRuntime: Int,
                 overview: String,
                 cast: Seq[(String, String)],
                 genres: Seq[String],
                 productionCompanies: Seq[String],
                 productionCountries: Seq[String],
                 numberOfSeasons: Int,
                 numberOfEpisodes: Int,
                 seasonsIds: Seq[Season.Id],
                 episodesIds: Seq[Episode.Id],
                 recommendations: Seq[Movie.Id],
                 similar: Seq[Movie.Id],

                 likes: Seq[Like.Id],
                 reviews: Seq[Review.Id],
                 averageRating: Double,
                 ratings: Long,
                 lists: Seq[ElementList.Id],
                 completed: Long,
                 inProgress: Long,
                 paused: Long,
                 pending: Long,
                 abandoned: Long
                 ) extends Element

object TVShow {
  type Id = Long
}