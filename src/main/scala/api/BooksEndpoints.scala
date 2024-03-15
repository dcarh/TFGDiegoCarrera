//package api
//
//import io.circe.generic.auto._
//import modelClasses.Book
//import sttp.tapir._
//import sttp.tapir.generic.auto._
//import sttp.tapir.json.circe._
//
//class BooksEndpoints {
//
//  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]
//
//  private val pathBookId: EndpointInput[Int] =
//    path[Int]("book_id")
//
//  private val queryOrderBy: EndpointInput[String] =
//    query[String]("order_by").description("Ordenar por")
//
//  private val jsonBookListOut: EndpointOutput[Seq[Book]] =
//    jsonBody[Seq[Book]]
//
//  private val jsonBookOut: EndpointOutput[Book] =
//    jsonBody[Book]
//
//
//  private val booksBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
//    endpoint.in("api" / "books")
//
//  private val bookBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
//    endpoint.in("api" / "book")
//
//
//  val booksEnpoint: PublicEndpoint[String, Unit, Seq[Book], Any] =
//    booksBaseEndpoint
//      .in(queryOrderBy)
//      .out(jsonBookListOut)
//
//  val specificBookEnpoint: PublicEndpoint[Int, Unit, Book, Any] =
//    bookBaseEndpoint
//      .in(pathBookId)
//      .out(jsonBookOut)
//  
//}
