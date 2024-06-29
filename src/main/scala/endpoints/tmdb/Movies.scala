package endpoints.tmdb

import sttp.tapir.*

import endpoints.tmdb.Outputs.MoviesOutputs._
import modelClasses.tmdb.MovieRequests._
import modelClasses.app.media.IDs.MovieId
import modelClasses.ErrorInfo

object Movies {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]
  
  val requestMovieEndpoint: PublicEndpoint[(String, MovieId), ErrorInfo, RequestedMovie, Any] =
    Base.movieBaseEndpoint(
        "Get movie from TMDB", 
        "This endpoint returns a specific movie from TMDB API by its ID"
      )
      .out(jsonRequestedMovieOut)

  val requestedSimilarMoviesEndpoint: PublicEndpoint[(String, MovieId), ErrorInfo, RequestedSimilarMovies, Any] =
    Base.movieBaseEndpoint(
        "Get similar movies from TMDB",
        "This endpoint returns a list of similar movies to a movie specified by its ID from TMDB API"
      )
      .in("similar")
      .out(jsonRequestedSimilarMoviesOut)

  val requestedRecommendedMoviesEndpoint: PublicEndpoint[(String, MovieId), ErrorInfo, RequestedRecommendedMovies, Any] =
    Base.movieBaseEndpoint(
        "Get recommended movies from TMDB",
        "This endpoint returns a list of recommended movies by a movie specified by its ID from TMDB API"
      )
      .in("recommendations")
      .out(jsonRequestedRecommendedMoviesOut)

  val requestedCreditsForMovieEndpoint: PublicEndpoint[(String, MovieId), ErrorInfo, RequestedCreditsForMovie, Any] =
    Base.movieBaseEndpoint(
        "Get credits for a movie from TMDB",
        "This endpoint returns the credits of a movie specified by its ID from TMDB API"
      )
      .in("credits")
      .out(jsonRequestedCreditsForMovieOut)
}
