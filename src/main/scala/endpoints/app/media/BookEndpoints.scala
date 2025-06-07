package endpoints.app.media

import sttp.tapir.*
import endpoints.app.media.MediaBaseEndpoint.mediaBaseEndpoint
import endpoints.io.inputs.Common.*
import endpoints.io.outputs.Common.*
import domain.errors.UserError.*
import domain.app.media.*
import domain.app.social.{Entry, MediaList}
import domain.ids.Media.*

object BookEndpoints {

  private val getBookBaseEndpoint:
    (String, String) => PublicEndpoint[BookId, UserError, Unit, Any] =
    (name, description) => mediaBaseEndpoint(name, description)
      .in("book")
      .in(PathInputs.pathBookId)

  val getBook: PublicEndpoint[BookId, UserError, Book, Any] =
    getBookBaseEndpoint(
      "getBook",
      "This endpoint returns a book by its ID"
    )
      .out(MediaOutputs.bookOutput)

  val getEntriesForBook: PublicEndpoint[BookId, UserError, List[Entry], Any] =
    getBookBaseEndpoint(
      "getEntriesForBook",
      "This endpoint returns a list of all the entries for a book",
    )
      .in("entries")
      .out(SocialOutputs.listOfEntriesOutput)

  val getMediaListsForBook: PublicEndpoint[BookId, UserError, List[MediaList], Any] =
    getBookBaseEndpoint(
      "getMediaListsForBook",
      "This endpoint returns a list of all the media lists for a book",
    )
      .in("media_lists")
      .out(SocialOutputs.listOfMediaListsOutput)

}
