import cats.effect._

import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
// import sttp.tapir.EndpointIO.annotations._

import java.util.UUID

import io.circe.generic.auto._
import io.circe.syntax._
import io.circe.Printer

class PruebaEndpointGET {


  case class Movie(
                    title: String,
                    id: Int,
                    year: String,
                    releaseDates: List[String],
                    countries: List[String],
                    originalLanguages: List[String],
                    crew: List[String],
                    overview: String,
                    genres: List[Int],
                    productionCompanies: List[Int],
                    budget: Int
                  )

  val newMovie: Movie = Movie(
    "Moonlight",
    3357,
    "2016",
    List("2016-01-01"),
    List("US"),
    List("en"),
    List("Barry Jenkins", "Mahersala Ali", "Trevante Rhodes"),
    "Bla bla bla",
    List(18),
    List(24),
    8000000
  )

  /*
  val obtainMovies: EndpointInput[String] =
    query[String]("name")

  type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]
  val myFirstEndpoint: PublicEndpoint[String, String, Any, Any] = ???

  val listMoviesEndpoint: PublicEndpoint[String, Unit, List[Movie], Any] =
    endpoint.in("movies" / "search").in(obtainMovies).out(jsonBody[List[Movie]])
   */

}
