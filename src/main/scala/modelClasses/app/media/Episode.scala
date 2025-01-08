package modelClasses.app.media

import io.circe.generic.auto.*
import modelClasses.ids.Media.{TvShowId, SeasonNumber, EpisodeNumber}
import modelClasses.ids.Social.{LikeId, ReviewId}

case class Episode(
                    airDate      : String,
                    crew         : List[(String, String)],
                    // episodeNumber: EpisodeNumber,
                    guestStars   : List[(String, String)],
                    episodeNumber: EpisodeNumber,
                    overview     : String,
                    runtime      : String,
                    // seasonId     : Season.Id,
                    seasonNumber : SeasonNumber,
                    title        : String,
                    tvShowId     : TvShowId,

                    averageRating: Double,
                    completed    : Long,
                    likes        : List[LikeId],
                    ratings      : Long,
                    reviews      : List[ReviewId]
                    // TODO: Meter más campos
                  )

// object Episode {
//   type Number = Int
//   type Id = Long
// }
