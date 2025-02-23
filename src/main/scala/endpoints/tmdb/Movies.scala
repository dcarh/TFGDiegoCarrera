package endpoints.tmdb

import endpoints.inputs.Common.QueryInputs
import sttp.tapir.*
import endpoints.outputs.TMDB.{jsonCreditsOut, jsonResultsOut}
import endpoints.outputs.TMDB.MoviesOutputs.*
import modelClasses.tmdb.MovieRequests.*
import modelClasses.ids.Media.MovieId
import modelClasses.errors.UserError.*
import modelClasses.tmdb.Common.{Credits, Results}

object Movies {

  val searchMoviesEndpoint: PublicEndpoint[(String, String), UserError, Results, Any] =
    Base.searchBaseEndpoint(
        "Search movies in TMDB",
        "This endpoint returns a list of movies from TMDB API by their title"
      )
      .in("movie")
      .out(jsonResultsOut)
  
  val requestMovieEndpoint: PublicEndpoint[(String, MovieId), UserError, RequestedMovie, Any] =
    Base.movieBaseEndpoint(
        "Get movie from TMDB", 
        "This endpoint returns a specific movie from TMDB API by its ID"
      )
      .out(jsonRequestedMovieOut)

  val requestedSimilarMoviesEndpoint: PublicEndpoint[(String, MovieId), UserError, Results, Any] =
    Base.movieBaseEndpoint(
        "Get similar movies from TMDB",
        "This endpoint returns a list of similar movies to a movie specified by its ID from TMDB API"
      )
      .in("similar")
      .out(jsonResultsOut)

  val requestedRecommendedMoviesEndpoint: PublicEndpoint[(String, MovieId), UserError, Results, Any] =
    Base.movieBaseEndpoint(
        "Get recommended movies from TMDB",
        "This endpoint returns a list of recommended movies by a movie specified by its ID from TMDB API"
      )
      .in("recommendations")
      .out(jsonResultsOut)

  val requestedCreditsForMovieEndpoint: PublicEndpoint[(String, MovieId), UserError, Credits, Any] =
    Base.movieBaseEndpoint(
        "Get credits for a movie from TMDB",
        "This endpoint returns the credits of a movie specified by its ID from TMDB API"
      )
      .in("credits")
      .out(jsonCreditsOut)
}
