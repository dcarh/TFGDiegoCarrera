package endpoints.apis.tmdb

import sttp.tapir.*
import endpoints.outputs.TMDB.jsonCreditsOut
import endpoints.outputs.TMDB.SeasonsOutputs.*
import domain.tmdb.TvSeasonRequests.*
import domain.errors.UserError.*
import domain.ids.Media.{TvSeasonNumber, TvShowId}
import domain.tmdb.Common.Credits

object TvSeasons {

  val requestTvSeason: PublicEndpoint[(String, TvShowId, TvSeasonNumber), UserError, TvSeasonFromTMDB, Any] =
    Base.tvSeasonBaseEndpoint(
        "requestTvSeason", 
        "This endpoint returns a TV season from TMDB API by its ID"
      )
      .out(jsonRequestedTvSeasonOut)

  val requestedCreditsForTvSeason: PublicEndpoint[(String, TvShowId, TvSeasonNumber), UserError, Credits, Any] =
    Base.tvSeasonBaseEndpoint(
        "requestedCreditsForTvSeason",
        "This endpoint returns the credits of a TV season specified by its ID from TMDB API"
      )
      .in("credits")
      .out(jsonCreditsOut)

  val requestedAggregateCreditsForTvSeason: PublicEndpoint[(String, TvShowId, TvSeasonNumber), UserError, Credits, Any] =
    Base.tvSeasonBaseEndpoint(
        "requestedAggregateCreditsForTvSeason",
        "This endpoint returns the aggregate credits of a TV season specified by its ID from TMDB API"
      )
      .in("aggregate_credits")
      .out(jsonCreditsOut)
}
