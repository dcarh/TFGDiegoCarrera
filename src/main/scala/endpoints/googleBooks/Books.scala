package endpoints.googleBooks

import sttp.tapir.*
import endpoints.inputs.Common.*
import endpoints.inputs.GoogleBooks.*
import endpoints.outputs.GoogleBooks.*
import modelClasses.ids.Media.BookId
import modelClasses.googleBooks.BooksRequests.*
import modelClasses.errors.UserError.*

object Books {

  val requestBookEndpoint: PublicEndpoint[BookId, UserError, RequestedBook, Any] =
    Base.bookBaseEndpoint(
        "Get book from Google Books",
        "This endpoint returns a specific book from Google Books API by its ID"
      )
      .in(PathInputs.pathBookId)
      .out(jsonRequestedBookOut)

  val requestBookSearchEndpoint: PublicEndpoint[(String, String, String, String), UserError, RequestedBookSearch, Any] =
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
