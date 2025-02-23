package modelClasses.app.media

import io.circe.generic.auto.*
import modelClasses.ids.Media.{TvSeasonNumber, TvShowId}
import modelClasses.ids.Social.{LikeId, ReviewId}
import modelClasses.app.social.{Like, Review}
import modelClasses.tmdb.TvSeasonRequests.RequestedTvSeason

case class TvSeason(
                     requestedTvSeason: RequestedTvSeason
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