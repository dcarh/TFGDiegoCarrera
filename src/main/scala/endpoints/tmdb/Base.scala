package endpoints.tmdb

import sttp.tapir.*
import endpoints.EndpointsUtils.httpMethodEndpoint
import endpoints.inputs.Common.*
import endpoints.inputs.TMDB.Query.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.ids.Media.{TvEpisodeNumber, MovieId, TvSeasonNumber, TvShowId}

object Base {

  private val tmdbBaseEndpoint:
    (String, String, String) => PublicEndpoint[String, UserError, Unit, Any] =
      (name, description, path) =>
        httpMethodEndpoint(name, description, path, "GET")
          .in(queryApiKey)
        
  val searchBaseEndpoint:
    (String, String) => PublicEndpoint[(String, String), UserError, Unit, Any] =
      (name, description) =>
        tmdbBaseEndpoint(name, description, "search")
          .in(QueryInputs.querySearch)

  val movieBaseEndpoint:
    (String, String) => PublicEndpoint[(String, MovieId), UserError, Unit, Any] =
      (name, description) =>
        tmdbBaseEndpoint(name, description, "movie")
          .in(PathInputs.pathMovieId)

  val tvShowBaseEndpoint:
    (String, String) => PublicEndpoint[(String, TvShowId), UserError, Unit, Any] =
      (name, description) =>
        tmdbBaseEndpoint(name, description, "tv")
          .in(PathInputs.pathTvShowId)

  val tvSeasonBaseEndpoint:
    (String, String) => PublicEndpoint[(String, TvShowId, TvSeasonNumber), UserError, Unit, Any] =
      (name, description) =>
        tvShowBaseEndpoint(name, description)
          .in("season")
          .in(PathInputs.pathTvSeasonNumber)

  val tvEpisodeBaseEndpoint:
    (String, String) => PublicEndpoint[(String, TvShowId, TvSeasonNumber, TvEpisodeNumber), UserError, Unit, Any] =
      (name, description) =>
        tvSeasonBaseEndpoint(name, description)
          .in("episode")
          .in(PathInputs.pathTvEpisodeNumber)
}
