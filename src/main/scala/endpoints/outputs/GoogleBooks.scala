package endpoints.outputs

import io.circe.*
import io.circe.generic.auto.*
import modelClasses.googleBooks._
import sttp.tapir.*
import sttp.tapir.generic.auto.*
import sttp.tapir.json.circe.*

object GoogleBooks {

  import BooksRequests.*

  val jsonRequestedBookOut: EndpointOutput[RequestedBook] =
    jsonBody[RequestedBook]

  val jsonRequestedBookSearchOut: EndpointOutput[RequestedBookSearch] =
    jsonBody[RequestedBookSearch]

  val jsonSearchedBookListOut: EndpointOutput[List[SearchedBook]] =
    jsonBody[List[SearchedBook]]

  val listOfSearchedBooks: EndpointOutput[ListOfSearchedBooks] =
    jsonBody[ListOfSearchedBooks]

}
