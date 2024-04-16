package modelClasses

case class Season(
                 id: Season.Id,
                 title: String,
                 airDate: String,
                 seasonNumber: Int,
                 overview: String,
                 tvShowId: TVShow.Id,
                 numberOfEpisodes: Int,
                 episodesIds: Seq[Episode.Id],

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
                 )

object Season {
  type Id = Long
}