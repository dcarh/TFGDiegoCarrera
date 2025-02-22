package modelClasses.app.media

import io.circe.generic.auto.*
import modelClasses.ids.Media.{TvShowId, TvSeasonNumber, TvEpisodeNumber}
import modelClasses.ids.Social.{LikeId, ReviewId}

case class TvEpisode(
                      airDate      : String,
                      crew         : List[(String, String)],
                      // episodeNumber: EpisodeNumber,
                      guestStars   : List[(String, String)],
                      episodeNumber: TvEpisodeNumber,
                      overview     : String,
                      runtime      : String,
                      // seasonId     : Season.Id,
                      seasonNumber : TvSeasonNumber,
                      title        : String,
                      tvShowId     : TvShowId,

                      averageRating: Double,
                      completed    : Long,
                      ratings      : Long,
                      reviews      : List[ReviewId]
                      // TODO: Meter más campos
                  )