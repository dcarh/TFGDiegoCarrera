package endpoints.outputs

import io.circe.*
import io.circe.generic.auto.*
import modelClasses.googleBooks._
import sttp.tapir.*
import sttp.tapir.generic.auto.*
import sttp.tapir.json.circe.*
import codecs.decoders.MediaIDs.*
import codecs.encoders.MediaIDs.*

object GoogleBooks {

  import BooksRequests.*

  val jsonRequestedBookOut: EndpointOutput[BookFromGoogleBooks] =
    jsonBody[BookFromGoogleBooks]

  val jsonRequestedBookSearchOut: EndpointOutput[RequestedBookSearch] =
    jsonBody[RequestedBookSearch]

  val jsonSearchedBookListOut: EndpointOutput[List[SearchedBook]] =
    jsonBody[List[SearchedBook]]

  val listOfSearchedBooks: EndpointOutput[ListOfSearchedBooks] =
    jsonBody[ListOfSearchedBooks]
    
  val searchedBook: EndpointOutput[SearchedBook] =
    jsonBody[SearchedBook]

}
