package endpoints.googleBooks

import sttp.tapir.*

import endpoints.inputs.Common._
import endpoints.inputs.GoogleBooks._
import endpoints.outputs.GoogleBooks._

import modelClasses.ids.Media.BookId
import modelClasses.googleBooks.BooksRequests._
import modelClasses.ErrorInfo

object Books {

  val requestBookEndpoint: PublicEndpoint[BookId, ErrorInfo, RequestedBook, Any] =
    Base.bookBaseEndpoint(
        "Get book from Google Books",
        "This endpoint returns a specific book from Google Books API by its ID"
      )
      .in(PathInputs.pathBookId)
      .out(jsonRequestedBookOut)

  val requestBookSearchEndpoint: PublicEndpoint[(String, String, String, String), ErrorInfo, RequestedBookSearch, Any] =
    Base.bookBaseEndpoint(
        "Get book search from Google Books",
        "This endpoint returns a list of books from Google Books API by a search query"
      )
      .in(queryQ)
      .in(queryLangRestrict)
      .in(queryOrderBy)
      .in(queryProjection)
      .out(jsonRequestedBookSearchOut)
    

}
