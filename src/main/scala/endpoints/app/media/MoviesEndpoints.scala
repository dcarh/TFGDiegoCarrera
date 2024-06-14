package endpoints.app.media

import sttp.tapir._

import endpoints.common.Inputs._
import endpoints.common.Outputs._
import modelClasses.app.media.Movie

object MoviesEndpoints {
  
  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val moviesBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "movies")

  private val movieBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "movie")
  
  val moviesEndpoint: PublicEndpoint[Option[String], Unit, List[Movie], Any] =
    moviesBaseEndpoint
      .name("Movies endpoint")
      .description("This endpoint returns a list with all the movies in the app")
      .get
      .in(QueryInputs.querySortBy)
      .out(MediaOutputs.jsonMovieListOut)

  val specificMovieEndpoint: PublicEndpoint[Movie.Id, Unit, Movie, Any] =
    movieBaseEndpoint
      .name("Specific movie endpoint")
      .description("This endpoint returns a specific movie by its Id")
      .get
      .in(PathInputs.pathMovieId)
      .out(MediaOutputs.jsonMovieOut)
}
