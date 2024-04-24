package modelClasses

import sttp.tapir.generic.auto._

case class Episode(
                  id: Episode.Id,
                  tvShowId: TVShow.Id,
                  seasonId: Season.Id,
                  title: String,
                  airDate: String,
                  seasonNumber: Int,
                  overview: String,
                  episodeNumber: Int,
                  crew: List[(String, String)],
                  guestStars: List[(String, String)],

                  likes: List[Like.Id],
                  reviews: List[Review.Id],
                  averageRating: Double,
                  ratings: Long,
                  lists: List[MediaContentList.Id],
                  completed: Long,
                  // TODO: Meter más campos
                  )

object Episode {
  type Id = Long
}
