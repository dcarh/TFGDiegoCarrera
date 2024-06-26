package modelClasses.app.media

import io.circe.generic.auto.*
import modelClasses.app.social.{Like, Review}

case class Episode(
                    airDate      : String,
                    crew         : List[(String, String)],
                    episodeNumber: Episode.Number,
                    guestStars   : List[(String, String)],
                    id           : Episode.Id,
                    overview     : String,
                    runtime      : String,
                    seasonId     : Season.Id,
                    seasonNumber : Season.Number,
                    title        : String,
                    tvShowId     : TVShow.Id,

                    averageRating: Double,
                    completed    : Long,
                    likes        : List[Like.Id],
                    ratings      : Long,
                    reviews      : List[Review.Id]
                    // TODO: Meter más campos
                  )

object Episode {
  type Number = Int
  type Id = Long
}
