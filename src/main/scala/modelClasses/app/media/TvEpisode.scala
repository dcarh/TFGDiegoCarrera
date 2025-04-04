package modelClasses.app.media

import io.circe.generic.auto.*
import modelClasses.ids.Social.ReviewId
import modelClasses.tmdb.Common.Member
import modelClasses.tmdb.TvEpisodeRequests.RequestedTvEpisode

case class TvEpisode(
                      requestedTvEpisode: RequestedTvEpisode,
                      cast: Option[List[Member]],
                      crew: Option[List[Member]],
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
//                      averageRating      : Double, 
//                      lists              : List[MediaListId],
                      numberOfCompleted  : Long,
                      numberOfDropped    : Long,
                      numberOfPending    : Long,
//                      ratings            : Long,
//                      reviews            : List[ReviewId]
// TODO: Meter más campos
                  )