package endpoints.app.media

import sttp.tapir.*
import endpoints.EndpointsUtils.httpMethodEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.media.*
import modelClasses.ids.Media.*

object MediaEndpoints {

  private val mediaBaseEndpoint:
    (String, String) => PublicEndpoint[Unit, UserError, Unit, Any] =
      (name, description) => httpMethodEndpoint(name, description, "display", "GET")

  val getMovie: PublicEndpoint[MovieId, UserError, Movie, Any] =
    mediaBaseEndpoint(
      "getMovie",
      "This endpoint returns the movie specified by the ID introduced"
    )
      .in("movie")
      .in(PathInputs.pathMovieId)
      .out(MediaOutputs.movieOutput)

  val getTvShow: PublicEndpoint[TvShowId, UserError, TvShow, Any] =
    mediaBaseEndpoint(
      "getTvShow",
      "This endpoint returns the TV show specified by the ID introduced"
    )
      .in("tv_show")
      .in(PathInputs.pathTvShowId)
      .out(MediaOutputs.tvShowOutput)

  val getTvSeason: PublicEndpoint[(TvShowId, TvSeasonNumber), UserError, TvSeason, Any] =
    mediaBaseEndpoint(
      "getSeason",
      "This endpoint returns the season specified by the ID introduced"
    )
      .in("tv_show")
      .in(PathInputs.pathTvShowId)
      .in("season")
      .in(PathInputs.pathTvSeasonNumber)
      .out(MediaOutputs.seasonOutput)

  val getTvEpisode: PublicEndpoint[(TvShowId, TvSeasonNumber, TvEpisodeNumber), UserError, TvEpisode, Any] =
    mediaBaseEndpoint(
      "getEpisode",
      "This endpoint returns the episode specified by the ID introduced"
    )
      .in("tv_show")
      .in(PathInputs.pathTvShowId)
      .in("season")
      .in(PathInputs.pathTvSeasonNumber)
      .in("episode")
      .in(PathInputs.pathTvEpisodeNumber)
      .out(MediaOutputs.episodeOutput)

  val getVideogame: PublicEndpoint[VideogameId, UserError, Videogame, Any] =
    mediaBaseEndpoint(
      "getVideogame",
      "This endpoint returns the videogame specified by the ID introduced"
    )
      .in("videogame")
      .in(PathInputs.pathVideogameId)
      .out(MediaOutputs.videogameOutput)

  val getBook: PublicEndpoint[BookId, UserError, Book, Any] =
    mediaBaseEndpoint(
      "getBook",
      "This endpoint returns the book specified by the ID introduced"
    )
      .in("book")
      .in(PathInputs.pathBookId)
      .out(MediaOutputs.bookOutput)

}
