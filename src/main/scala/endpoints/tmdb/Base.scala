package endpoints.tmdb

import sttp.tapir.*
import endpoints.EndpointsUtils.httpMethodEndpoint
import endpoints.inputs.Common.*
import endpoints.inputs.TMDB.Query.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.ids.Media.{EpisodeNumber, MovieId, SeasonNumber, TVShowId}

object Base {

//  private val tmdbBaseEndpoint:
//    (String, String) => PublicEndpoint[String, ErrorInfo, Unit, Any] =
//      (name, description) =>
//        endpoint
//          .name(name)
//          .description(description)
//          .get
//          .in(queryApiKey)
//          .errorOut(ErrorOutputs.jsonErrorInfoOut)

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
    (String, String) => PublicEndpoint[(String, TVShowId), UserError, Unit, Any] =
      (name, description) =>
        tmdbBaseEndpoint(name, description, "tv")
          .in(PathInputs.pathTVShowId)

  val seasonBaseEndpoint:
    (String, String) => PublicEndpoint[(String, TVShowId, SeasonNumber), UserError, Unit, Any] =
      (name, description) =>
        tvShowBaseEndpoint(name, description)
          .in("season")
          .in(PathInputs.pathSeasonNumber)

  val episodeBaseEndpoint:
    (String, String) => PublicEndpoint[(String, TVShowId, SeasonNumber, EpisodeNumber), UserError, Unit, Any] =
      (name, description) =>
        seasonBaseEndpoint(name, description)
          .in("episode")
          .in(PathInputs.pathEpisodeNumber)
}
