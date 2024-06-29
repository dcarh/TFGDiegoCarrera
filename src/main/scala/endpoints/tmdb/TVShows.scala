package endpoints.tmdb

import sttp.tapir.*

import endpoints.tmdb.Outputs.TVShowsOutputs._
import modelClasses.app.media.IDs.TVShowId
import modelClasses.tmdb.TVShowRequests._
import modelClasses.ErrorInfo

object TVShows {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]
  
  val requestTvShowEndpoint: PublicEndpoint[(String, TVShowId), ErrorInfo, RequestedTVShow, Any] =
    Base.tvShowBaseEndpoint(
        "Get TV Show from TMDB", 
        "This endpoint a specific TV Show from TMDB API by its ID"
      )
      .out(jsonRequestedTvShowOut)

  val requestedSimilarTvShowsEndpoint: PublicEndpoint[(String, TVShowId), ErrorInfo, RequestedSimilarTVShows, Any] =
    Base.tvShowBaseEndpoint(
        "Get similar TV shows from TMDB", 
        "This endpoint returns a list of similar TV shows to a show specified by its ID from TMDB API"
      )
      .in("similar")
      .out(jsonRequestedSimilarTvShowsOut)

  val requestedRecommendedTvShowsEndpoint: PublicEndpoint[(String, TVShowId), ErrorInfo, RequestedRecommendedTVShows, Any] =
    Base.tvShowBaseEndpoint(
        "Get recommended TV shows from TMDB", 
        "This endpoint returns a list of recommendated TV shows by a show specified by its ID from TMDB API"
      )
      .in("recommendations")
      .out(jsonRequestedRecommendedTvShowsOut)

  val requestedCreditsForTvShowEndpoint: PublicEndpoint[(String, TVShowId), ErrorInfo, RequestedCreditsForTVShow, Any] =
    Base.tvShowBaseEndpoint(
        "Get credits for a TV show from TMDB",
        "This endpoint returns the credits of a TV show specified by its ID from TMDB API"
      )
      .in("credits")
      .out(jsonRequestedCreditsForTvShowOut)

  val requestedAggregateCreditsForTvShowEndpoint: PublicEndpoint[(String, TVShowId), ErrorInfo, RequestedAggregateCreditsForTVShow, Any] =
    Base.tvShowBaseEndpoint(
        "Get aggregate credits for a TV show from TMDB",
        "This endpoint returns the aggregate credits of a TV show specified by its ID from TMDB API"
      )
      .in("aggregate_credits")
      .out(jsonRequestedAggregateCreditsForTvShowOut)
}
