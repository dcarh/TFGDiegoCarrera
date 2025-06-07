package endpoints.apis.googleBooks

import sttp.tapir.*
import endpoints.io.inputs.Common.*
import endpoints.io.inputs.GoogleBooks.*
import endpoints.io.outputs.GoogleBooks.*
import domain.ids.Media.BookId
import domain.apis.googleBooks.BooksRequests.*
import domain.errors.UserError.*

object Books {

  val requestBook: PublicEndpoint[BookId, UserError, BookFromGoogleBooks, Any] =
    Base.bookBaseEndpoint(
        "requestBook",
        "This endpoint returns a book from Google Books API by its ID"
      )
      .in(PathInputs.pathBookId)
      .out(jsonRequestedBookOut)

  val requestBookSearch: PublicEndpoint[(String, String, String, String), UserError, RequestedBookSearch, Any] =
    Base.bookBaseEndpoint(
        "requestBookSearch",
        "This endpoint returns a list of books from Google Books API by a search query"
      )
      .in(queryQ)
      .in(queryOrderBy)
      .in(queryProjection)
      .in(queryLangRestrict)
      .out(jsonRequestedBookSearchOut)
  
  val searchBooks: PublicEndpoint[(String, String), UserError, ListOfSearchedBooks, Any] =
    Base.bookBaseEndpoint(
        "searchBooks",
        "This endpoint returns a list of books from Google Books API by a search query"
      )
      .in(queryQ)
      .in(queryProjection)
      .out(listOfSearchedBooks)

}
