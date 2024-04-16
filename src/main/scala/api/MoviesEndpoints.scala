package api

import io.circe.generic.auto._
import modelClasses.Movie
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._

class MoviesEndpoints {
  
  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val pathMovieId: EndpointInput[Movie.Id] =
    path[Movie.Id]("movie_id")

  private val queryOrderBy: EndpointInput[String] =
    query[String]("order_by").description("Ordenar por")

  private val jsonMovieListOut: EndpointOutput[List[Movie]] =
    jsonBody[List[Movie]]

  private val jsonMovieOut: EndpointOutput[Movie] =
    jsonBody[Movie]


  private val moviesBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "movies")

  private val movieBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "movie")


  val moviesEndpoint: PublicEndpoint[String, Unit, List[Movie], Any] =
    moviesBaseEndpoint
      .name("Movies endpoint")
      .description("This endpoint returns a list with all the movies in the app")
      .get
      .in(queryOrderBy)
      .out(jsonMovieListOut)

  val specificMovieEndpoint: PublicEndpoint[Movie.Id, Unit, Movie, Any] =
    movieBaseEndpoint
      .name("Specific movie endpoint")
      .description("This endpoint returns a specific movie by its Id")
      .get
      .in(pathMovieId)
      .out(jsonMovieOut)
}
