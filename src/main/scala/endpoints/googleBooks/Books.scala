package endpoints.googleBooks

import sttp.tapir.*
import endpoints.inputs.Common.*
import endpoints.inputs.GoogleBooks.*
import endpoints.outputs.GoogleBooks.*
import modelClasses.ids.Media.BookId
import modelClasses.googleBooks.BooksRequests.*
import modelClasses.errors.UserError.*

object Books {

  val requestBook: PublicEndpoint[BookId, UserError, RequestedBook, Any] =
    Base.bookBaseEndpoint(
        "requestBook",
        "This endpoint returns a specific book from Google Books API by its ID"
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
