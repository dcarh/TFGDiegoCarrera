package endpoints.apis.tmdb

import sttp.tapir.*
import endpoints.io.outputs.TMDB.{jsonCreditsOut, jsonResultsOut}
import endpoints.io.outputs.TMDB.MoviesOutputs.*
import domain.apis.tmdb.MovieRequests.*
import domain.ids.Media.MovieId
import domain.errors.UserError.*
import domain.apis.tmdb.Common.{Credits, Results}

object Movies {

  val searchMovies: PublicEndpoint[(String, String), UserError, Results, Any] =
    Base.searchBaseEndpoint(
        "searchMovies",
        "This endpoint returns a list of movies from TMDB API by their title"
      )
      .in("movie")
      .out(jsonResultsOut)
  
  val requestMovie: PublicEndpoint[(String, MovieId), UserError, MovieFromTMDB, Any] =
    Base.movieBaseEndpoint(
        "requestMovie", 
        "This endpoint returns a movie from TMDB API by its ID"
      )
      .out(jsonRequestedMovieOut)

  val requestedSimilarMovies: PublicEndpoint[(String, MovieId), UserError, Results, Any] =
    Base.movieBaseEndpoint(
        "requestedSimilarMovies",
        "This endpoint returns a list of movies similar to the movie specified by its ID from TMDB API"
      )
      .in("similar")
      .out(jsonResultsOut)

  val requestedRecommendedMovies: PublicEndpoint[(String, MovieId), UserError, Results, Any] =
    Base.movieBaseEndpoint(
        "requestedRecommendedMovies",
        "This endpoint returns a list of recommended movies if you liked the movie specified by its ID"
      )
      .in("recommendations")
      .out(jsonResultsOut)

  val requestedCreditsForMovie: PublicEndpoint[(String, MovieId), UserError, Credits, Any] =
    Base.movieBaseEndpoint(
        "requestedCreditsForMovie",
        "This endpoint returns the credits of a movie specified by its ID from TMDB API"
      )
      .in("credits")
      .out(jsonCreditsOut)
}
