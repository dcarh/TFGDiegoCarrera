package endpoints.tmdb

import sttp.tapir.*

import endpoints.outputs.TMDB.SeasonsOutputs._

import modelClasses.tmdb.TvSeasonRequests._
import modelClasses.errors.UserError.*
import modelClasses.ids.Media.{TvShowId, TvSeasonNumber}

object TvSeasons {

  val requestTvSeasonEndpoint: PublicEndpoint[(String, TvShowId, TvSeasonNumber), UserError, RequestedTvSeason, Any] =
    Base.tvSeasonBaseEndpoint(
        "Get TV show season from TMDB", 
        "This endpoint returns a specific TV show season from TMDB API by its ID"
      )
      .out(jsonRequestedTvSeasonOut)

  val requestedCreditsForTvSeasonEndpoint: PublicEndpoint[(String, TvShowId, TvSeasonNumber), UserError, RequestedCreditsForTvSeason, Any] =
    Base.tvSeasonBaseEndpoint(
        "Get credits for a TV show season from TMDB",
        "This endpoint returns the credits of a TV show season specified by its ID from TMDB API"
      )
      .in("credits")
      .out(jsonRequestedCreditsForTvSeasonOut)

  val requestedAggregateCreditsForTvSeasonEndpoint: PublicEndpoint[(String, TvShowId, TvSeasonNumber), UserError, RequestedAggregateCreditsForTvSeason, Any] =
    Base.tvSeasonBaseEndpoint(
        "Get aggregate credits for a TV show season from TMDB",
        "This endpoint returns the aggregate credits of a TV show season specified by its ID from TMDB API"
      )
      .in("aggregate_credits")
      .out(jsonRequestedAggregateCreditsForTvSeasonOut)
}
