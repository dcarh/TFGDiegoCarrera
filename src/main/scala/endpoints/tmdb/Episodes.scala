package endpoints.tmdb

import sttp.tapir.*

import endpoints.outputs.TMDB.EpisodesOutputs._

import modelClasses.tmdb.EpisodeRequests._
import modelClasses.errors.UserError.*
import modelClasses.ids.Media.{TVShowId, SeasonNumber, EpisodeNumber}

object Episodes {
  
  val requestEpisodeEndpoint: PublicEndpoint[(String, TVShowId, SeasonNumber, EpisodeNumber), UserError, RequestedEpisode, Any] =
    Base.episodeBaseEndpoint(
        "Get TV show episode from TMDB", 
        "This endpoint returns a specific TV show episode from TMDB API by its ID"
      )
      .out(jsonRequestedEpisodeOut)

  val requestedCreditsForEpisodeEndpoint: PublicEndpoint[(String, TVShowId, SeasonNumber, EpisodeNumber), UserError, RequestedCreditsForEpisode, Any] =
    Base.episodeBaseEndpoint(
        "Get credits for a TV show episode from TMDB",
        "This endpoint returns the credits of a TV show episode specified by its ID from TMDB API"
      )
      .in("credits")
      .out(jsonRequestedCreditsForEpisodeOut)
}
