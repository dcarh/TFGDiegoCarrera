package modelClasses.app.media

import io.circe.generic.auto.*
import modelClasses.ids.Media.{TvEpisodeNumber, TvSeasonNumber, TvShowId}
import modelClasses.ids.Social.{LikeId, ReviewId}
import modelClasses.tmdb.TvEpisodeRequests.RequestedTvEpisode

case class TvEpisode(
                      requestedTvEpisode: RequestedTvEpisode
//                      airDate      : String,
//                      crew         : List[(String, String)],
//                      // episodeNumber: EpisodeNumber,
//                      guestStars   : List[(String, String)],
//                      episodeNumber: TvEpisodeNumber,
//                      overview     : String,
//                      runtime      : String,
//                      // seasonId     : Season.Id,
//                      seasonNumber : TvSeasonNumber,
//                      title        : String,
//                      tvShowId     : TvShowId,
//
//                      averageRating: Double,
//                      completed    : Long,
//                      ratings      : Long,
//                      reviews      : List[ReviewId]
                      // TODO: Meter más campos
                  )