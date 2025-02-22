package modelClasses.app.media

import io.circe.generic.auto.*
import modelClasses.ids.Media.{TvShowId, TvSeasonNumber}
import modelClasses.ids.Social.{LikeId, ReviewId}
import modelClasses.app.social.{Like, Review}

case class TvSeason(
                     airDate: String,
                     // episodesIds: List[EpisodeId],
                     numberOfEpisodes: Int,
                     overview: String,
                     seasonNumber: TvSeasonNumber,
                     title: String,
                     tvShowId: TvShowId,

                     abandoned: Long,
                     averageRating: Double,
                     completed: Long,
                     inProgress: Long,
                     paused: Long,
                     pending: Long,
                     ratings: Long,
                     reviews: List[ReviewId]
                 )