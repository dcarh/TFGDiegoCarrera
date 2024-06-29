package endpoints.tmdb

import sttp.tapir.*

import endpoints.inputs.Common._
import endpoints.outputs.Common._
import endpoints.inputs.TMDB.Query._
import modelClasses.app.media.IDs.{MovieId, TVShowId, SeasonNumber, EpisodeNumber}
import modelClasses.ErrorInfo

object Base {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val tmdbBaseEndpoint:
    (String, String) => PublicEndpoint[String, ErrorInfo, Unit, Any] =
      (name, description) =>
        endpoint
          .name(name)
          .description(description)
          .get
          .in(queryApiKey)
          .errorOut(ApiOutputs.jsonErrorInfoOut)
        
  val searchBaseEndpoint:
    (String, String) => PublicEndpoint[String, ErrorInfo, Unit, Any] =
      (name, descrption) =>
        tmdbBaseEndpoint(name, descrption)
          .get
          .in("search")

  val movieBaseEndpoint:
    (String, String) => PublicEndpoint[(String, MovieId), ErrorInfo, Unit, Any] =
      (name, description) =>
        tmdbBaseEndpoint(name, description)
          .in("movie")
          .in(PathInputs.pathMovieIdNew)

  val tvShowBaseEndpoint:
    (String, String) => PublicEndpoint[(String, TVShowId), ErrorInfo, Unit, Any] =
      (name, description) =>
        tmdbBaseEndpoint(name, description)
          .in("tv")
          .in(PathInputs.pathTVShowIdNew)

  val seasonBaseEndpoint:
    (String, String) => PublicEndpoint[(String, TVShowId, SeasonNumber), ErrorInfo, Unit, Any] =
      (name, description) =>
        tvShowBaseEndpoint(name, description)
          .in("season")
          .in(PathInputs.pathSeasonNumberNew)

  val episodeBaseEndpoint:
    (String, String) => PublicEndpoint[(String, TVShowId, SeasonNumber, EpisodeNumber), ErrorInfo, Unit, Any] =
      (name, description) =>
        seasonBaseEndpoint(name, description)
          .in("episode")
          .in(PathInputs.pathEpisodeNumberNew)
}
