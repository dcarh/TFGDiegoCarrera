package endpoints.tmdb

import sttp.tapir.*

import endpoints.EndpointsUtils.appBaseEndpoint
import endpoints.inputs.Common._
import endpoints.inputs.TMDB.Query._
import endpoints.outputs.Common._

import modelClasses.ErrorInfo
import modelClasses.ids.Media.{MovieId, TVShowId, SeasonNumber, EpisodeNumber}

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
    (String, String, String) => PublicEndpoint[String, ErrorInfo, Unit, Any] =
      (name, description, path) =>
        appBaseEndpoint(name, description, path, "GET")
          .in(queryApiKey)
        
  val searchBaseEndpoint:
    (String, String) => PublicEndpoint[String, ErrorInfo, Unit, Any] =
      (name, description) =>
        tmdbBaseEndpoint(name, description, "search")

  val movieBaseEndpoint:
    (String, String) => PublicEndpoint[(String, MovieId), ErrorInfo, Unit, Any] =
      (name, description) =>
        tmdbBaseEndpoint(name, description, "movie")
          .in(PathInputs.pathMovieId)

  val tvShowBaseEndpoint:
    (String, String) => PublicEndpoint[(String, TVShowId), ErrorInfo, Unit, Any] =
      (name, description) =>
        tmdbBaseEndpoint(name, description, "tv")
          .in(PathInputs.pathTVShowId)

  val seasonBaseEndpoint:
    (String, String) => PublicEndpoint[(String, TVShowId, SeasonNumber), ErrorInfo, Unit, Any] =
      (name, description) =>
        tvShowBaseEndpoint(name, description)
          .in("season")
          .in(PathInputs.pathSeasonNumber)

  val episodeBaseEndpoint:
    (String, String) => PublicEndpoint[(String, TVShowId, SeasonNumber, EpisodeNumber), ErrorInfo, Unit, Any] =
      (name, description) =>
        seasonBaseEndpoint(name, description)
          .in("episode")
          .in(PathInputs.pathEpisodeNumber)
}
