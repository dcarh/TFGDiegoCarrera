package endpoints.app.media

import sttp.tapir._

import endpoints.common.Inputs._
import endpoints.common.Outputs._
import modelClasses.app.media.Book

object BooksEndpoints {

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
      .in(QueryInputs.querySortBy)
      .out(MediaOutputs.jsonBookListOut)

  val specificBookEndpoint: PublicEndpoint[Book.Id, Unit, Book, Any] =
    bookBaseEndpoint
      .name("Specific book endpoint")
      .description("This endpoint returns a specific book by its Id")
      .get
      .in(PathInputs.pathBookId)
      .out(MediaOutputs.jsonBookOut)
  
}
