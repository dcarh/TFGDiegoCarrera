package modelClasses.media

import sttp.tapir.generic.auto._
import io.circe.generic.auto._

import modelClasses.social.{Like, Review}

case class Season(
                   id: Season.Id,
                   title: String,
                   airDate: String,
                   seasonNumber: Int,
                   overview: String,
                   tvShowId: TVShow.Id,
                   numberOfEpisodes: Int,
                   episodesIds: List[Episode.Id],

                   likes: List[Like.Id],
                   reviews: List[Review.Id],
                   averageRating: Double,
                   ratings: Long,
                   completed: Long,
                   inProgress: Long,
                   paused: Long,
                   pending: Long,
                   abandoned: Long
                 )

object Season {
  type Id = Long
}
