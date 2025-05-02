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

object MovieEndpoints {

  private val getMovieBaseEndpoint:
    (String, String, String) => PublicEndpoint[MovieId, UserError, Unit, Any] =
    (name, description, method) => mediaBaseEndpoint(name, description, method)
      .in("movie")
      .in(PathInputs.pathMovieId)

  val getMovie: PublicEndpoint[MovieId, UserError, Movie, Any] =
    getMovieBaseEndpoint(
      "getMovie",
      "This endpoint returns the movie specified by the ID introduced",
      "GET"
    )
      .out(MediaOutputs.movieOutput)

  val getEntriesForMovie: PublicEndpoint[(MovieId, List[EntryId]), UserError, List[Entry], Any] =
    getMovieBaseEndpoint(
      "getEntriesForMovie",
      "This endpoint returns a list of all the entries for a specific movie",
      "POST_IGDB"
    )
      .in("entries")
      .in(JsonInputs.jsonListOfEntriesIds)
      .out(SocialOutputs.listOfEntriesOutput)

  val getMediaListsForMovie: PublicEndpoint[(MovieId, List[MediaListId]), UserError, List[MediaList], Any] =
    getMovieBaseEndpoint(
      "getMediaListsForMovie",
      "This endpoint returns a list of all the media lists for a specific movie",
      "POST_IGDB"
    )
      .in("media_lists")
      .in(JsonInputs.jsonListOfMediaListsIds)
      .out(SocialOutputs.listOfMediaListsOutput)

}
