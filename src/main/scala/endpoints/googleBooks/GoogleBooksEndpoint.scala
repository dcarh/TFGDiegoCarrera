package endpoints.googleBooks

import io.circe.generic.auto.*
import sttp.tapir.*
import sttp.tapir.generic.auto.*
import sttp.tapir.json.circe.*

class GoogleBooksEndpoint {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]
  
  case class BookRequested(id: Int, title: String, overview: String)

  // Define el endpoint para obtener información de un libro según su ID.
  val exampleGetBookEndpoint: PublicEndpoint[(String, String), Unit, BookRequested, Any] =
    endpoint
      .name("Get book from Google Books API")
      .description("This endpoint a specific book from Google Books API by its ID")
      .get
      .in("movie")
      .in(path[String]("book_id"))
      .in(query[String]("api_key"))
      .out(jsonBody[BookRequested])

}
