package modelClasses.app.media

import io.circe.generic.auto.*
import modelClasses.ids.Social.ReviewId
import modelClasses.tmdb.Common.Member
import modelClasses.tmdb.TvSeasonRequests.RequestedTvSeason

case class TvSeason(
                     requestedTvSeason: RequestedTvSeason,
                     cast: Option[List[Member]],
                     crew: Option[List[Member]]
//                     airDate: String,
//                     // episodesIds: List[EpisodeId],
//                     numberOfEpisodes: Int,
//                     overview: String,
//                     seasonNumber: TvSeasonNumber,
//                     title: String,
//                     tvShowId: TvShowId,
//
//                     abandoned: Long,
//                     averageRating: Double,
//                     completed: Long,
//                     inProgress: Long,
//                     paused: Long,
//                     pending: Long,
//                     ratings: Long,
//                     reviews: List[ReviewId]
                 )