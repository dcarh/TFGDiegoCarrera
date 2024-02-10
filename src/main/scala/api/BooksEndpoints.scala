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

class BooksEndpoints {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  private val pathBookId: EndpointInput[Int] =
    path[Int]("book_id")

  private val queryOrderBy: EndpointInput[String] =
    query[String]("order_by").description("Ordenar por")

  private val jsonBookListOut: EndpointOutput[List[Book]] =
    jsonBody[List[Book]]

  private val jsonBookOut: EndpointOutput[Book] =
    jsonBody[Book]


  private val booksBaseEndpoint: PublicEndpoint[Unit, Unit, Unit, Any] =
    endpoint.in("api" / "books")


  val booksEnpoint: PublicEndpoint[String, Unit, List[Book], Any] =
    booksBaseEndpoint
      .in(queryOrderBy)
      .out(jsonBookListOut)

  val specificBookEnpoint: PublicEndpoint[Int, Unit, Book, Any] =
    booksBaseEndpoint
      .in(pathBookId)
      .out(jsonBookOut)
  
}
