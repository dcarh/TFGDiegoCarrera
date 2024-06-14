package endpoints.tmdb

import sttp.tapir.*

import endpoints.tmdb.Outputs.EpisodesOutputs._
import modelClasses.app.media.{Episode, Season, TVShow}
import modelClasses.tmdb.EpisodeRequests._
import modelClasses.ErrorInfo

object Episodes {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]
  
  val requestEpisodeEndpoint: PublicEndpoint[(String, TVShow.Id, Season.Number, Episode.Number), ErrorInfo, RequestedEpisode, Any] =
    Base.episodeBaseEndpoint(
        "Get TV show episode from TMDB", 
        "This endpoint a specific TV show episode from TMDB API by its ID"
      )
      .out(jsonRequestedEpisodeOut)

  val requestedCreditsForEpisodeEndpoint: PublicEndpoint[(String, TVShow.Id, Season.Number, Episode.Number), ErrorInfo, RequestedCreditsForEpisode, Any] =
    Base.episodeBaseEndpoint(
        "Get credits for a TV show episode from TMDB",
        "This endpoint returns the credits of a TV show episode specified by its ID from TMDB API"
      )
      .in("credits")
      .out(jsonRequestedCreditsForEpisodeOut)
}
