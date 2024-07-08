package modelClasses.googleBooks

import io.circe.generic.auto.*

object BooksRequests {

  case class RequestedBook(id: String)
  
  case class RequestedBookSearch(kind: String, totalItems: Long)

}
