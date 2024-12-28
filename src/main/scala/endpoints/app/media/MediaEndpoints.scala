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
    (String, String, String) => PublicEndpoint[Unit, UserError, Unit, Any] =
      (name, description, category) => httpMethodEndpoint(name, description, "display/" + category, "GET")
//        .in(category)

  val getMovie: PublicEndpoint[MovieId, UserError, Movie, Any] =
    mediaBaseEndpoint(
      "Get movie endpoint",
      "This endpoint returns the movie specified by the ID introduced",
      "movie"
    )
      .in(PathInputs.pathMovieId)
      .out(MediaOutputs.movieSuccess)

  val getTvShow: PublicEndpoint[TVShowId, UserError, TVShow, Any] =
    mediaBaseEndpoint(
      "Get TV show endpoint",
      "This endpoint returns the TV show specified by the ID introduced",
      "tv_show"
    )
      .in(PathInputs.pathTVShowId)
      .out(MediaOutputs.tvShowSuccess)

  val getSeason: PublicEndpoint[(TVShowId, SeasonNumber), UserError, Season, Any] =
    mediaBaseEndpoint(
      "Get season endpoint",
      "This endpoint returns the season specified by the ID introduced",
      "tv_show"
    )
      .in(PathInputs.pathTVShowId)
      .in("season")
      .in(PathInputs.pathSeasonNumber)
      .out(MediaOutputs.seasonSuccess)

  val getEpisode: PublicEndpoint[(TVShowId, SeasonNumber, EpisodeNumber), UserError, Episode, Any] =
    mediaBaseEndpoint(
      "Get episode endpoint",
      "This endpoint returns the episode specified by the ID introduced",
      "tv_show"
    )
      .in(PathInputs.pathTVShowId)
      .in("season")
      .in(PathInputs.pathSeasonNumber)
      .in("episode")
      .in(PathInputs.pathEpisodeNumber)
      .out(MediaOutputs.episodeSuccess)

  val getVideogame: PublicEndpoint[VideogameId, UserError, Videogame, Any] =
    mediaBaseEndpoint(
      "Get videogame endpoint",
      "This endpoint returns the videogame specified by the ID introduced",
      "videogame"
    )
      .in(PathInputs.pathVideogameId)
      .out(MediaOutputs.videogameSuccess)

  val getBook: PublicEndpoint[BookId, UserError, Book, Any] =
    mediaBaseEndpoint(
      "Get book endpoint",
      "This endpoint returns the book specified by the ID introduced",
      "book"
    )
      .in(PathInputs.pathBookId)
      .out(MediaOutputs.bookSuccess)

}
