package endpoints.apis.tmdb

import sttp.tapir.*
import endpoints.io.outputs.TMDB.{jsonCreditsOut, jsonResultsOut}
import endpoints.io.outputs.TMDB.TVShowsOutputs.*
import domain.ids.Media.TvShowId
import domain.apis.tmdb.TvShowRequests.*
import domain.errors.UserError.*
import domain.apis.tmdb.Common.{Credits, Results}

object TvShows {

  val searchTvShows: PublicEndpoint[(String, String), UserError, Results, Any] =
    Base.searchBaseEndpoint(
        "searchTvShows",
        "This endpoint returns a list of movies from TMDB API by their title"
      )
      .in("tv")
      .out(jsonResultsOut)
  
  val requestTvShow: PublicEndpoint[(String, TvShowId), UserError, TvShowFromTMDB, Any] =
    Base.tvShowBaseEndpoint(
        "requestTvShow", 
        "This endpoint returns a TV show from TMDB API by its ID"
      )
      .out(jsonRequestedTvShowOut)

  val requestedSimilarTvShows: PublicEndpoint[(String, TvShowId), UserError, Results, Any] =
    Base.tvShowBaseEndpoint(
        "requestedSimilarTvShows", 
        "This endpoint returns a list of TV shows similar to the show specified by its ID from TMDB API"
      )
      .in("similar")
      .out(jsonResultsOut)

  val requestedRecommendedTvShows: PublicEndpoint[(String, TvShowId), UserError, Results, Any] =
    Base.tvShowBaseEndpoint(
        "requestedRecommendedTvShows", 
        "This endpoint returns a list of recommended TV shows if you liked the TV show specified by its ID"
      )
      .in("recommendations")
      .out(jsonResultsOut)

  val requestedCreditsForTvShow: PublicEndpoint[(String, TvShowId), UserError, Credits, Any] =
    Base.tvShowBaseEndpoint(
        "requestedCreditsForTvShow",
        "This endpoint returns the credits of a TV show specified by its ID from TMDB API"
      )
      .in("credits")
      .out(jsonCreditsOut)

  val requestedAggregateCreditsForTvShow: PublicEndpoint[(String, TvShowId), UserError, Credits, Any] =
    Base.tvShowBaseEndpoint(
        "requestedAggregateCreditsForTvShow",
        "This endpoint returns the aggregate credits of a TV show specified by its ID from TMDB API"
      )
      .in("aggregate_credits")
      .out(jsonCreditsOut)
}
