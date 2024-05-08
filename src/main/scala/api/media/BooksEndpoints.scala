package api.media

import sttp.tapir._

import modelClasses.media.Book
import api.common.Inputs.inputs
import api.common.Outputs.outputs

class BooksEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val booksBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "books")

  private val bookBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "book")
  
  val booksEndpoint: PublicEndpoint[Option[String], Unit, List[Book], Any] =
    booksBaseEndpoint
      .name("Books endpoint")
      .description("This endpoint returns a list with all the books in the app")
      .get
      .in(inputs.querySortBy)
      .out(outputs.jsonBookListOut)

  val specificBookEndpoint: PublicEndpoint[Book.Id, Unit, Book, Any] =
    bookBaseEndpoint
      .name("Specific book endpoint")
      .description("This endpoint returns a specific book by its Id")
      .get
      .in(inputs.pathBookId)
      .out(outputs.jsonBookOut)
  
}
