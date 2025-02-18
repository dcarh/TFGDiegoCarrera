package modelClasses.googleBooks

import io.circe.generic.auto.*

object BooksRequests {

  case class RequestedBook(
                            id: String,
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
                         id: String,
                         etag: String,
                         selfLink: String,
                         volumeInfo: VolumeInfo
                         )

  case class VolumeInfo(
                       title: String,
                       subtitle: Option[String],
                       authors: List[String],
                       publisher: Option[String],
                       publishedDate: Option[String],
                       description: Option[String]
                       )

}
