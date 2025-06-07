package endpoints.app.media

import sttp.tapir.*
import endpoints.app.media.MediaBaseEndpoint.mediaBaseEndpoint
import endpoints.inputs.Common.*
import endpoints.outputs.Common.*
import domain.errors.UserError.*
import domain.app.media.*
import domain.app.social.{Entry, MediaList}
import domain.ids.Media.*

object MovieEndpoints {

  private val getMovieBaseEndpoint:
    (String, String) => PublicEndpoint[MovieId, UserError, Unit, Any] =
    (name, description) => mediaBaseEndpoint(name, description)
      .in("movie")
      .in(PathInputs.pathMovieId)

  val getMovie: PublicEndpoint[MovieId, UserError, Movie, Any] =
    getMovieBaseEndpoint(
      "getMovie",
      "This endpoint returns a movie by its ID",
    )
      .out(MediaOutputs.movieOutput)

  val getEntriesForMovie: PublicEndpoint[MovieId, UserError, List[Entry], Any] =
    getMovieBaseEndpoint(
      "getEntriesForMovie",
      "This endpoint returns a list of all the entries for a movie",
    )
      .in("entries")
      .out(SocialOutputs.listOfEntriesOutput)

  val getMediaListsForMovie: PublicEndpoint[MovieId, UserError, List[MediaList], Any] =
    getMovieBaseEndpoint(
      "getMediaListsForMovie",
      "This endpoint returns a list of all the media lists for a movie",
    )
      .in("media_lists")
      .out(SocialOutputs.listOfMediaListsOutput)

}
