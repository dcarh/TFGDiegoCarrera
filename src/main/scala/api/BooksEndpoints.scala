package api

import io.circe.generic.auto._
import modelClasses.Book
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._

class BooksEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val pathBookId: EndpointInput[Int] =
    path[Int]("book_id")

  private val queryOrderBy: EndpointInput[String] =
    query[String]("order_by").description("Ordenar por")

  private val jsonBookListOut: EndpointOutput[Seq[Book]] =
    jsonBody[Seq[Book]]

  private val jsonBookOut: EndpointOutput[Book] =
    jsonBody[Book]


  private val booksBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "books")

  private val bookBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "book")


  val booksEndpoint: PublicEndpoint[String, Unit, Seq[Book], Any] =
    booksBaseEndpoint
      .name("Books endpoint")
      .description("This endpoint returns a list with all the books in the app")
      .get
      .in(queryOrderBy)
      .out(jsonBookListOut)

  val specificBookEndpoint: PublicEndpoint[Int, Unit, Book, Any] =
    bookBaseEndpoint
      .name("Specific book endpoint")
      .description("This endpoint returns a specific book by its Id")
      .get
      .in(pathBookId)
      .out(jsonBookOut)
  
}
