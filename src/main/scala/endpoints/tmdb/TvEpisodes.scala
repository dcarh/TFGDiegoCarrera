package endpoints.tmdb

import sttp.tapir.*
import endpoints.outputs.TMDB.jsonCreditsOut
import endpoints.outputs.TMDB.EpisodesOutputs.*
import modelClasses.tmdb.TvEpisodeRequests.*
import modelClasses.errors.UserError.*
import modelClasses.ids.Media.{TvEpisodeNumber, TvSeasonNumber, TvShowId}
import modelClasses.tmdb.Common.Credits

object TvEpisodes {
  
  val requestTvEpisode: PublicEndpoint[(String, TvShowId, TvSeasonNumber, TvEpisodeNumber), UserError, RequestedTvEpisode, Any] =
    Base.tvEpisodeBaseEndpoint(
        "requestTvEpisode", 
        "This endpoint returns a specific TV show episode from TMDB API by its ID"
      )
      .out(jsonRequestedTvEpisodeOut)

  val requestedCreditsForTvEpisode: PublicEndpoint[(String, TvShowId, TvSeasonNumber, TvEpisodeNumber), UserError, Credits, Any] =
    Base.tvEpisodeBaseEndpoint(
        "requestedCreditsForTvEpisode",
        "This endpoint returns the credits of a TV show episode specified by its ID from TMDB API"
      )
      .in("credits")
      .out(jsonCreditsOut)
}
