package modelClasses.app.media

import io.circe.generic.auto.*
import modelClasses.app.social.{Like, Review}

case class Season(
                   airDate: String,
                   episodesIds: List[Episode.Id],
                   id: Season.Id,
                   numberOfEpisodes: Int,
                   overview: String,
                   seasonNumber: Season.Number,
                   title: String,
                   tvShowId: TVShow.Id,

                   abandoned: Long,
                   averageRating: Double,
                   completed: Long,
                   inProgress: Long,
                   likes: List[Like.Id],
                   paused: Long,
                   pending: Long,
                   ratings: Long,
                   reviews: List[Review.Id]
                 )

object Season {
  type Id = Long
  type Number = Int
}
