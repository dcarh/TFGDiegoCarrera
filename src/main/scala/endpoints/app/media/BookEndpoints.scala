package endpoints.app.media

import sttp.tapir.*
import endpoints.app.media.MediaBaseEndpoint.mediaBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import modelClasses.errors.UserError.*
import modelClasses.app.media.*
import modelClasses.app.social.{Entry, MediaList}
import modelClasses.ids.Media.*
import modelClasses.ids.Social.{EntryId, MediaListId}

object BookEndpoints {

  private val getBookBaseEndpoint:
    (String, String, String) => PublicEndpoint[BookId, UserError, Unit, Any] =
    (name, description, method) => mediaBaseEndpoint(name, description, method)
      .in("book")
      .in(PathInputs.pathBookId)

  val getBook: PublicEndpoint[BookId, UserError, Book, Any] =
    getBookBaseEndpoint(
      "getBook",
      "This endpoint returns the book specified by the ID introduced",
      "GET"
    )
      .out(MediaOutputs.bookOutput)

  val getEntriesForBook: PublicEndpoint[(BookId, List[EntryId]), UserError, List[Entry], Any] =
    getBookBaseEndpoint(
      "getEntriesForBook",
      "This endpoint returns a list of all the entries for a specific book",
      "POST_IGDB"
    )
      .in("entries")
      .in(JsonInputs.jsonListOfEntriesIds)
      .out(SocialOutputs.listOfEntriesOutput)

  val getMediaListsForBook: PublicEndpoint[(BookId, List[MediaListId]), UserError, List[MediaList], Any] =
    getBookBaseEndpoint(
      "getMediaListsForBook",
      "This endpoint returns a list of all the media lists for a specific book",
      "POST_IGDB"
    )
      .in("mediaLists")
      .in(JsonInputs.jsonListOfMediaListsIds)
      .out(SocialOutputs.listOfMediaListsOutput)

}
