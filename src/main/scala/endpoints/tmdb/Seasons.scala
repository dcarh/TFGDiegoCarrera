package endpoints.tmdb

import sttp.tapir.*

import endpoints.outputs.TMDB.SeasonsOutputs._

import modelClasses.tmdb.SeasonRequests._
import modelClasses.errors.UserError.*
import modelClasses.ids.Media.{TvShowId, SeasonNumber}

object Seasons {

  val requestSeasonEndpoint: PublicEndpoint[(String, TvShowId, SeasonNumber), UserError, RequestedSeason, Any] =
    Base.seasonBaseEndpoint(
        "Get TV show season from TMDB", 
        "This endpoint returns a specific TV show season from TMDB API by its ID"
      )
      .out(jsonRequestedSeasonOut)

  val requestedCreditsForSeasonEndpoint: PublicEndpoint[(String, TvShowId, SeasonNumber), UserError, RequestedCreditsForSeason, Any] =
    Base.seasonBaseEndpoint(
        "Get credits for a TV show season from TMDB",
        "This endpoint returns the credits of a TV show season specified by its ID from TMDB API"
      )
      .in("credits")
      .out(jsonRequestedCreditsForSeasonOut)

  val requestedAggregateCreditsForSeasonEndpoint: PublicEndpoint[(String, TvShowId, SeasonNumber), UserError, RequestedAggregateCreditsForSeason, Any] =
    Base.seasonBaseEndpoint(
        "Get aggregate credits for a TV show season from TMDB",
        "This endpoint returns the aggregate credits of a TV show season specified by its ID from TMDB API"
      )
      .in("aggregate_credits")
      .out(jsonRequestedAggregateCreditsForSeasonOut)
}
