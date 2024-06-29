package endpoints.googleBooks

import sttp.tapir.*

import endpoints.common.Inputs._
import endpoints.googleBooks.Inputs.SearchInputs
import endpoints.googleBooks.Outputs.BooksOutputs._
import modelClasses.app.media.IDs.BookId
import modelClasses.googleBooks.BooksRequests._
import modelClasses.ErrorInfo

object Books {

  private type PublicEndpoint[I, E, O, -R] = Endpoint[Unit, I, E, O, R]

  val requestBookEndpoint: PublicEndpoint[BookId, ErrorInfo, RequestedBook, Any] =
    Base.bookBaseEndpoint(
        "Get book from Google Books",
        "This endpoint returns a specific book from Google Books API by its ID"
      )
      .in(PathInputs.pathBookIdNew)
      .out(jsonRequestedBookOut)

  val requestBookSearchEndpoint: PublicEndpoint[(String, String, String, String), ErrorInfo, RequestedBookSearch, Any] =
    Base.bookBaseEndpoint(
        "Get book search from Google Books",
        "This endpoint returns a list of books from Google Books API by a search query"
      )
      .in(SearchInputs.queryQ)
      .in(SearchInputs.queryLangRestrict)
      .in(SearchInputs.queryOrderBy)
      .in(SearchInputs.queryProjection)
      .out(jsonRequestedBookSearchOut)
    

}
