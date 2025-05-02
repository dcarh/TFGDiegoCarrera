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
    (String, String) => PublicEndpoint[MovieId, UserError, Unit, Any] =
    (name, description) => mediaBaseEndpoint(name, description)
      .in("movie")
      .in(PathInputs.pathMovieId)

  val getMovie: PublicEndpoint[MovieId, UserError, Movie, Any] =
    getMovieBaseEndpoint(
      "getMovie",
      "This endpoint returns the movie specified by the ID introduced",
    )
      .out(MediaOutputs.movieOutput)

  val getEntriesForMovie: PublicEndpoint[MovieId, UserError, List[Entry], Any] =
    getMovieBaseEndpoint(
      "getEntriesForMovie",
      "This endpoint returns a list of all the entries for a specific movie",
    )
      .in("entries")
      .out(SocialOutputs.listOfEntriesOutput)

  val getMediaListsForMovie: PublicEndpoint[MovieId, UserError, List[MediaList], Any] =
    getMovieBaseEndpoint(
      "getMediaListsForMovie",
      "This endpoint returns a list of all the media lists for a specific movie",
    )
      .in("media_lists")
      .out(SocialOutputs.listOfMediaListsOutput)

}
