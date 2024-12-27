package endpoints.tmdb

import sttp.tapir.*

import endpoints.outputs.TMDB.TVShowsOutputs._

import modelClasses.ids.Media.TVShowId
import modelClasses.tmdb.TVShowRequests._
import modelClasses.errors.UserError.*

object TVShows {

  val searchTvShowsEndpoint: PublicEndpoint[(String, String), UserError, List[RequestedTVShow], Any] =
    Base.searchBaseEndpoint(
        "Search movies in TMDB",
        "This endpoint returns a list of movies from TMDB API by their title"
      )
      .out(jsonRequestedTvShowsListOut)
  
  val requestTvShowEndpoint: PublicEndpoint[(String, TVShowId), UserError, RequestedTVShow, Any] =
    Base.tvShowBaseEndpoint(
        "Get TV Show from TMDB", 
        "This endpoint returns a specific TV Show from TMDB API by its ID"
      )
      .out(jsonRequestedTvShowOut)

  val requestedSimilarTvShowsEndpoint: PublicEndpoint[(String, TVShowId), UserError, RequestedSimilarTVShows, Any] =
    Base.tvShowBaseEndpoint(
        "Get similar TV shows from TMDB", 
        "This endpoint returns a list of similar TV shows to a show specified by its ID from TMDB API"
      )
      .in("similar")
      .out(jsonRequestedSimilarTvShowsOut)

  val requestedRecommendedTvShowsEndpoint: PublicEndpoint[(String, TVShowId), UserError, RequestedRecommendedTVShows, Any] =
    Base.tvShowBaseEndpoint(
        "Get recommended TV shows from TMDB", 
        "This endpoint returns a list of recommendated TV shows by a show specified by its ID from TMDB API"
      )
      .in("recommendations")
      .out(jsonRequestedRecommendedTvShowsOut)

  val requestedCreditsForTvShowEndpoint: PublicEndpoint[(String, TVShowId), UserError, RequestedCreditsForTVShow, Any] =
    Base.tvShowBaseEndpoint(
        "Get credits for a TV show from TMDB",
        "This endpoint returns the credits of a TV show specified by its ID from TMDB API"
      )
      .in("credits")
      .out(jsonRequestedCreditsForTvShowOut)

  val requestedAggregateCreditsForTvShowEndpoint: PublicEndpoint[(String, TVShowId), UserError, RequestedAggregateCreditsForTVShow, Any] =
    Base.tvShowBaseEndpoint(
        "Get aggregate credits for a TV show from TMDB",
        "This endpoint returns the aggregate credits of a TV show specified by its ID from TMDB API"
      )
      .in("aggregate_credits")
      .out(jsonRequestedAggregateCreditsForTvShowOut)
}
