package domain.googleBooks

import io.circe.generic.auto.*
import domain.ids.Media.BookId

object BooksRequests {

  case class BookFromGoogleBooks(
                            id: BookId,
                            volumeInfo: VolumeInfo
                          )
  
  case class RequestedBookSearch(kind: String, totalItems: Long)

  case class ListOfSearchedBooks(
                                kind: String,
                                totalItems: Long,
                                items: List[SearchedBook]
                                )

  case class SearchedBook(
                         kind: String,
                         id: BookId,
                         etag: String,
                         selfLink: String,
                         volumeInfo: VolumeInfo
                         )

  case class VolumeInfo(
                         title: String,
                         subtitle: Option[String],
                         authors: Option[List[String]],
                         publisher: Option[String],
                         publishedDate: Option[String],
                         description: Option[String]
                       )

}
