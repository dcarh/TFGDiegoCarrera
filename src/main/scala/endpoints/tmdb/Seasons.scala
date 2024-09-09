package endpoints.tmdb

import sttp.tapir.*

import endpoints.outputs.TMDB.SeasonsOutputs._

import modelClasses.tmdb.SeasonRequests._
import modelClasses.ErrorInfo
import modelClasses.ids.Media.{TVShowId, SeasonNumber}

object Seasons {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  val requestSeasonEndpoint: PublicEndpoint[(String, TVShowId, SeasonNumber), ErrorInfo, RequestedSeason, Any] =
    Base.seasonBaseEndpoint(
        "Get TV show season from TMDB", 
        "This endpoint a specific TV show season from TMDB API by its ID"
      )
      .out(jsonRequestedSeasonOut)

  val requestedCreditsForSeasonEndpoint: PublicEndpoint[(String, TVShowId, SeasonNumber), ErrorInfo, RequestedCreditsForSeason, Any] =
    Base.seasonBaseEndpoint(
        "Get credits for a TV show season from TMDB",
        "This endpoint returns the credits of a TV show season specified by its ID from TMDB API"
      )
      .in("credits")
      .out(jsonRequestedCreditsForSeasonOut)

  val requestedAggregateCreditsForSeasonEndpoint: PublicEndpoint[(String, TVShowId, SeasonNumber), ErrorInfo, RequestedAggregateCreditsForSeason, Any] =
    Base.seasonBaseEndpoint(
        "Get aggregate credits for a TV show season from TMDB",
        "This endpoint returns the aggregate credits of a TV show season specified by its ID from TMDB API"
      )
      .in("aggregate_credits")
      .out(jsonRequestedAggregateCreditsForSeasonOut)
}
