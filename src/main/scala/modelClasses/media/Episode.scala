package modelClasses.media

import sttp.tapir.generic.auto._
import io.circe.generic.auto._

import modelClasses.social.{Like, Review}

case class Episode(
                    id           : Episode.Id,
                    tvShowId     : TVShow.Id,
                    seasonId     : Season.Id,
                    title        : String,
                    airDate      : String,
                    seasonNumber : Int,
                    overview     : String,
                    episodeNumber: Int,
                    crew         : List[(String, String)],
                    guestStars   : List[(String, String)],

                    likes        : List[Like.Id],
                    reviews      : List[Review.Id],
                    averageRating: Double,
                    ratings      : Long,
                    completed    : Long,
                    // TODO: Meter más campos
                  )

object Episode {
  type Id = Long
}
