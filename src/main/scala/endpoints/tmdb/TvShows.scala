package endpoints.tmdb

import sttp.tapir.*

import endpoints.outputs.TMDB.TVShowsOutputs._

import modelClasses.ids.Media.TvShowId
import modelClasses.tmdb.TvShowRequests._
import modelClasses.errors.UserError.*

object TvShows {

  val searchTvShowsEndpoint: PublicEndpoint[(String, String), UserError, List[RequestedTvShow], Any] =
    Base.searchBaseEndpoint(
        "Search TV shows in TMDB",
        "This endpoint returns a list of movies from TMDB API by their title"
      )
      .out(jsonRequestedTvShowsListOut)
  
  val requestTvShowEndpoint: PublicEndpoint[(String, TvShowId), UserError, RequestedTvShow, Any] =
    Base.tvShowBaseEndpoint(
        "Get TV Show from TMDB", 
        "This endpoint returns a specific TV Show from TMDB API by its ID"
      )
      .out(jsonRequestedTvShowOut)

  val requestedSimilarTvShowsEndpoint: PublicEndpoint[(String, TvShowId), UserError, RequestedSimilarTvShows, Any] =
    Base.tvShowBaseEndpoint(
        "Get similar TV shows from TMDB", 
        "This endpoint returns a list of similar TV shows to a show specified by its ID from TMDB API"
      )
      .in("similar")
      .out(jsonRequestedSimilarTvShowsOut)

  val requestedRecommendedTvShowsEndpoint: PublicEndpoint[(String, TvShowId), UserError, RequestedRecommendedTvShows, Any] =
    Base.tvShowBaseEndpoint(
        "Get recommended TV shows from TMDB", 
        "This endpoint returns a list of recommendated TV shows by a show specified by its ID from TMDB API"
      )
      .in("recommendations")
      .out(jsonRequestedRecommendedTvShowsOut)

  val requestedCreditsForTvShowEndpoint: PublicEndpoint[(String, TvShowId), UserError, RequestedCreditsForTvShow, Any] =
    Base.tvShowBaseEndpoint(
        "Get credits for a TV show from TMDB",
        "This endpoint returns the credits of a TV show specified by its ID from TMDB API"
      )
      .in("credits")
      .out(jsonRequestedCreditsForTvShowOut)

  val requestedAggregateCreditsForTvShowEndpoint: PublicEndpoint[(String, TvShowId), UserError, RequestedAggregateCreditsForTvShow, Any] =
    Base.tvShowBaseEndpoint(
        "Get aggregate credits for a TV show from TMDB",
        "This endpoint returns the aggregate credits of a TV show specified by its ID from TMDB API"
      )
      .in("aggregate_credits")
      .out(jsonRequestedAggregateCreditsForTvShowOut)
}
