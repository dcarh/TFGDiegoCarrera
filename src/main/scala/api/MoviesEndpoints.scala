package api

import cats.effect.*
import io.circe.Printer
import io.circe.generic.auto._
import io.circe.syntax.*
import model.{User,Element, Movie, TVShow, Season, Episode, Videogame, Book, ElementList, Log, Comment, Article,
  Settings, Review, ErrorInfo, Chat}
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import sttp.tapir.model.UsernamePassword
import sttp.model.StatusCode

import java.util.UUID

class MoviesEndpoints {
  
  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val pathMovieId: EndpointInput[Int] =
    path[Int]("movie_id")

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


  val moviesEnpoint: PublicEndpoint[String, Unit, List[Movie], Any] =
    moviesBaseEndpoint
      .in(queryOrderBy)
      .out(jsonMovieListOut)

  val specificMovieEnpoint: PublicEndpoint[Int, Unit, Movie, Any] =
    movieBaseEndpoint
      .in(pathMovieId)
      .out(jsonMovieOut)
}
