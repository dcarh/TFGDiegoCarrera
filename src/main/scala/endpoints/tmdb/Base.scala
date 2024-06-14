package endpoints.tmdb

import sttp.tapir.*

import endpoints.common.Inputs._
import endpoints.common.Outputs._
import modelClasses.app.media.{Episode, Movie, Season, TVShow}
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
          .in(QueryInputs.queryApiKey)
          .errorOut(ApiOutputs.jsonErrorInfoOut)

  val movieBaseEndpoint:
    (String, String) => PublicEndpoint[(String, Movie.Id), ErrorInfo, Unit, Any] =
      (name, description) =>
        tmdbBaseEndpoint(name, description)
          .in("movie")
          .in(PathInputs.pathMovieId)

  val tvShowBaseEndpoint:
    (String, String) => PublicEndpoint[(String, TVShow.Id), ErrorInfo, Unit, Any] =
      (name, description) =>
        tmdbBaseEndpoint(name, description)
          .in("tv")
          .in(PathInputs.pathTVShowId)

  val seasonBaseEndpoint:
    (String, String) => PublicEndpoint[(String, TVShow.Id, Season.Number), ErrorInfo, Unit, Any] =
      (name, description) =>
        tvShowBaseEndpoint(name, description)
          .in("season")
          .in(PathInputs.pathSeasonNumber)

  val episodeBaseEndpoint:
    (String, String) => PublicEndpoint[(String, TVShow.Id, Season.Number, Episode.Number), ErrorInfo, Unit, Any] =
      (name, description) =>
        seasonBaseEndpoint(name, description)
          .in("episode")
          .in(PathInputs.pathEpisodeNumber)
}
