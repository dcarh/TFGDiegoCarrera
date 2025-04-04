package modelClasses.app.media

import io.circe.generic.auto.*
import modelClasses.ids.Social.ReviewId
import modelClasses.tmdb.Common.Member
import modelClasses.tmdb.TvSeasonRequests.RequestedTvSeason

case class TvSeason(
                     requestedTvSeason: RequestedTvSeason,
                     cast: Option[List[Member]],
                     crew: Option[List[Member]],
//                     airDate: String,
//                     // episodesIds: List[EpisodeId],
//                     numberOfEpisodes: Int,
//                     overview: String,
//                     seasonNumber: TvSeasonNumber,
//                     title: String,
//                     tvShowId: TvShowId,
//
//                     averageRating: Double,
//                     lists: List[MediaListId],
                     numberOfCompleted: Long,
                     numberOfDropped: Long,
                     numberOfInProgress: Long,
                     numberOfOnHold: Long,
                     numberOfPending: Long,
//                     ratings: Long,
//                     reviews: List[ReviewId]
                 )