package modelClasses

case class Episode(
                  id: Episode.Id,
                  tvShowId: TVShow.Id,
                  seasonId: Season.Id,
                  title: String,
                  airDate: String,
                  seasonNumber: Int,
                  overview: String,
                  episodeNumber: Int,
                  crew: Seq[(String, String)],
                  guestStars: Seq[(String, String)],

                  likes: Seq[Like.Id],
                  reviews: Seq[Review.Id],
                  averageRating: Double,
                  ratings: Long,
                  lists: Seq[ElementList.Id],
                  completed: Long,
                  // TODO: Meter más campos
                  )

object Episode {
  type Id = Long
}
